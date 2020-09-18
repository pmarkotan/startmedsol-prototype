package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.MedicalCaseDiagnosisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MedicalCaseDiagnosis} and its DTO {@link MedicalCaseDiagnosisDTO}.
 */
@Mapper(componentModel = "spring", uses = {CsDiagnosisMapper.class, MedicalCaseMapper.class})
public interface MedicalCaseDiagnosisMapper extends EntityMapper<MedicalCaseDiagnosisDTO, MedicalCaseDiagnosis> {

    @Mapping(source = "diagnosis.id", target = "diagnosisId")
    @Mapping(source = "medicalCase.id", target = "medicalCaseId")
    MedicalCaseDiagnosisDTO toDto(MedicalCaseDiagnosis medicalCaseDiagnosis);

    @Mapping(source = "diagnosisId", target = "diagnosis")
    @Mapping(source = "medicalCaseId", target = "medicalCase")
    MedicalCaseDiagnosis toEntity(MedicalCaseDiagnosisDTO medicalCaseDiagnosisDTO);

    default MedicalCaseDiagnosis fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedicalCaseDiagnosis medicalCaseDiagnosis = new MedicalCaseDiagnosis();
        medicalCaseDiagnosis.setId(id);
        return medicalCaseDiagnosis;
    }
}
