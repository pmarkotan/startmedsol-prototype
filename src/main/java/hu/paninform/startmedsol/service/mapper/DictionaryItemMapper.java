package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.DictionaryItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DictionaryItem} and its DTO {@link DictionaryItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DictionaryItemMapper extends EntityMapper<DictionaryItemDTO, DictionaryItem> {


    @Mapping(target = "dictionaryTranslations", ignore = true)
    @Mapping(target = "removeDictionaryTranslations", ignore = true)
    @Mapping(target = "removeParent", ignore = true)
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "removeChildren", ignore = true)
    DictionaryItem toEntity(DictionaryItemDTO dictionaryItemDTO);

    default DictionaryItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        DictionaryItem dictionaryItem = new DictionaryItem();
        dictionaryItem.setId(id);
        return dictionaryItem;
    }
}
