package com.planner.conference.model.dto;

import com.planner.conference.util.Constants;

public class PresentationDTO {

    private Long id;
    private String name;
    private Integer duration;

    private Boolean lightning;

    public PresentationDTO() {
    }

    public PresentationDTO(Long id, String name, Integer duration, Boolean lightning) {
        this.id = id;
        this.name = name;
        this.duration = lightning != null && lightning.booleanValue() ? Constants.LIGHTNING_DURATION : duration;
        this.lightning = lightning;
    }

    public PresentationDTO(String name, Integer duration, Boolean lightning) {
        this.name = name;
        this.duration = lightning != null && lightning.booleanValue() ? Constants.LIGHTNING_DURATION : duration;
        this.lightning = lightning;
    }

    public PresentationDTO(String name, Boolean lightning) {
        this(name, Constants.LIGHTNING_DURATION, lightning);
    }

    public PresentationDTO(String name, Integer duration) {
        this(name, duration, false);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getLightning() {
        return lightning;
    }

    public void setLightning(Boolean lightning) {
        this.lightning = lightning;
    }

    @Override
    public String toString() {
        return "PresentationDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", lightning=" + lightning +
                '}';
    }

}
