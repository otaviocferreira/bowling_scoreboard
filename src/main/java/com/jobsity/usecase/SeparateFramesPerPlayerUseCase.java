package com.jobsity.usecase;

import com.jobsity.domain.dto.FrameDTO;

import java.util.List;
import java.util.Map;

/**
 * This use case is responsible for separating all plays by their respective player.
 *
 * @see FrameDTO
 * @since 1.0.0
 * @author Otavio Ferreira
 */
public interface SeparateFramesPerPlayerUseCase {

    /**
     * Execute the use case.
     *
     * @param allFrames A {@link FrameDTO} list containing all frames from a game.
     * @return A {@link Map} contains all plays separated by their players.
     *
     * @since 1.0.0
     */
    Map<String, List<FrameDTO>> execute(List<FrameDTO> allFrames);
}
