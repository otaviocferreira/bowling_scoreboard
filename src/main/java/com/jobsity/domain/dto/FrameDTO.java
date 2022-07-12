package com.jobsity.domain.dto;

public interface FrameDTO {

    Integer getOrder();

    String getPlayerName();

    String getFirstPinFall();

    void setFirstPinFall(String firstPinFall);

    String getSecondPinFall();

    void setSecondPinFall(String secondPinFall);

    String getThirdPinFall();

    void setThirdPinFall(String secondPinFall);

    Integer getScore();

    void setScore(Integer score);
}
