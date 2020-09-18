package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.ExternalDocumentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ExternalDocument} and its DTO {@link ExternalDocumentDTO}.
 */
@Mapper(componentModel = "spring", uses = {MedicalCaseMapper.class})
public interface ExternalDocumentMapper extends EntityMapper<ExternalDocumentDTO, ExternalDocument> {

    @Mapping(source = "medicalCase.id", target = "medicalCaseId")
    ExternalDocumentDTO toDto(ExternalDocument externalDocument);

    @Mapping(source = "medicalCaseId", target = "medicalCase")
    ExternalDocument toEntity(ExternalDocumentDTO externalDocumentDTO);

    default ExternalDocument fromId(Long id) {
        if (id == null) {
            return null;
        }
        ExternalDocument externalDocument = new ExternalDocument();
        externalDocument.setId(id);
        return externalDocument;
    }
}
