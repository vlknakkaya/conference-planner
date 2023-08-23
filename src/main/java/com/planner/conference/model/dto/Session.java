package com.planner.conference.model.dto;

import com.planner.conference.model.entity.Presentation;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private final List<Presentation> presentationList = new ArrayList<>();
    private final int totalDuration;
    private int remainDuration;

    public Session(int totalDuration) {
        this.totalDuration = totalDuration;
        this.remainDuration = totalDuration;
    }

    public List<Presentation> getPresentationList() {
        return presentationList;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public int getRemainDuration() {
        return remainDuration;
    }

    public boolean addPresentation(Presentation presentation) {
        if (remainDuration < presentation.getDuration()) {
            return false;
        }

        presentationList.add(presentation);
        remainDuration -= presentation.getDuration();

        return true;
    }

    public boolean removePresentation(Presentation presentation) {
        if (!presentationList.contains(presentation)) {
            return false;
        }

        presentationList.remove(presentation);
        remainDuration += presentation.getDuration();

        return true;
    }

    @Override
    public String toString() {
        return "Session{" +
                "presentationList=" + presentationList +
                ", totalDuration=" + totalDuration +
                ", remainDuration=" + remainDuration +
                '}';
    }

}
