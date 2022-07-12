package com.jobsity.infrastructure;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.infrastructure.CommandLineService;
import com.jobsity.infrastructure.impl.CommandLineServiceImpl;
import com.jobsity.domain.vo.FrameVO;
import com.jobsity.usecase.CalculateScoreForFramesUseCase;
import com.jobsity.usecase.PopulateFramesUseCase;
import com.jobsity.usecase.RetrieveFramesFromFileUseCase;
import com.jobsity.usecase.SeparateFramesPerPlayerUseCase;
import com.jobsity.usecase.SetPinfallSymbologyUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommandLineTest {

    private CommandLineService commandLineService;

    @Mock
    private RetrieveFramesFromFileUseCase retrieveFramesFromFileUseCase;

    @Mock
    private PopulateFramesUseCase populateFramesUseCase;

    @Mock
    private SeparateFramesPerPlayerUseCase separateFramesPerPlayerUseCase;

    @Mock
    private CalculateScoreForFramesUseCase calculateScoreForFramesUseCase;

    @Mock
    private SetPinfallSymbologyUseCase setPinfallSymbologyUseCase;

    @BeforeEach
    public void init() {
        commandLineService = new CommandLineServiceImpl(retrieveFramesFromFileUseCase,
                populateFramesUseCase, separateFramesPerPlayerUseCase, calculateScoreForFramesUseCase, setPinfallSymbologyUseCase);
    }

    @Test
    public void processFile() {
        File file = new File("");

        Map<String, List<FrameDTO>> separatedFrames = new HashMap<>();
        separatedFrames.put("Otavio", Collections.singletonList(FrameVO.builder().order(1).playerName("Otavio").score(100).build()));

        when(retrieveFramesFromFileUseCase.execute(file)).thenReturn(new ArrayList<>());
        when(populateFramesUseCase.execute(any())).thenReturn(new ArrayList<>());
        when(separateFramesPerPlayerUseCase.execute(any())).thenReturn(separatedFrames);

        Map<String, List<FrameDTO>> result = commandLineService.processFile(file);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get("Otavio")).isNotNull();
        assertThat(result.get("Otavio").get(0)).isNotNull();
        assertThat(result.get("Otavio").get(0).getOrder()).isEqualTo(1);
        assertThat(result.get("Otavio").get(0).getScore()).isEqualTo(100);
    }

    @Test
    public void processFileWithFileNull() {
        Map<String, List<FrameDTO>> separatedFrames = new HashMap<>();
        separatedFrames.put("Otavio", Collections.singletonList(FrameVO.builder().order(1).playerName("Otavio").score(100).build()));

        when(retrieveFramesFromFileUseCase.execute(any(File.class))).thenReturn(new ArrayList<>());
        when(populateFramesUseCase.execute(any())).thenReturn(new ArrayList<>());
        when(separateFramesPerPlayerUseCase.execute(any())).thenReturn(separatedFrames);

        Map<String, List<FrameDTO>> result = commandLineService.processFile(null);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get("Otavio")).isNotNull();
        assertThat(result.get("Otavio").get(0)).isNotNull();
        assertThat(result.get("Otavio").get(0).getOrder()).isEqualTo(1);
        assertThat(result.get("Otavio").get(0).getScore()).isEqualTo(100);
    }
}
