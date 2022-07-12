package com.jobsity.infrastructure;

import com.jobsity.domain.dto.FrameDTO;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * This class represents the utilization from a commandline calls.
 *
 * @since 1.0.0
 * @author Otavio Ferreira
 */
public interface CommandLineService {

    /**
     * This method starts a process using a file sent by a commandline call.
     *
     * @param file A {@link File} sent by a command line call.
     * @return A processed {@link Map} that contains all the correct frames by player.
     *
     * @since 1.0.0
     */
    Map<String, List<FrameDTO>> processFile(File file);
}
