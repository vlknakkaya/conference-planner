package com.planner.conference.service;

import com.planner.conference.exception.EntityNotFoundException;
import com.planner.conference.model.entity.Conference;
import com.planner.conference.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    public Conference findById(long id) {
        return conferenceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Conference", "id", id));
    }

    public List<Conference> findAll() {
        return conferenceRepository.findAll();
    }

    public Conference save(Conference conference) {
        return conferenceRepository.save(conference);
    }

    public List<Conference> saveAll(List<Conference> conferenceList) {
        return conferenceRepository.saveAll(conferenceList);
    }

    public void remove(long id) {
        conferenceRepository.deleteById(id);
    }

    public void removeAll() {
        conferenceRepository.deleteAll();
    }

}
