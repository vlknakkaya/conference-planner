package com.planner.conference.controller;

import com.planner.conference.model.converter.ConferenceConverter;
import com.planner.conference.model.dto.ConferenceDTO;
import com.planner.conference.model.entity.Conference;
import com.planner.conference.service.ConferenceService;
import com.planner.conference.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/conference")
public class ConferenceController {

    private final ConferenceService conferenceService;
    private final ConferenceConverter conferenceConverter;

    @Autowired
    public ConferenceController(ConferenceService conferenceService, ConferenceConverter conferenceConverter) {
        this.conferenceService = conferenceService;
        this.conferenceConverter = conferenceConverter;
    }

    @GetMapping("/{id}")
    public ConferenceDTO getConference(@PathVariable long id) {
        return conferenceConverter.convertToDTO(conferenceService.findById(id));
    }

    @GetMapping
    public List<ConferenceDTO> getAllConferences() {
        return conferenceConverter.convertToDTOList(conferenceService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createConference(@RequestBody ConferenceDTO conferenceDTO) {
        Conference conference = conferenceService.save(conferenceConverter.convertToEntity(conferenceDTO));

        return ResponseEntity.ok("Conference was created: " + conference.getId());
    }

    @PostMapping("/bulk")
    public ResponseEntity<String> createConferences(@RequestBody List<ConferenceDTO> conferenceDTOList) {
        List<Conference> conferences = conferenceService.saveAll(conferenceConverter.convertToEntityList(conferenceDTOList));

        return ResponseEntity.ok("Conferences were created: " + conferences.stream().map(Conference::getId).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateConference(@PathVariable long id, @RequestBody ConferenceDTO conferenceDTO) {
        Conference conference = conferenceService.findById(id);

        if (conferenceDTO.getName() != null) {
            conference.setName(conferenceDTO.getName());
        }

        if (conferenceDTO.getDuration() >= 0) {
            conference.setDuration(conferenceDTO.getDuration());
        }

        if (conferenceDTO.isLightning()) {
            conference.setLightning(true);
            conference.setDuration(Constants.LIGHTNING_DURATION);
        }

        conference = conferenceService.save(conference);

        return ResponseEntity.ok("Conference was updated: " + conference.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeConference(@PathVariable long id) {
        conferenceService.remove(id);

        return ResponseEntity.ok("Conference was removed: " + id);
    }

    @DeleteMapping
    public ResponseEntity<String> removeAllConferences() {
        conferenceService.removeAll();

        return ResponseEntity.ok("All conferences were removed.");
    }

}
