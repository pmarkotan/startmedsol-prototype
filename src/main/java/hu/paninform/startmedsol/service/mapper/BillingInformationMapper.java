package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.BillingInformationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BillingInformation} and its DTO {@link BillingInformationDTO}.
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface BillingInformationMapper extends EntityMapper<BillingInformationDTO, BillingInformation> {

    @Mapping(source = "billingAddress.id", target = "billingAddressId")
    BillingInformationDTO toDto(BillingInformation billingInformation);

    @Mapping(source = "billingAddressId", target = "billingAddress")
    BillingInformation toEntity(BillingInformationDTO billingInformationDTO);

    default BillingInformation fromId(Long id) {
        if (id == null) {
            return null;
        }
        BillingInformation billingInformation = new BillingInformation();
        billingInformation.setId(id);
        return billingInformation;
    }
}
