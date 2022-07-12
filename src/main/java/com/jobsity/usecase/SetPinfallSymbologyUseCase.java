package com.jobsity.usecase;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.domain.service.FrameService;

import java.util.List;

/**
 * This use case is responsible for setting symbology for all game's frames.
 *
 * It needs to use a reference for {@link FrameService} to manipulate the Frame instances.
 *
 * @see FrameDTO
 * @since 1.0.0
 * @author Otavio Ferreira
 */
public interface SetPinfallSymbologyUseCase {

    /**
     * Execute the use case.
     *
     * @param allFrames A {@link FrameDTO} list containing all frames from a game.
     *
     * @since 1.0.0
     */
    void execute(List<FrameDTO> allFrames);
}
