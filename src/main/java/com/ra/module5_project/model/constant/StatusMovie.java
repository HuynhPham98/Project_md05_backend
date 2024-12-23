package com.ra.module5_project.model.constant;

import lombok.Getter;

@Getter
public enum StatusMovie {
    NOW_SHOWING("Đang chiếu"),
    COMING_SOON("Sắp chiếu"),
    STOPPED_SHOWING("Đã ngừng chiếu");

    private final String description;

    StatusMovie(String description) {
        this.description = description;
    }

}
