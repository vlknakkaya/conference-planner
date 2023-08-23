package com.planner.conference.model.dto;

import com.planner.conference.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class ConferencePlan {

    private final List<ConferenceDay> conferenceDays = new ArrayList<>();

    public ConferencePlan(int totalDuration) {
        int days = calculateNeededDays(totalDuration);

        for (int i = 0; i < days; i++) {
            conferenceDays.add(new ConferenceDay(i+1));
        }
    }

    public List<ConferenceDay> getConferenceDays() {
        return conferenceDays;
    }

    public int getTotalDays() {
        return conferenceDays.size();
    }

    public ConferenceDay getConferenceDay(int day) {
        return conferenceDays.get(day-1);
    }

    private int calculateNeededDays(int totalDuration) {
        if (totalDuration == 0) {
            return 0;
        }

        int dayTotalDuration = Constants.SESSION_ONE_DURATION + Constants.SESSION_TWO_DURATION;

        return (totalDuration / dayTotalDuration) + 1;
    }

    @Override
    public String toString() {
        return "ConferencePlan{" +
                "conferenceDays=" + conferenceDays +
                '}';
    }

}
