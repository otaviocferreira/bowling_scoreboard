package com.jobsity.usecase;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.domain.service.impl.FrameServiceImpl;
import com.jobsity.domain.service.impl.PlayServiceImpl;
import com.jobsity.domain.service.impl.PlayerServiceImpl;
import com.jobsity.usecase.impl.PopulateFramesUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PopulateFramesTest {

    private PopulateFramesUseCase populateFramesUseCase;

    @Spy
    private FrameServiceImpl frameService;

    @Spy
    private PlayServiceImpl playService;

    @Spy
    private PlayerServiceImpl playerService;

    @BeforeEach
    public void init() {
        populateFramesUseCase = new PopulateFramesUseCaseImpl(playService, frameService, playerService);
    }

    @Test
    public void execute() {
        List<String> plays = Arrays.asList("Carl	2", "Carl	3", "Carl	4", "Carl	2", "Carl	2",
                "Carl	8","Carl	2", "Carl	F", "Carl	9", "Carl	7", "Carl	3",
                "Carl	9", "Carl	10", "Carl	10", "Carl	8", "Carl	7", "Carl	3", "Carl	3");

        List<FrameDTO> frames = populateFramesUseCase.execute(plays);

        assertThat(frames).isNotNull();
        assertThat(frames.size()).isEqualTo(10);
        assertThat(frames.get(0).getFirstPinFall()).isEqualTo("2");
        assertThat(frames.get(1).getFirstPinFall()).isEqualTo("4");
        assertThat(frames.get(2).getFirstPinFall()).isEqualTo("2");
        assertThat(frames.get(3).getFirstPinFall()).isEqualTo("2");
        assertThat(frames.get(4).getFirstPinFall()).isEqualTo("9");
        assertThat(frames.get(5).getFirstPinFall()).isEqualTo("3");
        assertThat(frames.get(6).getFirstPinFall()).isEqualTo("0");
        assertThat(frames.get(7).getFirstPinFall()).isEqualTo("0");
        assertThat(frames.get(8).getFirstPinFall()).isEqualTo("8");
        assertThat(frames.get(9).getFirstPinFall()).isEqualTo("3");
    }
}
