package com.planner.conference.model.validation;

import com.planner.conference.model.dto.PresentationDTO;
import com.planner.conference.util.ErrorCodes;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PresentationDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PresentationDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PresentationDTO presentationDTO = (PresentationDTO) target;

        if (presentationDTO.getName() == null || presentationDTO.getName().isEmpty()) {
            errors.rejectValue("name",
                    String.valueOf(ErrorCodes.VALIDATION_EMPTY_NAME),
                    "name field can not be blank");
        }

        if (presentationDTO.getDuration() != null && presentationDTO.getLightning() != null) {
            errors.rejectValue("duration",
                    String.valueOf(ErrorCodes.VALIDATION_BOTH_DURATION_AND_LIGHTNING),
                    "both of duration or lightning values can not be given");
        }

        if (presentationDTO.getDuration() == null && presentationDTO.getLightning() == null) {
            errors.rejectValue("duration",
                    String.valueOf(ErrorCodes.VALIDATION_EMPTY_DURATION_AND_LIGHTNING),
                    "only one of duration or lightning values must be given");
        }

        if (presentationDTO.getDuration() != null && presentationDTO.getDuration().intValue() <= 0) {
            errors.rejectValue("duration",
                    String.valueOf(ErrorCodes.VALIDATION_DURATION_LOWER_THAN_ZERO),
                    "duration must be greater than 0");
        }

        if (presentationDTO.getLightning() != null && !presentationDTO.getLightning().booleanValue()) {
            errors.rejectValue("lightning",
                    String.valueOf(ErrorCodes.VALIDATION_LIGHTNING_MUST_BE_TRUE),
                    "lightning must be true if given");
        }
    }

}
