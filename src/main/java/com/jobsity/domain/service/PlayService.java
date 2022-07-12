package com.jobsity.domain.service;

import com.jobsity.domain.dto.PlayDTO;

import java.util.List;

/**
 * This class is responsible for all interaction with PlayDTO.
 *
 * @see PlayDTO
 * @since 1.0.0
 * @author Otavio Ferreira
 */
public interface PlayService {

    /**
     * Method for separate all plays from a list that comes from an external file.
     *
     * @param inputPLays A list of inputted plays from an external file.
     * @return A list of {@link PlayDTO} that contains the separated plays.
     *
     * @since 1.0.0
     */
    List<PlayDTO> separatePLays(List<String> inputPLays);
}
