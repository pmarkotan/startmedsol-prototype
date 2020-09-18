package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.PraxisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Praxis} and its DTO {@link PraxisDTO}.
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class, ProviderMapper.class, EmployeeMapper.class})
public interface PraxisMapper extends EntityMapper<PraxisDTO, Praxis> {

    @Mapping(source = "officeAddress.id", target = "officeAddressId")
    @Mapping(source = "provider.id", target = "providerId")
    @Mapping(source = "doctor.id", target = "doctorId")
    PraxisDTO toDto(Praxis praxis);

    @Mapping(source = "officeAddressId", target = "officeAddress")
    @Mapping(source = "providerId", target = "provider")
    @Mapping(source = "doctorId", target = "doctor")
    Praxis toEntity(PraxisDTO praxisDTO);

    default Praxis fromId(Long id) {
        if (id == null) {
            return null;
        }
        Praxis praxis = new Praxis();
        praxis.setId(id);
        return praxis;
    }
}
