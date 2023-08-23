package com.planner.conference.model.entity;

import com.planner.conference.util.Constants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class Presentation implements Comparable<Presentation> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer duration;
    private Boolean lightning;

    public Presentation() {
    }

    public Presentation(String name, Integer duration, Boolean lightning) {
        this.name = name;
        this.duration = lightning != null && lightning.booleanValue() ? Constants.LIGHTNING_DURATION : duration;
        this.lightning = lightning;
    }

    public Presentation(String name, Integer duration) {
        this(name, duration, false);
    }

    public Presentation(String name, Boolean lightning) {
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
    public int compareTo(Presentation o) {
        return Integer.compare(this.duration, o.duration);
    }

    @Override
    public String toString() {
        return "Presentation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", lightning=" + lightning +
                '}';
    }

}
