package com.planner.conference.model.entity;

import com.planner.conference.util.Constants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Conference implements Comparable<Conference> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Integer duration;
    private Boolean lightning;

    public Conference() {
    }

    public Conference(String name, Integer duration, Boolean lightning) {
        this.name = name;
        this.duration = lightning ? Constants.LIGHTNING_DURATION : duration;
        this.lightning = lightning;
    }

    public Conference(String name, Integer duration) {
        this(name, duration, false);
    }

    public Conference(String name, Boolean lightning) {
        this(name, Constants.LIGHTNING_DURATION, lightning);
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
    public int compareTo(Conference o) {
        return Integer.compare(this.duration, o.duration);
    }

    @Override
    public String toString() {
        return "Conference{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", lightning=" + lightning +
                '}';
    }

}
