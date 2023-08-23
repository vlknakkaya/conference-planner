package com.planner.conference.controller;

import com.planner.conference.model.converter.PresentationConverter;
import com.planner.conference.model.dto.PresentationDTO;
import com.planner.conference.model.entity.Presentation;
import com.planner.conference.model.validation.PresentationDTOValidator;
import com.planner.conference.service.PresentationService;
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
@RequestMapping("/presentation")
public class PresentationController {

    private final PresentationService presentationService;
    private final PresentationConverter presentationConverter;
    private final PresentationDTOValidator presentationDTOValidator;

    @InitBinder(value = "presentationDTO")
    protected void initPresentationDTOValidator(final WebDataBinder binder){
        binder.addValidators(presentationDTOValidator);
    }

    @Autowired
    public PresentationController(PresentationService presentationService, PresentationConverter presentationConverter, PresentationDTOValidator presentationDTOValidator) {
        this.presentationService = presentationService;
        this.presentationConverter = presentationConverter;
        this.presentationDTOValidator = presentationDTOValidator;
    }

    @GetMapping("/{id}")
    public PresentationDTO getPresentation(@PathVariable long id) {
        return presentationConverter.convertToDTO(presentationService.findById(id));
    }

    @GetMapping
    public List<PresentationDTO> getAllPresentations() {
        return presentationConverter.convertToDTOList(presentationService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createPresentation(@RequestBody @Valid PresentationDTO presentationDTO) {
        Presentation presentation = presentationService.save(presentationConverter.convertToEntity(presentationDTO));

        return ResponseEntity.ok("Presentation was created: " + presentation.getId());
    }

    @PostMapping("/bulk")
    public ResponseEntity<String> createPresentations(@RequestBody List<@Valid PresentationDTO> presentationDTOList) {
        List<Presentation> presentations = presentationService.saveAll(presentationConverter.convertToEntityList(presentationDTOList));

        return ResponseEntity.ok("Presentations were created: " + presentations.stream().map(Presentation::getId).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePresentation(@PathVariable long id, @RequestBody @Valid PresentationDTO presentationDTO) {
        Presentation presentation = presentationService.findById(id);

        if (presentationDTO.getName() != null) {
            presentation.setName(presentationDTO.getName());
        }

        if (presentationDTO.getDuration() != null) {
            presentation.setDuration(presentationDTO.getDuration());
        }

        if (presentationDTO.getLightning() != null) {
            presentation.setLightning(true);
            presentation.setDuration(Constants.LIGHTNING_DURATION);
        }

        presentation = presentationService.save(presentation);

        return ResponseEntity.ok("Presentation was updated: " + presentation.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removePresentation(@PathVariable long id) {
        presentationService.remove(presentationService.findById(id));

        return ResponseEntity.ok("Presentation was removed: " + id);
    }

    @DeleteMapping
    public ResponseEntity<String> removeAllPresentations() {
        presentationService.removeAll();

        return ResponseEntity.ok("All presentations were removed.");
    }

}
