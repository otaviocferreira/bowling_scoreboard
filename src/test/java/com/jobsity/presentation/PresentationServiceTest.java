package com.jobsity.presentation;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.domain.vo.FrameVO;
import com.jobsity.presentation.impl.PresentationServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PresentationServiceTest {

    final PresentationService playerService = new PresentationServiceImpl();

    @Test
    public void separatePlaysOfPlayers() {
        Map<String, List<FrameDTO>> separatedFrames = new HashMap<>();

        FrameVO frame1 = FrameVO.builder().order(1).playerName("Otavio").firstPinFall("3").secondPinFall("7").score(16).build();
        FrameVO frame2 = FrameVO.builder().order(2).playerName("Otavio").firstPinFall("6").secondPinFall("3").score(25).build();
        FrameVO frame3 = FrameVO.builder().order(3).playerName("Otavio").firstPinFall("0").secondPinFall("10").score(44).build();
        FrameVO frame4 = FrameVO.builder().order(4).playerName("Otavio").firstPinFall("8").secondPinFall("1").score(53).build();
        FrameVO frame5 = FrameVO.builder().order(5).playerName("Otavio").firstPinFall("0").secondPinFall("10").score(82).build();
        FrameVO frame6 = FrameVO.builder().order(6).playerName("Otavio").firstPinFall("0").secondPinFall("10").score(101).build();
        FrameVO frame7 = FrameVO.builder().order(7).playerName("Otavio").firstPinFall("9").secondPinFall("0").score(110).build();
        FrameVO frame8 = FrameVO.builder().order(8).playerName("Otavio").firstPinFall("7").secondPinFall("3").score(124).build();
        FrameVO frame9 = FrameVO.builder().order(9).playerName("Otavio").firstPinFall("4").secondPinFall("4").score(132).build();
        FrameVO frame10 = FrameVO.builder().order(10).playerName("Otavio").firstPinFall("10").secondPinFall("9").thirdPinFall("0").score(151).build();

        separatedFrames.put("Otavio", Arrays.asList(frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10));

        String scoreBoard = playerService.setupSimpleScoreBoard(separatedFrames);

        assertThat(scoreBoard).isNotNull();
        assertThat(scoreBoard).isEqualTo("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n" +
                "Otavio\n" +
                "Pinfalls\t3\t7\t6\t3\t0\t10\t8\t1\t0\t10\t0\t10\t9\t0\t7\t3\t4\t4\t10\t9\t0\n" +
                "Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151\n");
    }
}
