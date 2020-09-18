package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.CaseTextDocumentationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CaseTextDocumentation} and its DTO {@link CaseTextDocumentationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CaseTextDocumentationMapper extends EntityMapper<CaseTextDocumentationDTO, CaseTextDocumentation> {



    default CaseTextDocumentation fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseTextDocumentation caseTextDocumentation = new CaseTextDocumentation();
        caseTextDocumentation.setId(id);
        return caseTextDocumentation;
    }
}
