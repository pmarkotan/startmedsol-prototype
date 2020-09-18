package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.CsPostalCodeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CsPostalCode} and its DTO {@link CsPostalCodeDTO}.
 */
@Mapper(componentModel = "spring", uses = {ValidityMapper.class})
public interface CsPostalCodeMapper extends EntityMapper<CsPostalCodeDTO, CsPostalCode> {

    @Mapping(source = "validity.id", target = "validityId")
    CsPostalCodeDTO toDto(CsPostalCode csPostalCode);

    @Mapping(source = "validityId", target = "validity")
    CsPostalCode toEntity(CsPostalCodeDTO csPostalCodeDTO);

    default CsPostalCode fromId(Long id) {
        if (id == null) {
            return null;
        }
        CsPostalCode csPostalCode = new CsPostalCode();
        csPostalCode.setId(id);
        return csPostalCode;
    }
}
