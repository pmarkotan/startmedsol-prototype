package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.PphMedicineDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PphMedicine} and its DTO {@link PphMedicineDTO}.
 */
@Mapper(componentModel = "spring", uses = {PphCompanyMapper.class, PphMedicineFormMapper.class, PphMotivationGroupMapper.class, PphNicheMapper.class, PphEuScoreMapper.class, PphMedicineQualifiedNameMapper.class})
public interface PphMedicineMapper extends EntityMapper<PphMedicineDTO, PphMedicine> {

    @Mapping(source = "dealerId.id", target = "dealerIdId")
    @Mapping(source = "marketingAuthOwner.id", target = "marketingAuthOwnerId")
    @Mapping(source = "medicineForm.id", target = "medicineFormId")
    @Mapping(source = "motivationGroup.id", target = "motivationGroupId")
    @Mapping(source = "niche.id", target = "nicheId")
    @Mapping(source = "qualifiedName.id", target = "qualifiedNameId")
    PphMedicineDTO toDto(PphMedicine pphMedicine);

    @Mapping(target = "subSidies", ignore = true)
    @Mapping(target = "removeSubSidies", ignore = true)
    @Mapping(source = "dealerIdId", target = "dealerId")
    @Mapping(source = "marketingAuthOwnerId", target = "marketingAuthOwner")
    @Mapping(source = "medicineFormId", target = "medicineForm")
    @Mapping(source = "motivationGroupId", target = "motivationGroup")
    @Mapping(source = "nicheId", target = "niche")
    @Mapping(target = "removeEuScores", ignore = true)
    @Mapping(source = "qualifiedNameId", target = "qualifiedName")
    PphMedicine toEntity(PphMedicineDTO pphMedicineDTO);

    default PphMedicine fromId(Long id) {
        if (id == null) {
            return null;
        }
        PphMedicine pphMedicine = new PphMedicine();
        pphMedicine.setId(id);
        return pphMedicine;
    }
}
