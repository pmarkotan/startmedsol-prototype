package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.DictionaryTranslationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DictionaryTranslation} and its DTO {@link DictionaryTranslationDTO}.
 */
@Mapper(componentModel = "spring", uses = {DictionaryItemMapper.class})
public interface DictionaryTranslationMapper extends EntityMapper<DictionaryTranslationDTO, DictionaryTranslation> {

    @Mapping(source = "dictionaryItem.id", target = "dictionaryItemId")
    DictionaryTranslationDTO toDto(DictionaryTranslation dictionaryTranslation);

    @Mapping(source = "dictionaryItemId", target = "dictionaryItem")
    DictionaryTranslation toEntity(DictionaryTranslationDTO dictionaryTranslationDTO);

    default DictionaryTranslation fromId(Long id) {
        if (id == null) {
            return null;
        }
        DictionaryTranslation dictionaryTranslation = new DictionaryTranslation();
        dictionaryTranslation.setId(id);
        return dictionaryTranslation;
    }
}
