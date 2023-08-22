package com.planner.conference.model.entity;

import com.planner.conference.util.Constants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conference implements Comparable<Conference> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int duration;
    private boolean lightning;

    public Conference() {
    }

    public Conference(String name, int duration, boolean lightning) {
        this.name = name;
        this.duration = lightning ? Constants.LIGHTNING_DURATION : duration;
        this.lightning = lightning;
    }

    public Conference(String name, int duration) {
        this(name, duration, false);
    }

    public Conference(String name, boolean lightning) {
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
