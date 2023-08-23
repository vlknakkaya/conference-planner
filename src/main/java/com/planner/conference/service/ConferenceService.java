package com.planner.conference.service;

import com.planner.conference.model.dto.Conference;
import com.planner.conference.model.dto.ConferenceDay;
import com.planner.conference.model.dto.Session;
import com.planner.conference.model.entity.Presentation;
import com.planner.conference.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConferenceService {

    private final PresentationService presentationService;

    @Autowired
    public ConferenceService(PresentationService presentationService) {
        this.presentationService = presentationService;
    }

    public String planAsString() {
        Conference conference = makePlan();

        StringBuilder stringBuilder = new StringBuilder("- CONFERENCE PLAN -");
        stringBuilder.append(Constants.NEW_LINE);

        for (ConferenceDay conferenceDay : conference.getConferenceDays()) {
            stringBuilder.append("- DAY-" + conferenceDay.getDay());
            stringBuilder.append(Constants.NEW_LINE);

            int time = Constants.START_DURATION;

            Session session = conferenceDay.getFirstSession();
            for (Presentation presentation : session.getPresentationList()) {
                stringBuilder.append(minToTime(time) + " " + presentation.getName() + " [" + presentation.getDuration() + "min]");
                stringBuilder.append(Constants.NEW_LINE);
                time += presentation.getDuration();
            }

            stringBuilder.append("12:00PM Lunch");
            stringBuilder.append(Constants.NEW_LINE);
            time += 60;

            session = conferenceDay.getSecondSession();
            for (Presentation presentation : session.getPresentationList()) {
                if (Constants.NETWORKING_NAME.equals(presentation.getName()) && presentation.getDuration() == 60) {
                    stringBuilder.append("04:00PM " + Constants.NETWORKING_NAME + " [60min]");
                    continue;
                }
                stringBuilder.append(minToTime(time) + " " + presentation.getName() + " [" + presentation.getDuration() + "min]");
                stringBuilder.append(Constants.NEW_LINE);
                time += presentation.getDuration();
            }

            stringBuilder.append(Constants.NEW_LINE);
        }

        return stringBuilder.toString();
    }

    public Conference makePlan() {
        List<Presentation> presentationList = presentationService.findAll();
        int totalDuration = presentationList.stream().mapToInt(Presentation::getDuration).sum();

        Conference conference = new Conference(totalDuration);

        List<Session> firstSessions = conference.getConferenceDays().stream().map(ConferenceDay::getFirstSession).collect(Collectors.toList());
        List<Session> secondSessions = conference.getConferenceDays().stream().map(ConferenceDay::getSecondSession).collect(Collectors.toList());

        for (Presentation presentation : presentationList) {
            boolean isAdded = false;

            for (int i=0; i < firstSessions.size(); i++) {
                isAdded = firstSessions.get(i).addPresentation(presentation);
                if (isAdded) {
                    break;
                }
            }

            if (isAdded) {
                continue;
            }

            for (int i=0; i < secondSessions.size(); i++) {
                isAdded = secondSessions.get(i).addPresentation(presentation);
                if (isAdded) {
                    break;
                }
            }
        }

        if (firstSessions.stream().anyMatch(x -> x.getRemainDuration() > 0)) {
            relocate(conference);
        }

        if (secondSessions.stream().anyMatch(x -> x.getRemainDuration() > 0)) {
            addNetworking(conference);
        }

        return conference;
    }

    private void relocate(Conference conference) {
        for (ConferenceDay conferenceDay : conference.getConferenceDays()) {
            int firstSessionRemainCapacity = conferenceDay.getFirstSession().getRemainDuration();
            if (firstSessionRemainCapacity > 0) {
                List<Presentation> firstSessionPresentations = new ArrayList<>(conferenceDay.getFirstSession().getPresentationList());
                List<Presentation> secondSessionPresentations = new ArrayList<>(conferenceDay.getSecondSession().getPresentationList());

                Collections.sort(firstSessionPresentations);
                Collections.sort(secondSessionPresentations);

                boolean done = false;

                for (int i = 0; i < firstSessionPresentations.size(); i++) {
                    for (int j = 0; j < secondSessionPresentations.size(); j++) {
                        Presentation presentationInFirst = firstSessionPresentations.get(i);
                        Presentation presentationInSecond = secondSessionPresentations.get(j);
                        if (presentationInFirst.getDuration() + firstSessionRemainCapacity == presentationInSecond.getDuration()) {
                            done = conferenceDay.removePresentation(presentationInFirst, Constants.SESSION_ONE) &&
                                    conferenceDay.addPresentation(presentationInSecond, Constants.SESSION_ONE) &&
                                    conferenceDay.removePresentation(presentationInSecond, Constants.SESSION_TWO) &&
                                    conferenceDay.addPresentation(presentationInFirst, Constants.SESSION_TWO);
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

    private void addNetworking(Conference conference) {
        for (ConferenceDay conferenceDay : conference.getConferenceDays()) {
            conferenceDay.addNetworking();
        }
    }

    private String minToTime(int startTime) {
        int hour = (startTime / 60) % 12;
        int minute = startTime % 60;
        String meridiem = startTime / 60 < 12 ? "AM" : "PM";

        return (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + meridiem;
    }

}
