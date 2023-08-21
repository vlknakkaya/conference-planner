package com.planner.conference.model.dto;

import com.planner.conference.model.entity.Conference;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private final List<Conference> conferenceList = new ArrayList<>();
    private final int totalDuration;
    private int remainDuration;

    public Session(int totalDuration) {
        this.totalDuration = totalDuration;
        this.remainDuration = totalDuration;
    }

    public List<Conference> getConferenceList() {
        return conferenceList;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public int getRemainDuration() {
        return remainDuration;
    }

    public boolean addConference(Conference conference) {
        if (remainDuration < conference.getDuration()) {
            return false;
        }

        conferenceList.add(conference);
        remainDuration -= conference.getDuration();

        return true;
    }

    public boolean removeConference(Conference conference) {
        if (!conferenceList.contains(conference)) {
            return false;
        }

        conferenceList.remove(conference);
        remainDuration += conference.getDuration();

        return true;
    }

    @Override
    public String toString() {
        return "Session{" +
                "conferenceList=" + conferenceList +
                ", totalDuration=" + totalDuration +
                ", remainDuration=" + remainDuration +
                '}';
    }

}
