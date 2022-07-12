package com.jobsity.usecase.impl;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.usecase.SeparateFramesPerPlayerUseCase;

import java.util.*;

/**
 * {@inheritDoc}
 */
public class SeparateFramesPerPlayerUseCaseImpl implements SeparateFramesPerPlayerUseCase {

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, List<FrameDTO>> execute(List<FrameDTO> allFrames) {
        Map<String, List<FrameDTO>> framesPerPlayer = new HashMap<>();

        allFrames.forEach(frame -> {
            if (!framesPerPlayer.containsKey(frame.getPlayerName())) {
                framesPerPlayer.put(frame.getPlayerName(), new ArrayList<>());
            }
            framesPerPlayer.get(frame.getPlayerName()).add(frame);
        });

        return framesPerPlayer;
    }
}
