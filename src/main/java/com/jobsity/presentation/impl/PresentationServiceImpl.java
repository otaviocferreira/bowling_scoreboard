package com.jobsity.presentation.impl;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.presentation.PresentationService;

import java.util.List;
import java.util.Map;

/**
 * {@inheritDoc}
 */
public class PresentationServiceImpl implements PresentationService {

    /**
     * {@inheritDoc}
     */
    @Override
    public String setupSimpleScoreBoard(Map<String, List<FrameDTO>> separatedFrames) {
        StringBuilder scoreBoard = populateHeaders();

        separatedFrames.forEach((playerName, frames) -> {
            scoreBoard.append(playerName.concat("\n"));
            scoreBoard.append(populatePinfalls(frames));
            scoreBoard.append(populateScore(frames));
        });

        return scoreBoard.toString();
    }

    /**
     * Create a line of Frames on scoreboard.
     *
     * @return A {@link StringBuilder} that contains a frame line to scoreboard.
     *
     * @since 1.0.0
     */
    private StringBuilder populateHeaders() {
        return new StringBuilder("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n");
    }

    /**
     * Create a pin falls line from one player on scoreboard.
     *
     * @return A {@link String} that contains a pin falls sequence line to scoreboard.
     *
     * @since 1.0.0
     */
    private String populatePinfalls(List<FrameDTO> frames) {
        StringBuilder pinfalls = new StringBuilder("Pinfalls");

        frames.forEach(frame -> {
            pinfalls.append("\t".concat(frame.getFirstPinFall()));
            pinfalls.append("\t".concat(frame.getSecondPinFall()));
            if (null != frame.getThirdPinFall()) {
                pinfalls.append("\t".concat(frame.getThirdPinFall()));
            }
        });

        pinfalls.append("\n");

        return pinfalls.toString();
    }

    /**
     * Create the score line from one player on scoreboard.
     *
     * @return A {@link String} that contains the score sequence line to scoreboard.
     *
     * @since 1.0.0
     */
    private String populateScore(List<FrameDTO> frames) {
        StringBuilder score = new StringBuilder("Score");

        frames.forEach(frame -> score.append("\t\t".concat(frame.getScore().toString())));

        score.append("\n");

        return score.toString();
    }
}
