package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.ProviderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Provider} and its DTO {@link ProviderDTO}.
 */
@Mapper(componentModel = "spring", uses = {ContactPersonMapper.class, CompanyMapper.class, BillingInformationMapper.class, EmployeeMapper.class})
public interface ProviderMapper extends EntityMapper<ProviderDTO, Provider> {

    @Mapping(source = "contactPerson.id", target = "contactPersonId")
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "billingInformation.id", target = "billingInformationId")
    ProviderDTO toDto(Provider provider);

    @Mapping(source = "contactPersonId", target = "contactPerson")
    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "billingInformationId", target = "billingInformation")
    @Mapping(target = "praxes", ignore = true)
    @Mapping(target = "removePraxis", ignore = true)
    @Mapping(target = "removeEmployee", ignore = true)
    Provider toEntity(ProviderDTO providerDTO);

    default Provider fromId(Long id) {
        if (id == null) {
            return null;
        }
        Provider provider = new Provider();
        provider.setId(id);
        return provider;
    }
}
