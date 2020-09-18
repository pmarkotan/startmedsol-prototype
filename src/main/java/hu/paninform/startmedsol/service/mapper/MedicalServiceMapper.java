package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.MedicalServiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MedicalService} and its DTO {@link MedicalServiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {PraxisMapper.class, ProviderMapper.class})
public interface MedicalServiceMapper extends EntityMapper<MedicalServiceDTO, MedicalService> {

    @Mapping(source = "praxis.id", target = "praxisId")
    @Mapping(source = "provider.id", target = "providerId")
    MedicalServiceDTO toDto(MedicalService medicalService);

    @Mapping(source = "praxisId", target = "praxis")
    @Mapping(source = "providerId", target = "provider")
    MedicalService toEntity(MedicalServiceDTO medicalServiceDTO);

    default MedicalService fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedicalService medicalService = new MedicalService();
        medicalService.setId(id);
        return medicalService;
    }
}
