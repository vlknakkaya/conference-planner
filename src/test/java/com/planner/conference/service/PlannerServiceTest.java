package com.planner.conference.service;

import com.planner.conference.model.dto.ConferencePlan;
import com.planner.conference.model.entity.Conference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class PlannerServiceTest {

    @InjectMocks
    private PlannerService plannerService;

    @Mock
    private ConferenceService conferenceService;

    @Test
    public void makePlan() {
        when(conferenceService.findAll()).thenReturn(dummyConferences());

        ConferencePlan response = plannerService.makePlan();

        assertNotNull(response);
        assertTrue(response.getTotalDays() > 0);
    }

    private List<Conference> dummyConferences() {
        // conferences from case example
        List<Conference> conferenceList = new ArrayList<>();
        conferenceList.add(new Conference("A", 60));
        conferenceList.add(new Conference("B", 45));
        conferenceList.add(new Conference("C", 30));
        conferenceList.add(new Conference("D", 45));
        conferenceList.add(new Conference("E", 45));
        conferenceList.add(new Conference("F", true));
        conferenceList.add(new Conference("G", 60));
        conferenceList.add(new Conference("H", 45));
        conferenceList.add(new Conference("I", 30));
        conferenceList.add(new Conference("J", 30));
        conferenceList.add(new Conference("K", 45));
        conferenceList.add(new Conference("L", 60));
        conferenceList.add(new Conference("M", 60));
        conferenceList.add(new Conference("N", 45));
        conferenceList.add(new Conference("O", 30));
        conferenceList.add(new Conference("P", 30));
        conferenceList.add(new Conference("R", 60));
        conferenceList.add(new Conference("S", 30));
        conferenceList.add(new Conference("T", 30));

        return conferenceList;
    }

}
