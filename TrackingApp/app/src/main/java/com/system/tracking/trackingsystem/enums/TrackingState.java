package com.system.tracking.trackingsystem.enums;

/**
 * Created by Milton on 18/03/2018.
 */

public enum TrackingState {

    SAFE(0, "Seguro"),
    DANGEOURS(1, "Perigo"),
    TRANCKING(2, "Monitoramento/Assalto");

    private Integer code;
    private String description;

    private TrackingState(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
