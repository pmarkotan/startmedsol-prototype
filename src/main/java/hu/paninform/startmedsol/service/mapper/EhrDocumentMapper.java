package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.EhrDocumentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EhrDocument} and its DTO {@link EhrDocumentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EhrDocumentMapper extends EntityMapper<EhrDocumentDTO, EhrDocument> {



    default EhrDocument fromId(Long id) {
        if (id == null) {
            return null;
        }
        EhrDocument ehrDocument = new EhrDocument();
        ehrDocument.setId(id);
        return ehrDocument;
    }
}
