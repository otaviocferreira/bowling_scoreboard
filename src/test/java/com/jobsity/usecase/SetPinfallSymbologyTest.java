package com.jobsity.usecase;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.domain.service.impl.FrameServiceImpl;
import com.jobsity.domain.vo.FrameVO;
import com.jobsity.usecase.impl.SetPinfallSymbologyUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SetPinfallSymbologyTest {

    private SetPinfallSymbologyUseCase setPinfallSymbologyUseCase;

    @Spy
    private FrameServiceImpl frameService;

    @BeforeEach
    public void init() {
        setPinfallSymbologyUseCase = new SetPinfallSymbologyUseCaseImpl(frameService);
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
        frames.add(FrameVO.builder().order(1).playerName("Carl").firstPinFall("0").secondPinFall("10").build());
        frames.add(FrameVO.builder().order(2).playerName("Carl").firstPinFall("0").secondPinFall("10").build());
        frames.add(FrameVO.builder().order(3).playerName("Carl").firstPinFall("0").secondPinFall("10").build());
        frames.add(FrameVO.builder().order(4).playerName("Carl").firstPinFall("0").secondPinFall("10").build());
        frames.add(FrameVO.builder().order(5).playerName("Carl").firstPinFall("0").secondPinFall("10").build());
        frames.add(FrameVO.builder().order(6).playerName("Carl").firstPinFall("0").secondPinFall("10").build());
        frames.add(FrameVO.builder().order(7).playerName("Carl").firstPinFall("0").secondPinFall("10").build());
        frames.add(FrameVO.builder().order(8).playerName("Carl").firstPinFall("0").secondPinFall("10").build());
        frames.add(FrameVO.builder().order(9).playerName("Carl").firstPinFall("0").secondPinFall("10").build());
        frames.add(FrameVO.builder().order(10).playerName("Carl").firstPinFall("10").secondPinFall("10").thirdPinFall("10").build());

        setPinfallSymbologyUseCase.execute(frames);

        assertThat(frames).isNotNull();
        assertThat(frames.size()).isEqualTo(20);
        assertThat(frames.get(0).getPlayerName()).isEqualTo("Otavio");
        assertThat(frames.get(0).getSecondPinFall()).isEqualTo("/");
        assertThat(frames.get(7).getPlayerName()).isEqualTo("Otavio");
        assertThat(frames.get(7).getSecondPinFall()).isEqualTo("/");
        assertThat(frames.get(10).getPlayerName()).isEqualTo("Carl");
        assertThat(frames.get(10).getSecondPinFall()).isEqualTo("X");
        assertThat(frames.get(17).getPlayerName()).isEqualTo("Carl");
        assertThat(frames.get(17).getFirstPinFall()).isEmpty();
        assertThat(frames.get(19).getPlayerName()).isEqualTo("Carl");
        assertThat(frames.get(19).getThirdPinFall()).isEqualTo("X");
    }
}
