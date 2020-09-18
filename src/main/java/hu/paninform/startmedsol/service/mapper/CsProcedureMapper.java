package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.CsProcedureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CsProcedure} and its DTO {@link CsProcedureDTO}.
 */
@Mapper(componentModel = "spring", uses = {ValidityMapper.class})
public interface CsProcedureMapper extends EntityMapper<CsProcedureDTO, CsProcedure> {

    @Mapping(source = "validity.id", target = "validityId")
    CsProcedureDTO toDto(CsProcedure csProcedure);

    @Mapping(source = "validityId", target = "validity")
    CsProcedure toEntity(CsProcedureDTO csProcedureDTO);

    default CsProcedure fromId(Long id) {
        if (id == null) {
            return null;
        }
        CsProcedure csProcedure = new CsProcedure();
        csProcedure.setId(id);
        return csProcedure;
    }
}
