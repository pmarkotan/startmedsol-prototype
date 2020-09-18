package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.HashTagDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link HashTag} and its DTO {@link HashTagDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProviderMapper.class})
public interface HashTagMapper extends EntityMapper<HashTagDTO, HashTag> {

    @Mapping(source = "provider.id", target = "providerId")
    @Mapping(source = "provider.nameLong", target = "providerNameLong")
    HashTagDTO toDto(HashTag hashTag);

    @Mapping(source = "providerId", target = "provider")
    @Mapping(target = "patients", ignore = true)
    @Mapping(target = "removePatients", ignore = true)
    HashTag toEntity(HashTagDTO hashTagDTO);

    default HashTag fromId(Long id) {
        if (id == null) {
            return null;
        }
        HashTag hashTag = new HashTag();
        hashTag.setId(id);
        return hashTag;
    }
}
