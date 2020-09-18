package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.CsDiagnosisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CsDiagnosis} and its DTO {@link CsDiagnosisDTO}.
 */
@Mapper(componentModel = "spring", uses = {ValidityMapper.class})
public interface CsDiagnosisMapper extends EntityMapper<CsDiagnosisDTO, CsDiagnosis> {

    @Mapping(source = "validity.id", target = "validityId")
    CsDiagnosisDTO toDto(CsDiagnosis csDiagnosis);

    @Mapping(source = "validityId", target = "validity")
    CsDiagnosis toEntity(CsDiagnosisDTO csDiagnosisDTO);

    default CsDiagnosis fromId(Long id) {
        if (id == null) {
            return null;
        }
        CsDiagnosis csDiagnosis = new CsDiagnosis();
        csDiagnosis.setId(id);
        return csDiagnosis;
    }
}
