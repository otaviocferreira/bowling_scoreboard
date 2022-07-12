package com.jobsity.domain.vo;

import com.jobsity.domain.dto.PlayerDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Builder
public class PlayerVO implements PlayerDTO {

    private String name;

    @Setter(AccessLevel.PRIVATE)
    private List<String> plays;

    @Override
    public List<String> getPlays() {
        return Collections.unmodifiableList(plays);
    }
}
