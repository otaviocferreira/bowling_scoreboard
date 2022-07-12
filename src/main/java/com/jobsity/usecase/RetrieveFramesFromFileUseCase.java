package com.jobsity.usecase;

import java.io.File;
import java.util.List;

/**
 * This use case uses the frames sent by file, transforming them into a {@link String} list, where each element is a line in the file.
 *
 * @see File
 * @since 1.0.0
 * @author Otavio Ferreira
 */
public interface RetrieveFramesFromFileUseCase {

    /**
     * Execute the use case.
     *
     * @param fileWithPLays A {@link File} That contains all the plays in a game.
     * @return A {@link String} list created using the file lines.
     *
     * @since 1.0.0
     */
    List<String> execute(File fileWithPLays);
}
