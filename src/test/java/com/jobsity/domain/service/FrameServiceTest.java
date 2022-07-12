package com.jobsity.domain.service;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.domain.service.impl.FrameServiceImpl;
import com.jobsity.domain.vo.FrameVO;
import com.jobsity.domain.vo.PlayerVO;
import com.jobsity.exception.ExtraScoreException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class FrameServiceTest {

    final FrameService frameService = new FrameServiceImpl();

    @Test
    public void populateFramesWithPlayersAndPlays() {
        PlayerVO player = PlayerVO.builder().name("Otavio").plays(Arrays.asList("2", "7")).build();

        List<FrameDTO> frames = frameService.populateFramesWithPlayersAndPlays(Collections.singletonList(player));

        assertThat(frames).isNotNull();
        assertThat(frames.size()).isEqualTo(1);
        assertThat(frames.get(0)).isNotNull();
        assertThat(frames.get(0).getOrder()).isEqualTo(1);
        assertThat(frames.get(0).getFirstPinFall()).isEqualTo("2");
        assertThat(frames.get(0).getSecondPinFall()).isEqualTo("7");
    }

    @Test
    public void populateFramesWithPlayersAndPlaysWithPerfectGame() {
        PlayerVO player = PlayerVO.builder().name("Otavio")
                .plays(Arrays.asList("10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10")).build();

        List<FrameDTO> frames = frameService.populateFramesWithPlayersAndPlays(Collections.singletonList(player));

        assertThat(frames).isNotNull();
        assertThat(frames.size()).isEqualTo(10);
        assertThat(frames.get(0)).isNotNull();
        assertThat(frames.get(0).getOrder()).isEqualTo(1);
        assertThat(frames.get(0).getFirstPinFall()).isEqualTo("0");
        assertThat(frames.get(0).getSecondPinFall()).isEqualTo("10");
        assertThat(frames.get(9)).isNotNull();
        assertThat(frames.get(9).getFirstPinFall()).isEqualTo("10");
        assertThat(frames.get(9).getSecondPinFall()).isEqualTo("10");
        assertThat(frames.get(9).getThirdPinFall()).isEqualTo("10");
    }

    @Test
    public void populateFramesWithPlayersAndPlaysWithSpareEndGame() {
        PlayerVO player = PlayerVO.builder().name("Otavio")
                .plays(Arrays.asList("10", "10", "10", "10", "10", "10", "10", "10", "10", "2", "8")).build();

        List<FrameDTO> frames = frameService.populateFramesWithPlayersAndPlays(Collections.singletonList(player));

        assertThat(frames).isNotNull();
        assertThat(frames.size()).isEqualTo(10);
        assertThat(frames.get(0)).isNotNull();
        assertThat(frames.get(0).getOrder()).isEqualTo(1);
        assertThat(frames.get(9)).isNotNull();
        assertThat(frames.get(9).getFirstPinFall()).isEqualTo("2");
        assertThat(frames.get(9).getSecondPinFall()).isEqualTo("8");
        assertThat(frames.get(9).getThirdPinFall()).isNull();
    }

    @Test
    public void populateFramesWithPlayersAndPlaysWithExtraScoreGame() {
        PlayerVO player = PlayerVO.builder().name("Otavio")
                .plays(Arrays.asList("10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10")).build();

        try {
            frameService.populateFramesWithPlayersAndPlays(Collections.singletonList(player));
            fail("An ExtraScoreException must've been thrown here!");
        } catch(ExtraScoreException e) {
            assertThat(e.getMessage()).isEqualTo("This file has a extra-score for the player Otavio, please remove that!");
        }
    }

    @Test
    public void isStrike() {
        FrameVO frame = FrameVO.builder().order(1).playerName("Otavio").firstPinFall("2").secondPinFall("7").build();
        FrameVO strikedFrame = FrameVO.builder().order(1).playerName("Jeff").firstPinFall("0").secondPinFall("10").build();

        assertThat(frameService.isStrike(frame)).isFalse();
        assertThat(frameService.isStrike(strikedFrame)).isTrue();
    }

    @Test
    public void isSpare() {
        FrameVO frame = FrameVO.builder().order(1).playerName("Otavio").firstPinFall("2").secondPinFall("7").build();
        FrameVO sparedFrame = FrameVO.builder().order(1).playerName("Jeff").firstPinFall("4").secondPinFall("6").build();

        assertThat(frameService.isSpare(frame)).isFalse();
        assertThat(frameService.isSpare(sparedFrame)).isTrue();
    }

    @Test
    public void sumPinFalls() {
        FrameVO frame = FrameVO.builder().order(1).playerName("Otavio").firstPinFall("2").secondPinFall("7").build();
        FrameVO tenthFrame = FrameVO.builder().order(1).playerName("Jeff").firstPinFall("10").secondPinFall("6").thirdPinFall("2").build();

        assertThat(frameService.sumPinFalls(frame)).isEqualTo(9);
        assertThat(frameService.sumPinFalls(tenthFrame)).isEqualTo(18);
    }
}
