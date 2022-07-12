package com.jobsity.domain.vo;

import com.jobsity.domain.dto.FrameDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class FrameVO implements FrameDTO {

    private Integer order;
    private String playerName;
    private String firstPinFall;
    private String secondPinFall;
    private String thirdPinFall;
    private Integer score;
}
