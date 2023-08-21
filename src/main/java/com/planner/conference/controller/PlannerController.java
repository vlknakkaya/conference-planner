package com.planner.conference.controller;

import com.planner.conference.model.dto.ConferenceDay;
import com.planner.conference.model.dto.ConferencePlan;
import com.planner.conference.service.PlannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planner")
public class PlannerController {

    private final PlannerService plannerService;

    @Autowired
    public PlannerController(PlannerService plannerService) {
        this.plannerService = plannerService;
    }

    @GetMapping
    public ConferencePlan makePlan() {
        return plannerService.makePlan();
    }

    @GetMapping("/{day}")
    public ConferenceDay getDay(@PathVariable int day) {
        ConferencePlan conferencePlan = plannerService.makePlan();
        if (conferencePlan.getTotalDays() < day) {
            return null;
        }

        return conferencePlan.getConferenceDay(day);
    }

}
