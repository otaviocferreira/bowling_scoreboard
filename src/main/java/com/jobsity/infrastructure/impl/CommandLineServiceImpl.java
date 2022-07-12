package com.jobsity.infrastructure.impl;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.infrastructure.CommandLineService;
import com.jobsity.usecase.*;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * {@inheritDoc}
 */
@RequiredArgsConstructor
public class CommandLineServiceImpl implements CommandLineService {

    private final RetrieveFramesFromFileUseCase retrieveFramesFromFileUseCase;
    private final PopulateFramesUseCase populateFramesUseCase;
    private final SeparateFramesPerPlayerUseCase separateFramesPerPlayerUseCase;
    private final CalculateScoreForFramesUseCase calculateScoreForFramesUseCase;
    private final SetPinfallSymbologyUseCase setPinfallSymbologyUseCase;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, List<FrameDTO>> processFile(File file) {

        if (null == file) {
            char sep = File.separatorChar;
            file = new File("src" + sep + "main" + sep + "resources" + sep + "real-play.txt");
        }

        List<String> framesList = retrieveFramesFromFileUseCase.execute(file);

        List<FrameDTO> populatedFrames = populateFramesUseCase.execute(framesList);

        Map<String, List<FrameDTO>> separatedFrames = separateFramesPerPlayerUseCase.execute(populatedFrames);

        separatedFrames.forEach((playerName, frames) -> calculateScoreForFramesUseCase.execute(frames));

        setPinfallSymbologyUseCase.execute(populatedFrames);

        return separatedFrames;
    }
}
