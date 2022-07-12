package com.jobsity.integration;

import com.jobsity.domain.dto.FrameDTO;
import com.jobsity.domain.service.FrameService;
import com.jobsity.domain.service.PlayService;
import com.jobsity.domain.service.PlayerService;
import com.jobsity.domain.service.impl.FrameServiceImpl;
import com.jobsity.domain.service.impl.PlayServiceImpl;
import com.jobsity.domain.service.impl.PlayerServiceImpl;
import com.jobsity.exception.EmptyFileException;
import com.jobsity.exception.ExtraScoreException;
import com.jobsity.exception.FrameFileException;
import com.jobsity.exception.InvalidFileException;
import com.jobsity.infrastructure.CommandLineService;
import com.jobsity.infrastructure.impl.CommandLineServiceImpl;
import com.jobsity.presentation.PresentationService;
import com.jobsity.presentation.impl.PresentationServiceImpl;
import com.jobsity.usecase.CalculateScoreForFramesUseCase;
import com.jobsity.usecase.PopulateFramesUseCase;
import com.jobsity.usecase.RetrieveFramesFromFileUseCase;
import com.jobsity.usecase.SeparateFramesPerPlayerUseCase;
import com.jobsity.usecase.SetPinfallSymbologyUseCase;
import com.jobsity.usecase.impl.CalculateScoreForFramesUseCaseImpl;
import com.jobsity.usecase.impl.PopulateFramesUseCaseImpl;
import com.jobsity.usecase.impl.RetrieveFramesFromFileUseCaseImpl;
import com.jobsity.usecase.impl.SeparateFramesPerPlayerUseCaseImpl;
import com.jobsity.usecase.impl.SetPinfallSymbologyUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
public class IntegrationTest {

    private CommandLineService commandLineService;

    private PresentationService presentationService;

    @Spy
    private RetrieveFramesFromFileUseCase retrieveFramesFromFileUseCase;

    @Spy
    private PopulateFramesUseCase populateFramesUseCase;

    @Spy
    private SeparateFramesPerPlayerUseCase separateFramesPerPlayerUseCase;

    @Spy
    private CalculateScoreForFramesUseCase calculateScoreForFramesUseCase;

    @Spy
    private SetPinfallSymbologyUseCase setPinfallSymbologyUseCase;

    @BeforeEach
    public void init() {
        final PlayService playService = new PlayServiceImpl();
        final FrameService frameService = new FrameServiceImpl();
        final PlayerService playerService = new PlayerServiceImpl();

        retrieveFramesFromFileUseCase = new RetrieveFramesFromFileUseCaseImpl();
        populateFramesUseCase = new PopulateFramesUseCaseImpl(playService, frameService, playerService);
        separateFramesPerPlayerUseCase = new SeparateFramesPerPlayerUseCaseImpl();
        calculateScoreForFramesUseCase = new CalculateScoreForFramesUseCaseImpl(frameService);
        setPinfallSymbologyUseCase = new SetPinfallSymbologyUseCaseImpl(frameService);

        commandLineService = new CommandLineServiceImpl(retrieveFramesFromFileUseCase,
                populateFramesUseCase, separateFramesPerPlayerUseCase, calculateScoreForFramesUseCase, setPinfallSymbologyUseCase);

        presentationService = new PresentationServiceImpl();
    }

    @Test
    public void processFilePositivePerfect() throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("./positive/perfect.txt");
        File file = new File(Objects.requireNonNull(resource).toURI());

        Map<String, List<FrameDTO>> result = commandLineService.processFile(file);

        String scoreBoard = presentationService.setupSimpleScoreBoard(result);

        assertThat(scoreBoard).isEqualTo("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n" +
                "Carl\n" +
                "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\tX\tX\n" +
                "Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\n");
    }

    @Test
    public void processFilePositiveScores() throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("./positive/scores.txt");
        File file = new File(Objects.requireNonNull(resource).toURI());

        Map<String, List<FrameDTO>> result = commandLineService.processFile(file);

        String scoreBoard = presentationService.setupSimpleScoreBoard(result);

        assertThat(scoreBoard).isEqualTo("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n" +
                "Jeff\n" +
                "Pinfalls\t\tX\t7\t/\t9\t-\t\tX\t-\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1\n" +
                "Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\n" +
                "John\n" +
                "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t-\t7\t/\t4\t4\tX\t9\t-\n" +
                "Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151\n");
    }

    @Test
    public void processFileNegativeEmpty() throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("./negative/empty.txt");
        File file = new File(Objects.requireNonNull(resource).toURI());

        try {
            commandLineService.processFile(file);
            fail("An EmptyFileException must've been thrown here.");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(FrameFileException.class);
            assertThat(e.getCause()).isInstanceOf(EmptyFileException.class);
            assertThat(e.getMessage()).contains("This file is empty!");
        }
    }

    @Test
    public void processFileNegativeExtraScore() throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("./negative/extra-score.txt");
        File file = new File(Objects.requireNonNull(resource).toURI());

        try {
            commandLineService.processFile(file);
            fail("An ExtraScoreException must've been thrown here.");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(ExtraScoreException.class);
            assertThat(e.getMessage()).contains("This file has a extra-score for the player Carl, please remove that!");
        }
    }

    @Test
    public void processFileNegativeFreeText() throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("./negative/free-text.txt");
        File file = new File(Objects.requireNonNull(resource).toURI());

        try {
            commandLineService.processFile(file);
            fail("An InvalidFileException must've been thrown here.");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(FrameFileException.class);
            assertThat(e.getCause()).isInstanceOf(InvalidFileException.class);
            assertThat(e.getMessage()).contains("has a line (1) with content");
        }
    }

    @Test
    public void processFileNegativeInvalidScore() throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("./negative/invalid-score.txt");
        File file = new File(Objects.requireNonNull(resource).toURI());

        try {
            commandLineService.processFile(file);
            fail("An InvalidFileException must've been thrown here.");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(FrameFileException.class);
            assertThat(e.getCause()).isInstanceOf(InvalidFileException.class);
            assertThat(e.getMessage()).contains("has a line (2) with content (Carl\tlorem) that not matches a correct play.");
        }
    }

    @Test
    public void processFileNegativeNegative() throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("./negative/negative.txt");
        File file = new File(Objects.requireNonNull(resource).toURI());

        try {
            commandLineService.processFile(file);
            fail("An InvalidFileException must've been thrown here.");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(FrameFileException.class);
            assertThat(e.getCause()).isInstanceOf(InvalidFileException.class);
            assertThat(e.getMessage()).contains("has a line (2) with content (Carl\t-5) that not matches a correct play.");
        }
    }
}
