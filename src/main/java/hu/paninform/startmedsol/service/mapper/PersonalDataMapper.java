package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.PersonalDataDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PersonalData} and its DTO {@link PersonalDataDTO}.
 */
@Mapper(componentModel = "spring", uses = {BillingInformationMapper.class})
public interface PersonalDataMapper extends EntityMapper<PersonalDataDTO, PersonalData> {

    @Mapping(source = "billingInformation.id", target = "billingInformationId")
    PersonalDataDTO toDto(PersonalData personalData);

    @Mapping(source = "billingInformationId", target = "billingInformation")
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "removeAddress", ignore = true)
    PersonalData toEntity(PersonalDataDTO personalDataDTO);

    default PersonalData fromId(Long id) {
        if (id == null) {
            return null;
        }
        PersonalData personalData = new PersonalData();
        personalData.setId(id);
        return personalData;
    }
}
