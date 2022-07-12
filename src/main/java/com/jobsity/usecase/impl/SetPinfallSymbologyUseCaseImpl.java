package com.jobsity.usecase.impl;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.domain.service.FrameService;
import com.jobsity.usecase.SetPinfallSymbologyUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * {@inheritDoc}
 */
@RequiredArgsConstructor
public class SetPinfallSymbologyUseCaseImpl implements SetPinfallSymbologyUseCase {

    private final FrameService frameService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(List<FrameDTO> allFrames) {
        allFrames.forEach(this::setFrameSymbology);
    }

    /**
     * Set the pinfall symbology on the pinfalls attributes from a frame.
     *
     * @param frame A {@link FrameDTO} that will have your pinfall attributes set up.
     *
     * @since 1.0.0
     */
    private void setFrameSymbology(FrameDTO frame) {
        if (frameService.isStrike(frame)) {
            frame.setFirstPinFall("");
            frame.setSecondPinFall("X");
            return;
        } else if (frameService.isSpare(frame)) {
            frame.setSecondPinFall("/");
            return;
        }

        frame.setFirstPinFall(setPinfall(frame.getFirstPinFall()));
        frame.setSecondPinFall(setPinfall(frame.getSecondPinFall()));
        frame.setThirdPinFall(setPinfall(frame.getThirdPinFall()));
    }

    /**
     * Set the right representation for the pinfall.
     *
     * @param pinfall A pin fall value for transformation
     * @return A correct value for pin fall.
     *
     * @since 1.0.0
     */
    private String setPinfall(String pinfall) {
        if (null != pinfall) {
            if (pinfall.equals("10")) {
                return "X";
            } else if (pinfall.equals("0")) {
                return "-";
            }
        }

        return pinfall;
    }
}
