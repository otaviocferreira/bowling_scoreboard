package com.jobsity.usecase;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.domain.service.FrameService;

import java.util.List;

/**
 * This use case requests that scores be calculated according to the plays performed by the players.
 *
 * It needs to use a reference for {@link FrameService} to manipulate the Frame instances.
 *
 * @see FrameDTO
 * @since 1.0.0
 * @author Otavio Ferreira
 */
public interface CalculateScoreForFramesUseCase {

    /**
     * Execute the use case.
     *
     * @param frames A list of {@link FrameDTO} that contains all plays performed by players.
     *
     * @since 1.0.0
     */
    void execute(List<FrameDTO> frames);
}
