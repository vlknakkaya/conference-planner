package com.planner.conference.service;

import com.planner.conference.exception.EntityNotFoundException;
import com.planner.conference.model.entity.Presentation;
import com.planner.conference.repository.PresentationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PresentationServiceTest {

    @InjectMocks
    private PresentationService presentationService;

    @Mock
    private PresentationRepository presentationRepository;

    @Test
    public void findById() {
        Optional<Presentation> optConference = Optional.of(dummyPresentation());
        when(presentationRepository.findById(5L)).thenReturn(optConference);

        Presentation presentation = presentationService.findById(5L);

        verify(presentationRepository).findById(5L);
        assertNotNull(presentation);
    }

    @Test
    public void findById_notFound() {
        assertThrows(EntityNotFoundException.class, () -> presentationService.findById(-1L));
    }

    @Test
    public void findAll() {
        presentationService.findAll();

        verify(presentationRepository).findAll();
    }

    @Test
    public void save() {
        Presentation dummyPresentation = dummyPresentation();

        presentationService.save(dummyPresentation);

        verify(presentationRepository).save(dummyPresentation);
    }

    @Test
    public void saveAll() {
        List<Presentation> presentationList = Collections.singletonList(dummyPresentation());

        presentationService.saveAll(presentationList);

        verify(presentationRepository).saveAll(presentationList);
    }

    @Test
    public void remove() {
        presentationService.remove(any());

        verify(presentationRepository).delete(any());
    }

    @Test
    public void removeAll() {
        presentationService.removeAll();

        verify(presentationRepository).deleteAll();
    }

    private Presentation dummyPresentation() {
        return new Presentation("dummy", 15);
    }

}
