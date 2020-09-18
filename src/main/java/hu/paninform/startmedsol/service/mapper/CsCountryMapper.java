package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.CsCountryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CsCountry} and its DTO {@link CsCountryDTO}.
 */
@Mapper(componentModel = "spring", uses = {ValidityMapper.class})
public interface CsCountryMapper extends EntityMapper<CsCountryDTO, CsCountry> {

    @Mapping(source = "validity.id", target = "validityId")
    CsCountryDTO toDto(CsCountry csCountry);

    @Mapping(source = "validityId", target = "validity")
    CsCountry toEntity(CsCountryDTO csCountryDTO);

    default CsCountry fromId(Long id) {
        if (id == null) {
            return null;
        }
        CsCountry csCountry = new CsCountry();
        csCountry.setId(id);
        return csCountry;
    }
}
