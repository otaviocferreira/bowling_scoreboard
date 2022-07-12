package com.jobsity.usecase.impl;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.domain.dto.PlayDTO;
import com.jobsity.domain.dto.PlayerDTO;
import com.jobsity.domain.service.FrameService;
import com.jobsity.domain.service.PlayService;
import com.jobsity.domain.service.PlayerService;
import com.jobsity.usecase.PopulateFramesUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * {@inheritDoc}
 */
@RequiredArgsConstructor
public class PopulateFramesUseCaseImpl implements PopulateFramesUseCase {

    private final PlayService playService;
    private final FrameService frameService;
    private final PlayerService playerService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FrameDTO> execute(List<String> playsFromInput) {
        List<PlayDTO> mixedPlays = playService.separatePLays(playsFromInput);

        List<PlayerDTO> separatedPlayers = playerService.separatePlaysOfPlayers(mixedPlays);

        return frameService.populateFramesWithPlayersAndPlays(separatedPlayers);
    }
}
