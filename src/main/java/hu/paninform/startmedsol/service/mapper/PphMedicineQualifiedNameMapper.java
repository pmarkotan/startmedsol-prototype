package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.PphMedicineQualifiedNameDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PphMedicineQualifiedName} and its DTO {@link PphMedicineQualifiedNameDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PphMedicineQualifiedNameMapper extends EntityMapper<PphMedicineQualifiedNameDTO, PphMedicineQualifiedName> {


    @Mapping(target = "medicines", ignore = true)
    @Mapping(target = "removeMedicine", ignore = true)
    PphMedicineQualifiedName toEntity(PphMedicineQualifiedNameDTO pphMedicineQualifiedNameDTO);

    default PphMedicineQualifiedName fromId(Long id) {
        if (id == null) {
            return null;
        }
        PphMedicineQualifiedName pphMedicineQualifiedName = new PphMedicineQualifiedName();
        pphMedicineQualifiedName.setId(id);
        return pphMedicineQualifiedName;
    }
}
