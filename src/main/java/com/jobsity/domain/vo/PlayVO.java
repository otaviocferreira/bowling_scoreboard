package com.jobsity.domain.vo;

import com.jobsity.domain.dto.PlayDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlayVO implements PlayDTO {

    private String playerName;
    private String pinFalls;
}
