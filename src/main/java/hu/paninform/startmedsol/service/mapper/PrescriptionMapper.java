package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.PrescriptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Prescription} and its DTO {@link PrescriptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {CsDiagnosisMapper.class, EmployeeMapper.class, PphMedicineMapper.class, MedicalCaseMapper.class, PphPuphaVersionMapper.class})
public interface PrescriptionMapper extends EntityMapper<PrescriptionDTO, Prescription> {

    @Mapping(source = "diagnosis.id", target = "diagnosisId")
    @Mapping(source = "inscriberDoctor.id", target = "inscriberDoctorId")
    @Mapping(source = "qualificationRuleAcceptor.id", target = "qualificationRuleAcceptorId")
    @Mapping(source = "medicine.id", target = "medicineId")
    @Mapping(source = "medicalCase.id", target = "medicalCaseId")
    @Mapping(source = "puphaVersion.id", target = "puphaVersionId")
    PrescriptionDTO toDto(Prescription prescription);

    @Mapping(source = "diagnosisId", target = "diagnosis")
    @Mapping(source = "inscriberDoctorId", target = "inscriberDoctor")
    @Mapping(source = "qualificationRuleAcceptorId", target = "qualificationRuleAcceptor")
    @Mapping(source = "medicineId", target = "medicine")
    @Mapping(source = "medicalCaseId", target = "medicalCase")
    @Mapping(source = "puphaVersionId", target = "puphaVersion")
    Prescription toEntity(PrescriptionDTO prescriptionDTO);

    default Prescription fromId(Long id) {
        if (id == null) {
            return null;
        }
        Prescription prescription = new Prescription();
        prescription.setId(id);
        return prescription;
    }
}
