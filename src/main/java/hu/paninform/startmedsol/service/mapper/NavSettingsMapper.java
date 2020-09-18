package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.NavSettingsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NavSettings} and its DTO {@link NavSettingsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NavSettingsMapper extends EntityMapper<NavSettingsDTO, NavSettings> {



    default NavSettings fromId(Long id) {
        if (id == null) {
            return null;
        }
        NavSettings navSettings = new NavSettings();
        navSettings.setId(id);
        return navSettings;
    }
}
