package com.jobsity.domain.service;

import com.jobsity.domain.dto.PlayDTO;
import com.jobsity.domain.dto.PlayerDTO;

import java.util.List;

/**
 * This class is responsible for all interaction with PlayerDTO.
 *
 * @see PlayerDTO
 * @since 1.0.0
 * @author Otavio Ferreira
 */
public interface PlayerService {

    /**
     * Method responsible for separating all moves from a list of {@link PlayDTO} by player who performed them.
     *
     * @param mixedPlays A list of {@link PlayDTO} containing all plays for all players.
     * @return A list of {@link PlayerDTO} containing the players and them plays.
     *
     * @since 1.0.0
     */
    List<PlayerDTO> separatePlaysOfPlayers(List<PlayDTO> mixedPlays);
}
