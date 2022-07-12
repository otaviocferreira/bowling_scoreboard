package com.jobsity.domain.service.impl;

import com.jobsity.domain.dto.PlayDTO;
import com.jobsity.domain.dto.PlayerDTO;
import com.jobsity.domain.service.PlayerService;
import com.jobsity.domain.vo.PlayerVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@inheritDoc}
 */
public class PlayerServiceImpl implements PlayerService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PlayerDTO> separatePlaysOfPlayers(List<PlayDTO> mixedPlays) {
        List<PlayerDTO> separatedPlayers = new ArrayList<>();
        Map<String, List<String>> players = new HashMap<>();

        mixedPlays.forEach(play -> {
            if (!players.containsKey(play.getPlayerName())) {
                players.put(play.getPlayerName(), new ArrayList<>());
            }
            players.get(play.getPlayerName()).add(play.getPinFalls());
        });

        players.forEach((name, plays) -> separatedPlayers.add(PlayerVO.builder()
                .name(name)
                .plays(plays)
                .build()));

        return separatedPlayers;
    }
}
