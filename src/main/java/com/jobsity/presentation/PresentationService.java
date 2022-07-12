package com.jobsity.presentation;

import com.jobsity.domain.dto.FrameDTO;

import java.util.List;
import java.util.Map;

/**
 * This interface needs to be used for set up the ScoreBoard. Just choose which way you want.
 *
 * @see FrameDTO
 * @since 1.0.0
 * @author Otavio Ferreira
 */
public interface PresentationService {

    /**
     * It creates a simple presentation in plain text for the scoreboard.
     *
     * @param separatedFrames A {@link FrameDTO} {@link Map} containing all the frames from a game separated by player.
     * @return A presentation in plain text of the scoreboard.
     *
     * @since 1.0.0
     */
    String setupSimpleScoreBoard(Map<String, List<FrameDTO>> separatedFrames);
}
