package com.planner.conference.controller;

import com.planner.conference.exception.DayNumberExceededException;
import com.planner.conference.model.dto.ConferenceDay;
import com.planner.conference.model.dto.Conference;
import com.planner.conference.service.ConferenceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ConferenceControllerTest {

    @InjectMocks
    private ConferenceController conferenceController;

    @Mock
    private ConferenceService conferenceService;

    @Test
    public void makePlan() {
        conferenceController.makePlan();

        verify(conferenceService).makePlan();
    }

    @Test
    public void getDay() {
        when(conferenceService.makePlan()).thenReturn(dummyConference());

        ConferenceDay response = conferenceController.getDay(1);

        verify(conferenceService).makePlan();
        assertNotNull(response);
    }

    @Test
    public void getDay_null() {
        when(conferenceService.makePlan()).thenReturn(dummyConference());

        assertThrows(DayNumberExceededException.class, () -> conferenceController.getDay(10));
    }

    private Conference dummyConference() {
        return new Conference(1000);
    }

}
