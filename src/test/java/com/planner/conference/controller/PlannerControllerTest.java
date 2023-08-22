package com.planner.conference.controller;

import com.planner.conference.model.dto.ConferenceDay;
import com.planner.conference.model.dto.ConferencePlan;
import com.planner.conference.service.PlannerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class PlannerControllerTest {

    @InjectMocks
    private PlannerController plannerController;

    @Mock
    private PlannerService plannerService;

    @Test
    public void makePlan() {
        plannerController.makePlan();

        verify(plannerService).makePlan();
    }

    @Test
    public void getDay() {
        when(plannerService.makePlan()).thenReturn(dummyConferencePlan());

        ConferenceDay response = plannerController.getDay(1);

        verify(plannerService).makePlan();
        assertNotNull(response);
    }

    @Test
    public void getDay_null() {
        when(plannerService.makePlan()).thenReturn(dummyConferencePlan());

        ConferenceDay response = plannerController.getDay(10);

        verify(plannerService).makePlan();
        assertNull(response);
    }

    private ConferencePlan dummyConferencePlan() {
        return new ConferencePlan(1000);
    }

}
