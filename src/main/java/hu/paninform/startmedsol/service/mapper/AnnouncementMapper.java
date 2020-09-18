package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.AnnouncementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Announcement} and its DTO {@link AnnouncementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AnnouncementMapper extends EntityMapper<AnnouncementDTO, Announcement> {



    default Announcement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Announcement announcement = new Announcement();
        announcement.setId(id);
        return announcement;
    }
}
