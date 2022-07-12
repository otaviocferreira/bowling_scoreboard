package com.jobsity;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.infrastructure.CommandLineService;
import com.jobsity.infrastructure.impl.CommandLineServiceImpl;
import com.jobsity.domain.service.FrameService;
import com.jobsity.domain.service.PlayService;
import com.jobsity.domain.service.PlayerService;
import com.jobsity.domain.service.impl.FrameServiceImpl;
import com.jobsity.domain.service.impl.PlayServiceImpl;
import com.jobsity.domain.service.impl.PlayerServiceImpl;
import com.jobsity.presentation.PresentationService;
import com.jobsity.presentation.impl.PresentationServiceImpl;
import com.jobsity.usecase.CalculateScoreForFramesUseCase;
import com.jobsity.usecase.PopulateFramesUseCase;
import com.jobsity.usecase.RetrieveFramesFromFileUseCase;
import com.jobsity.usecase.SeparateFramesPerPlayerUseCase;
import com.jobsity.usecase.SetPinfallSymbologyUseCase;
import com.jobsity.usecase.impl.CalculateScoreForFramesUseCaseImpl;
import com.jobsity.usecase.impl.PopulateFramesUseCaseImpl;
import com.jobsity.usecase.impl.RetrieveFramesFromFileUseCaseImpl;
import com.jobsity.usecase.impl.SeparateFramesPerPlayerUseCaseImpl;
import com.jobsity.usecase.impl.SetPinfallSymbologyUseCaseImpl;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class App {
    private static RetrieveFramesFromFileUseCase retrieveFramesFromFileUseCase;
    private static PopulateFramesUseCase populateFramesUseCase;
    private static SeparateFramesPerPlayerUseCase separateFramesPerPlayerUseCase;
    private static CalculateScoreForFramesUseCase calculateScoreForFramesUseCase;
    private static SetPinfallSymbologyUseCase setPinfallSymbologyUseCase;

    public static void main(String[] args) {
        createDependencyInjection();

        final CommandLineService commandLineService = new CommandLineServiceImpl(retrieveFramesFromFileUseCase,
                populateFramesUseCase, separateFramesPerPlayerUseCase, calculateScoreForFramesUseCase, setPinfallSymbologyUseCase);

        PresentationService presentationService = new PresentationServiceImpl();
        File file = null;

        if (args.length > 0) {
            file = new File(args[0]);
        }

        Map<String, List<FrameDTO>> separatedFrames = commandLineService.processFile(file);

        System.out.println(presentationService.setupSimpleScoreBoard(separatedFrames));
    }

    public static void createDependencyInjection() {
        final PlayService playService = new PlayServiceImpl();
        final FrameService frameService = new FrameServiceImpl();
        final PlayerService playerService = new PlayerServiceImpl();

        retrieveFramesFromFileUseCase = new RetrieveFramesFromFileUseCaseImpl();
        populateFramesUseCase = new PopulateFramesUseCaseImpl(playService, frameService, playerService);
        separateFramesPerPlayerUseCase = new SeparateFramesPerPlayerUseCaseImpl();
        calculateScoreForFramesUseCase = new CalculateScoreForFramesUseCaseImpl(frameService);
        setPinfallSymbologyUseCase = new SetPinfallSymbologyUseCaseImpl(frameService);
    }
}
