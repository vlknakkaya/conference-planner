package com.planner.conference.model.converter;

import com.planner.conference.model.dto.PresentationDTO;
import com.planner.conference.model.entity.Presentation;
import org.springframework.stereotype.Component;

@Component
public class PresentationConverter implements DTOConverter<Presentation, PresentationDTO> {

    @Override
    public Presentation convertToEntity(PresentationDTO dto) {
        return dto != null ? new Presentation(dto.getName(), dto.getDuration(), dto.getLightning()) : null;
    }

    @Override
    public PresentationDTO convertToDTO(Presentation entity) {
        return entity != null ? new PresentationDTO(entity.getId(), entity.getName(), entity.getDuration(), entity.getLightning()) : null;
    }

}
