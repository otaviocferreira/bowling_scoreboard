package com.jobsity.usecase.impl;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.domain.service.FrameService;
import com.jobsity.usecase.CalculateScoreForFramesUseCase;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@inheritDoc}
 */
@RequiredArgsConstructor
public class CalculateScoreForFramesUseCaseImpl implements CalculateScoreForFramesUseCase {

    private final FrameService frameService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(List<FrameDTO> frames) {
        Map<FrameDTO, Integer> framesWithBonus = new HashMap<>();

        frames.forEach(frame -> {
            if (frameService.isStrike(frame)) {
                populatePastPlays(framesWithBonus, frame.getSecondPinFall());
            } else {
                populatePastPlays(framesWithBonus, frame.getFirstPinFall());
                populatePastPlays(framesWithBonus, frame.getSecondPinFall());
            }

            frame.setScore(frameService.sumPinFalls(frame));

            if (frameService.isStrike(frame)) {
                framesWithBonus.put(frame, 2);
            }

            if (frameService.isSpare(frame)) {
                framesWithBonus.put(frame, 1);
            }
        });

        for (int i = 1; i < frames.size(); i++) {
            frames.get(i).setScore(frames.get(i).getScore() + frames.get(i - 1).getScore());
        }
    }

    /**
     * Here it needs to populate the Frames with bonuses plays, using a {@link Map} containing that special plays with bonus count greater than 0.
     *
     * @param framesWithBonus A {@link Map} containing all the special plays and its count for receiving bonus.
     * @param pinFall         The play that need to be increased on special plays.
     * @since 1.0.0
     */
    private void populatePastPlays(Map<FrameDTO, Integer> framesWithBonus, String pinFall) {
        framesWithBonus.forEach((frameToIncrease, count) -> {
            if (count > 0) {
                int pinFallsToIncrease = pinFall.equals("F") ? 0 : Integer.parseInt(pinFall);
                frameToIncrease.setScore(frameToIncrease.getScore() + pinFallsToIncrease);
                framesWithBonus.put(frameToIncrease, --count);
            }
        });
    }
}
