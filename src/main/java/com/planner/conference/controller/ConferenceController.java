package com.planner.conference.controller;

import com.planner.conference.model.converter.ConferenceConverter;
import com.planner.conference.model.dto.ConferenceDTO;
import com.planner.conference.model.entity.Conference;
import com.planner.conference.model.validation.ConferenceDTOValidator;
import com.planner.conference.service.ConferenceService;
import com.planner.conference.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/conference")
public class ConferenceController {

    private final ConferenceService conferenceService;
    private final ConferenceConverter conferenceConverter;
    private final ConferenceDTOValidator conferenceDTOValidator;

    @InitBinder(value = "conferenceDTO")
    protected void initConferenceDTOValidator(final WebDataBinder binder){
        binder.addValidators(conferenceDTOValidator);
    }

    @Autowired
    public ConferenceController(ConferenceService conferenceService, ConferenceConverter conferenceConverter, ConferenceDTOValidator conferenceDTOValidator) {
        this.conferenceService = conferenceService;
        this.conferenceConverter = conferenceConverter;
        this.conferenceDTOValidator = conferenceDTOValidator;
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
    public ResponseEntity<String> createConference(@RequestBody @Valid ConferenceDTO conferenceDTO) {
        Conference conference = conferenceService.save(conferenceConverter.convertToEntity(conferenceDTO));

        return ResponseEntity.ok("Conference was created: " + conference.getId());
    }

    @PostMapping("/bulk")
    public ResponseEntity<String> createConferences(@RequestBody List<@Valid ConferenceDTO> conferenceDTOList) {
        List<Conference> conferences = conferenceService.saveAll(conferenceConverter.convertToEntityList(conferenceDTOList));

        return ResponseEntity.ok("Conferences were created: " + conferences.stream().map(Conference::getId).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateConference(@PathVariable long id, @RequestBody @Valid ConferenceDTO conferenceDTO) {
        Conference conference = conferenceService.findById(id);

        if (conferenceDTO.getName() != null) {
            conference.setName(conferenceDTO.getName());
        }

        if (conferenceDTO.getDuration() != null) {
            conference.setDuration(conferenceDTO.getDuration());
        }

        if (conferenceDTO.getLightning() != null) {
            conference.setLightning(true);
            conference.setDuration(Constants.LIGHTNING_DURATION);
        }

        conference = conferenceService.save(conference);

        return ResponseEntity.ok("Conference was updated: " + conference.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeConference(@PathVariable long id) {
        conferenceService.remove(conferenceService.findById(id));

        return ResponseEntity.ok("Conference was removed: " + id);
    }

    @DeleteMapping
    public ResponseEntity<String> removeAllConferences() {
        conferenceService.removeAll();

        return ResponseEntity.ok("All conferences were removed.");
    }

}
