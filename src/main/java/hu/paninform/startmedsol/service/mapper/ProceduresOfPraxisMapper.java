package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.ProceduresOfPraxisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProceduresOfPraxis} and its DTO {@link ProceduresOfPraxisDTO}.
 */
@Mapper(componentModel = "spring", uses = {CsProcedureMapper.class, PraxisMapper.class})
public interface ProceduresOfPraxisMapper extends EntityMapper<ProceduresOfPraxisDTO, ProceduresOfPraxis> {

    @Mapping(source = "procedure.id", target = "procedureId")
    @Mapping(source = "praxis.id", target = "praxisId")
    ProceduresOfPraxisDTO toDto(ProceduresOfPraxis proceduresOfPraxis);

    @Mapping(source = "procedureId", target = "procedure")
    @Mapping(source = "praxisId", target = "praxis")
    ProceduresOfPraxis toEntity(ProceduresOfPraxisDTO proceduresOfPraxisDTO);

    default ProceduresOfPraxis fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProceduresOfPraxis proceduresOfPraxis = new ProceduresOfPraxis();
        proceduresOfPraxis.setId(id);
        return proceduresOfPraxis;
    }
}
