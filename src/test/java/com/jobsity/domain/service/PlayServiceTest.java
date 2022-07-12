package com.jobsity.domain.service;

import com.jobsity.domain.dto.PlayDTO;
import com.jobsity.domain.service.impl.PlayServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayServiceTest {

    final PlayService playService = new PlayServiceImpl();

    @Test
    public void separatePlaysOfPlayers() {

        List<String> plays = Arrays.asList("Carl	2", "Carl	3", "Carl	4", "Carl	2", "Carl	2",
                "Carl	8","Carl	2", "Carl	F", "Carl	9", "Carl	10", "Carl	7", "Carl	3",
                "Carl	9", "Carl	0", "Carl	10", "Carl	10", "Carl	8");

        List<PlayDTO> resultedPlays = playService.separatePLays(plays);

        assertThat(resultedPlays).isNotNull();
        assertThat(resultedPlays.size()).isEqualTo(17);
        assertThat(resultedPlays.get(0).getPlayerName()).isEqualTo("Carl");
        assertThat(resultedPlays.get(1).getPinFalls()).isEqualTo("3");
        assertThat(resultedPlays.get(13).getPinFalls()).isEqualTo("0");
    }
}
