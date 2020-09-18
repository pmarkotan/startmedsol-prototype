package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.PuphaLoaderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PuphaLoader} and its DTO {@link PuphaLoaderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PuphaLoaderMapper extends EntityMapper<PuphaLoaderDTO, PuphaLoader> {



    default PuphaLoader fromId(Long id) {
        if (id == null) {
            return null;
        }
        PuphaLoader puphaLoader = new PuphaLoader();
        puphaLoader.setId(id);
        return puphaLoader;
    }
}
