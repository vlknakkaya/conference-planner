package com.planner.conference.service;

import com.planner.conference.model.dto.Conference;
import com.planner.conference.model.entity.Presentation;
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
public class ConferenceServiceTest {

    @InjectMocks
    private ConferenceService conferenceService;

    @Mock
    private PresentationService presentationService;

    @Test
    public void makePlan() {
        when(presentationService.findAll()).thenReturn(dummyPresentations());

        Conference response = conferenceService.makePlan();

        assertNotNull(response);
        assertTrue(response.getTotalDays() > 0);
    }

    private List<Presentation> dummyPresentations() {
        // conferences from case example
        List<Presentation> presentationList = new ArrayList<>();
        presentationList.add(new Presentation("A", 60));
        presentationList.add(new Presentation("B", 45));
        presentationList.add(new Presentation("C", 30));
        presentationList.add(new Presentation("D", 45));
        presentationList.add(new Presentation("E", 45));
        presentationList.add(new Presentation("F", true));
        presentationList.add(new Presentation("G", 60));
        presentationList.add(new Presentation("H", 45));
        presentationList.add(new Presentation("I", 30));
        presentationList.add(new Presentation("J", 30));
        presentationList.add(new Presentation("K", 45));
        presentationList.add(new Presentation("L", 60));
        presentationList.add(new Presentation("M", 60));
        presentationList.add(new Presentation("N", 45));
        presentationList.add(new Presentation("O", 30));
        presentationList.add(new Presentation("P", 30));
        presentationList.add(new Presentation("R", 60));
        presentationList.add(new Presentation("S", 30));
        presentationList.add(new Presentation("T", 30));

        return presentationList;
    }

}
