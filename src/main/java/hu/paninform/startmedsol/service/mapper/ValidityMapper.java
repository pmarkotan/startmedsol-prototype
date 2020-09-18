package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.ValidityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Validity} and its DTO {@link ValidityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ValidityMapper extends EntityMapper<ValidityDTO, Validity> {



    default Validity fromId(Long id) {
        if (id == null) {
            return null;
        }
        Validity validity = new Validity();
        validity.setId(id);
        return validity;
    }
}
