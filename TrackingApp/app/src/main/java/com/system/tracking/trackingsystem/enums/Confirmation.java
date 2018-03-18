package com.system.tracking.trackingsystem.enums;

/**
 * Created by Milton on 18/03/2018.
 */

public enum Confirmation {

    NO(0, "Não"),
    YES(1, "Sim"),
    NO_ANSWER(2, "Sem resposta");

    private Integer code;
    private String description;

    private Confirmation(Integer code, String description) {
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
