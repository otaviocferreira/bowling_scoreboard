package com.jobsity.usecase.impl;

import com.jobsity.exception.EmptyFileException;
import com.jobsity.exception.FrameFileException;
import com.jobsity.exception.InvalidFileException;
import com.jobsity.usecase.RetrieveFramesFromFileUseCase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * {@inheritDoc}
 */
public class RetrieveFramesFromFileUseCaseImpl implements RetrieveFramesFromFileUseCase {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> execute(File fileWithPLays) {
        try {
            validateFile(fileWithPLays);
            return Files.readAllLines(Paths.get(fileWithPLays.toURI()));
        } catch (Exception e) {
            throw new FrameFileException(String.format("An error occurred trying to read the file: %s", e.getMessage()), e);
        }
    }

    /**
     * This method valid all lines from a file.
     * <p>
     * Here, we need to use the below regex to validate each line:<p>
     * <b>\w{3,}\t(1?0|\d|F)</b>
     *
     * @param fileWithPLays A {@link File} That contains all the plays in a game.
     * @throws IOException          If the file has some problem with its lines.
     * @throws EmptyFileException   If the file is an empty file.
     * @throws InvalidFileException If any line do not match with the above regex.
     * @see Pattern
     * @since 1.0.0
     */
    private void validateFile(File fileWithPLays) throws IOException {

        try (Stream<String> lines = Files.lines(Paths.get(fileWithPLays.toURI()))) {
            if (!lines.findAny().isPresent()) {
                throw new EmptyFileException("This file is empty!");
            }
        }

        Pattern p = Pattern.compile("\\w{3,}\\t(1?0|\\d|F)");

        AtomicInteger lineNumber = new AtomicInteger(0);

        try (Stream<String> lines = Files.lines(Paths.get(fileWithPLays.toURI()))) {

            lines.filter(line -> {
                        lineNumber.incrementAndGet();
                        CharSequence sequence = line.subSequence(0, line.length());
                        return !p.matcher(sequence).matches();
                    })
                    .findAny()
                    .ifPresent(s -> {
                        throw new InvalidFileException(
                                String.format("The file (%s) has a line (%d) with content (%s) that not matches a correct play.",
                                        fileWithPLays.getAbsolutePath(),
                                        lineNumber.get(),
                                        s));
                    });
        }
    }
}
