package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.PatientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Patient} and its DTO {@link PatientDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonalDataMapper.class, HashTagMapper.class})
public interface PatientMapper extends EntityMapper<PatientDTO, Patient> {

    @Mapping(source = "personalData.id", target = "personalDataId")
    PatientDTO toDto(Patient patient);

    @Mapping(source = "personalDataId", target = "personalData")
    @Mapping(target = "cards", ignore = true)
    @Mapping(target = "removeCard", ignore = true)
    @Mapping(target = "removeTags", ignore = true)
    Patient toEntity(PatientDTO patientDTO);

    default Patient fromId(Long id) {
        if (id == null) {
            return null;
        }
        Patient patient = new Patient();
        patient.setId(id);
        return patient;
    }
}
