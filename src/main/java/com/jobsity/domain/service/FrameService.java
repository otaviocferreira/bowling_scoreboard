package com.jobsity.domain.service;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.domain.dto.PlayerDTO;
import com.jobsity.exception.FrameFileException;

import java.util.List;

/**
 * This class is responsible for all interactions with FramesDTO.
 *
 * @see FrameDTO
 * @since 1.0.0
 * @author Otavio Ferreira
 */
public interface FrameService {

    /**
     * Using a list of players, containing a list of respective plays,</br>
     * a list of frames is created with the separate information of plays</br>
     * that were performed sequentially by a player.
     *
     * @param playersWithPlays {@link PlayerDTO} A list of players and your plays.
     * @return {@link FrameDTO} A list of created frames by player.
     * @throws FrameFileException In case of any extra-score for any player.
     *
     * @since 1.0.0
     */
    List<FrameDTO> populateFramesWithPlayersAndPlays(List<PlayerDTO> playersWithPlays);

    /**
     * Verify if a frame contains a strike.
     *
     * There is one verification here:
     * {@code
     *   if (frame.getOrder().equals(10)) {
     *      return false;
     *   }
     * }
     *
     * @param frame A {@link FrameDTO} that contains a sequence of plays.
     * @return TRUE if it's a strike or FALSE if it's not one.
     *
     * @since 1.0.0
     */
    boolean isStrike(FrameDTO frame);

    /**
     * Verify if a frame contains a spare.
     *
     * Here we need to use {@link #isStrike(FrameDTO) isStrike} to verify if it's just a spare.
     *
     * There is one verification here:
     * {@code
     *   if (frame.getOrder().equals(10) || this.isStrike(frame)) {
     *      return false;
     *   }
     * }
     *
     * @param frame A {@link FrameDTO} that contains a sequence of plays.
     * @return TRUE if it's a spare or FALSE if it's not.
     *
     * @since 1.0.0
     */
    boolean isSpare(FrameDTO frame);

    /**
     * Sum the plays in a Frame to increase its score.
     *
     * @param frame A {@link FrameDTO} that contains a sequence of plays.
     * @return A sum of all plays from a frame.
     *
     * @since 1.0.0
     */
    Integer sumPinFalls(FrameDTO frame);
}
