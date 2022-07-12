package com.jobsity.usecase;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.domain.service.impl.FrameServiceImpl;
import com.jobsity.domain.vo.FrameVO;
import com.jobsity.usecase.impl.CalculateScoreForFramesUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CalculateScoreForFramesTest {

    private CalculateScoreForFramesUseCase calculateScoreForFramesUseCase;

    @Spy
    private FrameServiceImpl frameService;

    @BeforeEach
    public void init() {
        calculateScoreForFramesUseCase = new CalculateScoreForFramesUseCaseImpl(frameService);
    }

    @Test
    public void execute() {
        List<FrameDTO> frames = new ArrayList<>();

        frames.add(FrameVO.builder().order(1).playerName("Otavio").firstPinFall("3").secondPinFall("7").build());
        frames.add(FrameVO.builder().order(2).playerName("Otavio").firstPinFall("6").secondPinFall("3").build());
        frames.add(FrameVO.builder().order(3).playerName("Otavio").firstPinFall("0").secondPinFall("10").build());
        frames.add(FrameVO.builder().order(4).playerName("Otavio").firstPinFall("8").secondPinFall("1").build());
        frames.add(FrameVO.builder().order(5).playerName("Otavio").firstPinFall("0").secondPinFall("10").build());
        frames.add(FrameVO.builder().order(6).playerName("Otavio").firstPinFall("0").secondPinFall("10").build());
        frames.add(FrameVO.builder().order(7).playerName("Otavio").firstPinFall("9").secondPinFall("0").build());
        frames.add(FrameVO.builder().order(8).playerName("Otavio").firstPinFall("7").secondPinFall("3").build());
        frames.add(FrameVO.builder().order(9).playerName("Otavio").firstPinFall("4").secondPinFall("4").build());
        frames.add(FrameVO.builder().order(10).playerName("Otavio").firstPinFall("10").secondPinFall("9").thirdPinFall("0").build());

        calculateScoreForFramesUseCase.execute(frames);

        assertThat(frames).isNotNull();
        assertThat(frames.size()).isEqualTo(10);
        assertThat(frames.get(0).getScore()).isEqualTo(16);
        assertThat(frames.get(1).getScore()).isEqualTo(25);
        assertThat(frames.get(2).getScore()).isEqualTo(44);
        assertThat(frames.get(3).getScore()).isEqualTo(53);
        assertThat(frames.get(4).getScore()).isEqualTo(82);
        assertThat(frames.get(5).getScore()).isEqualTo(101);
        assertThat(frames.get(6).getScore()).isEqualTo(110);
        assertThat(frames.get(7).getScore()).isEqualTo(124);
        assertThat(frames.get(8).getScore()).isEqualTo(132);
        assertThat(frames.get(9).getScore()).isEqualTo(151);
    }
}
