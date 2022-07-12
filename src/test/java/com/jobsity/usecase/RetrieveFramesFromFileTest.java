package com.jobsity.usecase;

import com.jobsity.exception.EmptyFileException;
import com.jobsity.exception.FrameFileException;
import com.jobsity.exception.InvalidFileException;
import com.jobsity.usecase.impl.RetrieveFramesFromFileUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
public class RetrieveFramesFromFileTest {

    private final RetrieveFramesFromFileUseCase retrieveFramesFromFileUseCase = new RetrieveFramesFromFileUseCaseImpl();

    @Test
    public void execute() throws URISyntaxException {

        URL resource = getClass().getClassLoader().getResource("./positive/scores.txt");
        File file = new File(Objects.requireNonNull(resource).toURI());

        List<String> lines = retrieveFramesFromFileUseCase.execute(file);

        assertThat(lines).isNotNull();
        assertThat(lines.size()).isEqualTo(35);
    }

    @Test
    public void executeWithExtraScore() throws URISyntaxException {

        URL resource = getClass().getClassLoader().getResource("./negative/invalid-score.txt");
        File file = new File(Objects.requireNonNull(resource).toURI());

        try {
            retrieveFramesFromFileUseCase.execute(file);
            fail("An InvalidFileException must've been thrown here.");
        } catch(Exception e) {
            assertThat(e).isInstanceOf(FrameFileException.class);
            assertThat(e.getCause()).isInstanceOf(InvalidFileException.class);
            assertThat(e.getMessage()).contains("has a line (2) with content (Carl\tlorem) that not matches a correct play");
        }
    }

    @Test
    public void executeWithEmptyFile() throws URISyntaxException {

        URL resource = getClass().getClassLoader().getResource("./negative/empty.txt");
        File file = new File(Objects.requireNonNull(resource).toURI());

        try {
            retrieveFramesFromFileUseCase.execute(file);
            fail("An EmptyFileException must've been thrown here.");
        } catch(Exception e) {
            assertThat(e).isInstanceOf(FrameFileException.class);
            assertThat(e.getCause()).isInstanceOf(EmptyFileException.class);
            assertThat(e.getMessage()).contains("This file is empty!");
        }
    }

    @Test
    public void executeWithNoFile() {
        try {
            retrieveFramesFromFileUseCase.execute(null);
            fail("An FrameFileException must've been thrown here.");
        } catch(Exception e) {
            assertThat(e).isInstanceOf(FrameFileException.class);
            assertThat(e.getMessage()).contains("An error occurred trying to read the file");
        }
    }
}
