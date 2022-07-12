package com.jobsity.domain.service;

import com.jobsity.domain.dto.PlayerDTO;
import com.jobsity.domain.service.impl.PlayerServiceImpl;
import com.jobsity.domain.vo.PlayVO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerServiceTest {

    final PlayerService playerService = new PlayerServiceImpl();

    @Test
    public void separatePlaysOfPlayers() {
        PlayVO play1 = PlayVO.builder().playerName("Otavio").pinFalls("2").build();
        PlayVO play2 = PlayVO.builder().playerName("Otavio").pinFalls("5").build();
        PlayVO play3 = PlayVO.builder().playerName("Jeff").pinFalls("10").build();
        PlayVO play4 = PlayVO.builder().playerName("Otavio").pinFalls("10").build();
        PlayVO play5 = PlayVO.builder().playerName("Jeff").pinFalls("2").build();
        PlayVO play6 = PlayVO.builder().playerName("Jeff").pinFalls("8").build();

        List<PlayerDTO> players = playerService.separatePlaysOfPlayers(Arrays.asList(play1, play2, play3, play4, play5, play6));

        assertThat(players).isNotNull();
        assertThat(players.size()).isEqualTo(2);
        assertThat(players.get(0).getPlays().size()).isEqualTo(3);
        assertThat(players.get(1).getPlays().size()).isEqualTo(3);
    }
}
