package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.MedicalInvoiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MedicalInvoice} and its DTO {@link MedicalInvoiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {MedicalCaseMapper.class})
public interface MedicalInvoiceMapper extends EntityMapper<MedicalInvoiceDTO, MedicalInvoice> {

    @Mapping(source = "medicalCase.id", target = "medicalCaseId")
    MedicalInvoiceDTO toDto(MedicalInvoice medicalInvoice);

    @Mapping(source = "medicalCaseId", target = "medicalCase")
    MedicalInvoice toEntity(MedicalInvoiceDTO medicalInvoiceDTO);

    default MedicalInvoice fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedicalInvoice medicalInvoice = new MedicalInvoice();
        medicalInvoice.setId(id);
        return medicalInvoice;
    }
}
