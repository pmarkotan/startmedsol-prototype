package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.DynamicFormDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DynamicForm} and its DTO {@link DynamicFormDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProviderMapper.class})
public interface DynamicFormMapper extends EntityMapper<DynamicFormDTO, DynamicForm> {

    @Mapping(source = "provider.id", target = "providerId")
    DynamicFormDTO toDto(DynamicForm dynamicForm);

    @Mapping(source = "providerId", target = "provider")
    DynamicForm toEntity(DynamicFormDTO dynamicFormDTO);

    default DynamicForm fromId(Long id) {
        if (id == null) {
            return null;
        }
        DynamicForm dynamicForm = new DynamicForm();
        dynamicForm.setId(id);
        return dynamicForm;
    }
}
