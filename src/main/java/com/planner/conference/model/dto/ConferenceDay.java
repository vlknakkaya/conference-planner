package com.planner.conference.model.dto;

import com.planner.conference.model.entity.Presentation;
import com.planner.conference.util.Constants;

public class ConferenceDay {

    private int day;
    private final Session firstSession = new Session(Constants.SESSION_ONE_DURATION);
    private final Session secondSession = new Session(Constants.SESSION_TWO_DURATION);

    public ConferenceDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Session getFirstSession() {
        return firstSession;
    }

    public Session getSecondSession() {
        return secondSession;
    }

    public boolean addPresentation(Presentation presentation, int sessionNo) {
        return sessionNo == Constants.SESSION_ONE ? firstSession.addPresentation(presentation) : secondSession.addPresentation(presentation);
    }

    public boolean removePresentation(Presentation presentation, int sessionNo) {
        return sessionNo == Constants.SESSION_ONE ? firstSession.removePresentation(presentation) : secondSession.removePresentation(presentation);
    }

    public boolean addNetworking() {
        int duration = Integer.min(secondSession.getRemainDuration(), Constants.MAX_NETWORKING_DURATION);

        return secondSession.addPresentation(new Presentation(Constants.NETWORKING_NAME, duration));
    }

    @Override
    public String toString() {
        return "ConferenceDay{" +
                "day=" + day +
                ", firstSession=" + firstSession +
                ", secondSession=" + secondSession +
                '}';
    }

}
