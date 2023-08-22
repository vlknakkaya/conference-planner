package com.planner.conference.controller;

import com.planner.conference.model.converter.ConferenceConverter;
import com.planner.conference.model.dto.ConferenceDTO;
import com.planner.conference.model.entity.Conference;
import com.planner.conference.service.ConferenceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ConferenceControllerTest {

    @InjectMocks
    private ConferenceController conferenceController;

    @Mock
    private ConferenceService conferenceService;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private ConferenceConverter conferenceConverter;

    @Test
    public void getConference() {
        Conference dummyConference = dummyConference();
        when(conferenceService.findById(anyLong())).thenReturn(dummyConference);

        ConferenceDTO response = conferenceController.getConference(anyLong());

        verify(conferenceConverter).convertToDTO(any());
        verify(conferenceService).findById(anyLong());
        assertNotNull(response);
        assertEquals(dummyConference.getName(), response.getName());
        assertEquals(dummyConference.getDuration(), response.getDuration());
    }

    @Test
    public void getAllConferences() {
        List<Conference> dummyConferences = Collections.singletonList(dummyConference());
        when(conferenceService.findAll()).thenReturn(dummyConferences);

        List<ConferenceDTO> response = conferenceController.getAllConferences();

        verify(conferenceConverter).convertToDTOList(anyList());
        verify(conferenceService).findAll();
        assertNotNull(response);
        assertEquals(dummyConferences.size(), response.size());
        assertEquals(dummyConferences.get(0).getName(), response.get(0).getName());
        assertEquals(dummyConferences.get(0).getDuration(), response.get(0).getDuration());
    }

    @Test
    public void createConference() {
        Conference dummyConference = dummyConference();
        when(conferenceService.save(any())).thenReturn(dummyConference);

        ResponseEntity<String> response = conferenceController.createConference(any());

        verify(conferenceConverter).convertToEntity(any());
        verify(conferenceService).save(any());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void createConferences() {
        List<Conference> dummyConferences = Collections.singletonList(dummyConference());
        when(conferenceService.saveAll(anyList())).thenReturn(dummyConferences);

        ResponseEntity<String> response = conferenceController.createConferences(anyList());

        verify(conferenceConverter).convertToEntityList(anyList());
        verify(conferenceService).saveAll(anyList());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updateConference() {
        Conference dummyConference = dummyConference();
        ConferenceDTO dummyConferenceDTO = dummyConferenceDTO();
        dummyConferenceDTO.setLightning(true);

        when(conferenceService.findById(anyLong())).thenReturn(dummyConference);
        when(conferenceService.save(any())).thenReturn(dummyConference);

        ResponseEntity<String> response = conferenceController.updateConference(anyLong(), dummyConferenceDTO);

        verify(conferenceService).save(any());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void removeConference() {
        ResponseEntity<String> response = conferenceController.removeConference(anyLong());

        verify(conferenceService).remove(any());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void removeAllConferences() {
        ResponseEntity<String> response = conferenceController.removeAllConferences();

        verify(conferenceService).removeAll();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private Conference dummyConference() {
        return new Conference("A", 60);
    }

    private ConferenceDTO dummyConferenceDTO() {
        return new ConferenceDTO("B", 90);
    }

}
