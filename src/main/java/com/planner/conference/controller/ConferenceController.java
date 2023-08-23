package com.planner.conference.controller;

import com.planner.conference.exception.DayNumberExceededException;
import com.planner.conference.model.dto.Conference;
import com.planner.conference.model.dto.ConferenceDay;
import com.planner.conference.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conference")
public class ConferenceController {

    private final ConferenceService conferenceService;

    @Autowired
    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping
    public Conference makePlan() {
        return conferenceService.makePlan();
    }

    @GetMapping("/{day}")
    public ConferenceDay getDay(@PathVariable int day) {
        Conference conference = conferenceService.makePlan();
        if (conference.getTotalDays() < day) {
            throw new DayNumberExceededException(conference.getTotalDays(), day);
        }

        return conference.getConferenceDay(day);
    }

    @GetMapping("/print")
    public String printPlan() {
        return conferenceService.planAsString();
    }

}
