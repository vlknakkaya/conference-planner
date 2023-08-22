package com.planner.conference.model.dto;

import com.planner.conference.util.Constants;

public class ConferenceDTO {

    private long id;
    private String name;
    private int duration;

    private boolean lightning;

    public ConferenceDTO() {
    }

    public ConferenceDTO(long id, String name, int duration, boolean lightning) {
        this.id = id;
        this.name = name;
        this.duration = lightning ? Constants.LIGHTNING_DURATION : duration;
        this.lightning = lightning;
    }

    public ConferenceDTO(String name, int duration, boolean lightning) {
        this.name = name;
        this.duration = lightning ? Constants.LIGHTNING_DURATION : duration;
        this.lightning = lightning;
    }

    public ConferenceDTO(String name, int duration) {
        this(name, duration, false);
    }

    public ConferenceDTO(String name, boolean lightning) {
        this(name, Constants.LIGHTNING_DURATION, lightning);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isLightning() {
        return lightning;
    }

    public void setLightning(boolean lightning) {
        this.lightning = lightning;
    }

    @Override
    public String toString() {
        return "ConferenceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", lightning=" + lightning +
                '}';
    }

}
