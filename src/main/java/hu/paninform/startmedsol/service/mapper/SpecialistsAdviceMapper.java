package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.SpecialistsAdviceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SpecialistsAdvice} and its DTO {@link SpecialistsAdviceDTO}.
 */
@Mapper(componentModel = "spring", uses = {MedicalCaseMapper.class, CsDiagnosisMapper.class})
public interface SpecialistsAdviceMapper extends EntityMapper<SpecialistsAdviceDTO, SpecialistsAdvice> {

    @Mapping(source = "medicalCase.id", target = "medicalCaseId")
    @Mapping(source = "diagnosis.id", target = "diagnosisId")
    SpecialistsAdviceDTO toDto(SpecialistsAdvice specialistsAdvice);

    @Mapping(source = "medicalCaseId", target = "medicalCase")
    @Mapping(source = "diagnosisId", target = "diagnosis")
    SpecialistsAdvice toEntity(SpecialistsAdviceDTO specialistsAdviceDTO);

    default SpecialistsAdvice fromId(Long id) {
        if (id == null) {
            return null;
        }
        SpecialistsAdvice specialistsAdvice = new SpecialistsAdvice();
        specialistsAdvice.setId(id);
        return specialistsAdvice;
    }
}
