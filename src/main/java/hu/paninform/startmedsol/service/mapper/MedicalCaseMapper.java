package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.MedicalCaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MedicalCase} and its DTO {@link MedicalCaseDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MedicalCaseMapper extends EntityMapper<MedicalCaseDTO, MedicalCase> {


    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "removeDocuments", ignore = true)
    MedicalCase toEntity(MedicalCaseDTO medicalCaseDTO);

    default MedicalCase fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedicalCase medicalCase = new MedicalCase();
        medicalCase.setId(id);
        return medicalCase;
    }
}
