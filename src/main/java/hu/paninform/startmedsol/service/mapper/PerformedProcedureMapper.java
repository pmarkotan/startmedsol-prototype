package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.PerformedProcedureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PerformedProcedure} and its DTO {@link PerformedProcedureDTO}.
 */
@Mapper(componentModel = "spring", uses = {CsProcedureMapper.class, MedicalCaseMapper.class})
public interface PerformedProcedureMapper extends EntityMapper<PerformedProcedureDTO, PerformedProcedure> {

    @Mapping(source = "procedure.id", target = "procedureId")
    @Mapping(source = "medicalCase.id", target = "medicalCaseId")
    PerformedProcedureDTO toDto(PerformedProcedure performedProcedure);

    @Mapping(source = "procedureId", target = "procedure")
    @Mapping(source = "medicalCaseId", target = "medicalCase")
    PerformedProcedure toEntity(PerformedProcedureDTO performedProcedureDTO);

    default PerformedProcedure fromId(Long id) {
        if (id == null) {
            return null;
        }
        PerformedProcedure performedProcedure = new PerformedProcedure();
        performedProcedure.setId(id);
        return performedProcedure;
    }
}
