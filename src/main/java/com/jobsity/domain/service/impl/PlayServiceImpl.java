package com.jobsity.domain.service.impl;

import com.jobsity.domain.dto.PlayDTO;
import com.jobsity.domain.service.PlayService;
import com.jobsity.domain.vo.PlayVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
public class PlayServiceImpl implements PlayService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PlayDTO> separatePLays(List<String> inputPLays) {
        return inputPLays.stream()
                .map(play -> {
                    String[] splittedPlays = play.split("\\t");
                    return PlayVO.builder()
                            .playerName(splittedPlays[0])
                            .pinFalls(splittedPlays[1])
                            .build();
                })
                .collect(Collectors.toList());
    }
}
