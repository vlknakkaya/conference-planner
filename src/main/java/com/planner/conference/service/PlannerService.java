package com.planner.conference.service;

import com.planner.conference.model.dto.ConferencePlan;
import com.planner.conference.model.dto.ConferenceDay;
import com.planner.conference.model.dto.Session;
import com.planner.conference.model.entity.Conference;
import com.planner.conference.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlannerService {

    private final ConferenceService conferenceService;

    @Autowired
    public PlannerService(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    public ConferencePlan makePlan() {
        List<Conference> conferenceList = conferenceService.findAll();
        int totalDuration = conferenceList.stream().mapToInt(Conference::getDuration).sum();

        ConferencePlan conferencePlan = new ConferencePlan(totalDuration);

        List<Session> firstSessions = conferencePlan.getConferenceDays().stream().map(ConferenceDay::getFirstSession).collect(Collectors.toList());
        List<Session> secondSessions = conferencePlan.getConferenceDays().stream().map(ConferenceDay::getSecondSession).collect(Collectors.toList());

        for (Conference conference : conferenceList) {
            boolean isAdded = false;

            for (int i=0; i < firstSessions.size(); i++) {
                isAdded = firstSessions.get(i).addConference(conference);
                if (isAdded) {
                    break;
                }
            }

            if (isAdded) {
                continue;
            }

            for (int i=0; i < secondSessions.size(); i++) {
                isAdded = secondSessions.get(i).addConference(conference);
                if (isAdded) {
                    break;
                }
            }
        }

        if (firstSessions.stream().anyMatch(x -> x.getRemainDuration() > 0)) {
            relocate(conferencePlan);
        }

        if (secondSessions.stream().anyMatch(x -> x.getRemainDuration() > 0)) {
            addNetworking(conferencePlan);
        }

        return conferencePlan;
    }

    private void relocate(ConferencePlan conferencePlan) {
        for (ConferenceDay conferenceDay : conferencePlan.getConferenceDays()) {
            int firstSessionRemainCapacity = conferenceDay.getFirstSession().getRemainDuration();
            if (firstSessionRemainCapacity > 0) {
                List<Conference> firstSessionConferences = new ArrayList<>(conferenceDay.getFirstSession().getConferenceList());
                List<Conference> secondSessionConferences = new ArrayList<>(conferenceDay.getSecondSession().getConferenceList());

                Collections.sort(firstSessionConferences);
                Collections.sort(secondSessionConferences);

                boolean done = false;

                for (int i = 0; i < firstSessionConferences.size(); i++) {
                    for (int j = 0; j < secondSessionConferences.size(); j++) {
                        Conference conferenceInFirst = firstSessionConferences.get(i);
                        Conference conferenceInSecond = secondSessionConferences.get(j);
                        if (conferenceInFirst.getDuration() + firstSessionRemainCapacity == conferenceInSecond.getDuration()) {
                            done = conferenceDay.removeConference(conferenceInFirst, Constants.SESSION_ONE) &&
                                    conferenceDay.addConference(conferenceInSecond, Constants.SESSION_ONE) &&
                                    conferenceDay.removeConference(conferenceInSecond, Constants.SESSION_TWO) &&
                                    conferenceDay.addConference(conferenceInFirst, Constants.SESSION_TWO);
                            break;
                        }
                    }

                    if (done) {
                        break;
                    }
                }
            }
        }
    }

    private void addNetworking(ConferencePlan conferencePlan) {
        for (ConferenceDay conferenceDay : conferencePlan.getConferenceDays()) {
            conferenceDay.addNetworking();
        }
    }

}
