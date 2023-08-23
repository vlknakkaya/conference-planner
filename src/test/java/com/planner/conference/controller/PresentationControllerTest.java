package com.planner.conference.controller;

import com.planner.conference.model.converter.PresentationConverter;
import com.planner.conference.model.dto.PresentationDTO;
import com.planner.conference.model.entity.Presentation;
import com.planner.conference.service.PresentationService;
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
public class PresentationControllerTest {

    @InjectMocks
    private PresentationController presentationController;

    @Mock
    private PresentationService presentationService;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private PresentationConverter presentationConverter;

    @Test
    public void getPresentation() {
        Presentation dummyPresentation = dummyPresentation();
        when(presentationService.findById(anyLong())).thenReturn(dummyPresentation);

        PresentationDTO response = presentationController.getPresentation(anyLong());

        verify(presentationConverter).convertToDTO(any());
        verify(presentationService).findById(anyLong());
        assertNotNull(response);
        assertEquals(dummyPresentation.getName(), response.getName());
        assertEquals(dummyPresentation.getDuration(), response.getDuration());
    }

    @Test
    public void getAllPresentations() {
        List<Presentation> dummyPresentations = Collections.singletonList(dummyPresentation());
        when(presentationService.findAll()).thenReturn(dummyPresentations);

        List<PresentationDTO> response = presentationController.getAllPresentations();

        verify(presentationConverter).convertToDTOList(anyList());
        verify(presentationService).findAll();
        assertNotNull(response);
        assertEquals(dummyPresentations.size(), response.size());
        assertEquals(dummyPresentations.get(0).getName(), response.get(0).getName());
        assertEquals(dummyPresentations.get(0).getDuration(), response.get(0).getDuration());
    }

    @Test
    public void createPresentation() {
        Presentation dummyPresentation = dummyPresentation();
        when(presentationService.save(any())).thenReturn(dummyPresentation);

        ResponseEntity<String> response = presentationController.createPresentation(any());

        verify(presentationConverter).convertToEntity(any());
        verify(presentationService).save(any());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void createPresentations() {
        List<Presentation> dummyPresentations = Collections.singletonList(dummyPresentation());
        when(presentationService.saveAll(anyList())).thenReturn(dummyPresentations);

        ResponseEntity<String> response = presentationController.createPresentations(anyList());

        verify(presentationConverter).convertToEntityList(anyList());
        verify(presentationService).saveAll(anyList());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void updatePresentation() {
        Presentation dummyPresentation = dummyPresentation();
        PresentationDTO dummyPresentationDTO = dummyPresentationDTO();
        dummyPresentationDTO.setLightning(true);

        when(presentationService.findById(anyLong())).thenReturn(dummyPresentation);
        when(presentationService.save(any())).thenReturn(dummyPresentation);

        ResponseEntity<String> response = presentationController.updatePresentation(anyLong(), dummyPresentationDTO);

        verify(presentationService).save(any());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void removePresentation() {
        ResponseEntity<String> response = presentationController.removePresentation(anyLong());

        verify(presentationService).remove(any());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void removeAllPresentations() {
        ResponseEntity<String> response = presentationController.removeAllPresentations();

        verify(presentationService).removeAll();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private Presentation dummyPresentation() {
        return new Presentation("A", 60);
    }

    private PresentationDTO dummyPresentationDTO() {
        return new PresentationDTO("B", 90);
    }

}
