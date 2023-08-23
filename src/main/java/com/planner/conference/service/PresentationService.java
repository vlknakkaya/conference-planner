package com.planner.conference.service;

import com.planner.conference.exception.EntityNotFoundException;
import com.planner.conference.model.entity.Presentation;
import com.planner.conference.repository.PresentationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresentationService {

    private final PresentationRepository presentationRepository;

    @Autowired
    public PresentationService(PresentationRepository presentationRepository) {
        this.presentationRepository = presentationRepository;
    }

    public Presentation findById(long id) {
        return presentationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Presentation", "id", id));
    }

    public List<Presentation> findAll() {
        return presentationRepository.findAll();
    }

    public Presentation save(Presentation presentation) {
        return presentationRepository.save(presentation);
    }

    public List<Presentation> saveAll(List<Presentation> presentationList) {
        return presentationRepository.saveAll(presentationList);
    }

    public void remove(Presentation presentation) {
        presentationRepository.delete(presentation);
    }

    public void removeAll() {
        presentationRepository.deleteAll();
    }

}
