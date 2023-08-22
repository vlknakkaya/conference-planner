package com.planner.conference.model.converter;

import com.planner.conference.model.dto.ConferenceDTO;
import com.planner.conference.model.entity.Conference;
import org.springframework.stereotype.Component;

@Component
public class ConferenceConverter implements DTOConverter<Conference, ConferenceDTO> {

    @Override
    public Conference convertToEntity(ConferenceDTO dto) {
        return dto != null ? new Conference(dto.getName(), dto.getDuration(), dto.getLightning()) : null;
    }

    @Override
    public ConferenceDTO convertToDTO(Conference entity) {
        return entity != null ? new ConferenceDTO(entity.getId(), entity.getName(), entity.getDuration(), entity.getLightning()) : null;
    }

}
