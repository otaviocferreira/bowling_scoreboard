package com.jobsity.usecase;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.domain.vo.FrameVO;
import com.jobsity.usecase.impl.SeparateFramesPerPlayerUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SeparateFramesPerPLayerTest {

    private final SeparateFramesPerPlayerUseCase separateFramesPerPlayerUseCase = new SeparateFramesPerPlayerUseCaseImpl();

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
        frames.add(FrameVO.builder().order(9).playerName("Carl").firstPinFall("0").secondPinFall("100").build());
        frames.add(FrameVO.builder().order(10).playerName("Carl").firstPinFall("10").secondPinFall("10").thirdPinFall("10").build());

        Map<String, List<FrameDTO>> framesPerPlayer = separateFramesPerPlayerUseCase.execute(frames);

        assertThat(framesPerPlayer).isNotNull();
        assertThat(framesPerPlayer.size()).isEqualTo(2);
        assertThat(framesPerPlayer.get("Otavio")).isNotNull();
        assertThat(framesPerPlayer.get("Otavio").size()).isEqualTo(10);
        assertThat(framesPerPlayer.get("Otavio").get(0).getFirstPinFall()).isEqualTo("3");
        assertThat(framesPerPlayer.get("Otavio").get(9).getThirdPinFall()).isEqualTo("0");
        assertThat(framesPerPlayer.get("Carl")).isNotNull();
        assertThat(framesPerPlayer.get("Carl").size()).isEqualTo(10);
        assertThat(framesPerPlayer.get("Carl").get(0).getFirstPinFall()).isEqualTo("0");
        assertThat(framesPerPlayer.get("Carl").get(9).getThirdPinFall()).isEqualTo("10");
    }
}
