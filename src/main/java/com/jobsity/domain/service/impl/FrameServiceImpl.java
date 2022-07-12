package com.jobsity.domain.service.impl;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.domain.dto.PlayerDTO;
import com.jobsity.domain.service.FrameService;
import com.jobsity.domain.vo.FrameVO;
import com.jobsity.exception.ExtraScoreException;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
public class FrameServiceImpl implements FrameService {

    /**
     * {@inheritDoc}
     * 
     * @see {@link #createFrame(int, String, String, String, String)}
     */
    @Override
    public List<FrameDTO> populateFramesWithPlayersAndPlays(List<PlayerDTO> playersWithPlays) {

        List<FrameDTO> frames = new ArrayList<>();

        for (PlayerDTO player : playersWithPlays) {
            int order = 1;
            for (int i = 0; i < player.getPlays().size(); i++) {
                if (order > 10) {
                    throw new ExtraScoreException(
                            String.format("This file has a extra-score for the player %s, please remove that!", player.getName()));
                }

                String play = player.getPlays().get(i);

                if (order == 10) {
                    if (play.equals("10")) {
                        frames.add(createFrame(order++, player.getName(), play, player.getPlays().get(++i), player.getPlays().get(++i)));
                    } else {
                        frames.add(createFrame(order++, player.getName(), play, player.getPlays().get(++i), null));
                    }
                    continue;
                }

                if (play.equals("F") || Integer.parseInt(play) < 10) {
                    String secondPlay = player.getPlays().get(++i);
                    frames.add(createFrame(order++, player.getName(), play, secondPlay, null));
                    continue;
                }

                frames.add(createFrame(order++, player.getName(), "0", play, null));
            }
        }

        return frames;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStrike(FrameDTO frame) {
        if (frame.getOrder().equals(10)) {
            return false;
        }

        return frame.getFirstPinFall().equals("0") && frame.getSecondPinFall().equals("10");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSpare(FrameDTO frame) {
        if (this.isStrike(frame)) {
            return false;
        }

        String playOne = frame.getFirstPinFall().equals("F") ? "0" : frame.getFirstPinFall();
        String playTwo = frame.getSecondPinFall().equals("F") ? "0" : frame.getSecondPinFall();

        return Integer.parseInt(playOne) + Integer.parseInt(playTwo) == 10;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer sumPinFalls(FrameDTO frame) {
        String playOne = frame.getFirstPinFall().equals("F") ? "0" : frame.getFirstPinFall();
        String playTwo = frame.getSecondPinFall().equals("F") ? "0" : frame.getSecondPinFall();
        String playThree = null == frame.getThirdPinFall() ? "0" : frame.getThirdPinFall();

        return Integer.parseInt(playOne) + Integer.parseInt(playTwo) + Integer.parseInt(playThree);
    }

    /**
     * This method is just to separate the builder code, just to clean the code that calls it.
     *
     * @param order The order for the frame.
     * @param playerName The player's name.
     * @param firstPlay The first play for the frame.
     * @param secondPlay The second play for the frame.
     * @param thirdPinFall The third play for the frame.
     * @return A new {@link FrameDTO} containing all these values.
     *
     * @since 1.0.0
     */
    private FrameDTO createFrame(int order, String playerName, String firstPlay, String secondPlay, String thirdPinFall) {
        return FrameVO.builder()
                .order(order)
                .playerName(playerName)
                .firstPinFall(firstPlay)
                .secondPinFall(secondPlay)
                .thirdPinFall(thirdPinFall)
                .build();
    }
}
