package com.jobsity.usecase;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.domain.service.FrameService;
import com.jobsity.domain.service.PlayService;
import com.jobsity.domain.service.PlayerService;

import java.util.List;

/**
 * This use case uses the list, where each element is a play, for a list of frames that will be used by the application.
 * <p>
 * It needs to use a reference for:<p>
 * - {@link FrameService} to manipulate the Frame instances.<p>
 * - {@link PlayerService} to manipulate the Player instances.<p>
 * - {@link PlayService} to manipulate the Play instances.<p>
 *
 * @see FrameDTO
 * @since 1.0.0
 * @author Otavio Ferreira
 */
public interface PopulateFramesUseCase {

    /**
     * Execute the use case.
     *
     * @param playsFromInput A list of {@link String} that contains all plays performed by players.
     *
     * @since 1.0.0
     */
    List<FrameDTO> execute(List<String> playsFromInput);
}
