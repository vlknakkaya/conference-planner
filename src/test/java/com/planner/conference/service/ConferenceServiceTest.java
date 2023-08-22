package com.planner.conference.service;

import com.planner.conference.exception.EntityNotFoundException;
import com.planner.conference.model.entity.Conference;
import com.planner.conference.repository.ConferenceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ConferenceServiceTest {

    @InjectMocks
    private ConferenceService conferenceService;

    @Mock
    private ConferenceRepository conferenceRepository;

    @Test
    public void findById() {
        Optional<Conference> optConference = Optional.of(dummyConference());
        when(conferenceRepository.findById(5L)).thenReturn(optConference);

        Conference conference = conferenceService.findById(5L);

        verify(conferenceRepository).findById(5L);
        assertNotNull(conference);
    }

    @Test
    public void findById_notFound() {
        assertThrows(EntityNotFoundException.class, () -> conferenceService.findById(-1L));
    }

    @Test
    public void findAll() {
        conferenceService.findAll();

        verify(conferenceRepository).findAll();
    }

    @Test
    public void save() {
        Conference dummyConference = dummyConference();

        conferenceService.save(dummyConference);

        verify(conferenceRepository).save(dummyConference);
    }

    @Test
    public void saveAll() {
        List<Conference> conferenceList = Collections.singletonList(dummyConference());

        conferenceService.saveAll(conferenceList);

        verify(conferenceRepository).saveAll(conferenceList);
    }

    @Test
    public void remove() {
        conferenceService.remove(1L);

        verify(conferenceRepository).deleteById(1L);
    }

    @Test
    public void removeAll() {
        conferenceService.removeAll();

        verify(conferenceRepository).deleteAll();
    }

    private Conference dummyConference() {
        return new Conference("dummy", 15);
    }

}
