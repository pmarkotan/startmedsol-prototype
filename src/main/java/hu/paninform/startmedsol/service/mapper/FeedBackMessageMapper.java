package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.FeedBackMessageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FeedBackMessage} and its DTO {@link FeedBackMessageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FeedBackMessageMapper extends EntityMapper<FeedBackMessageDTO, FeedBackMessage> {



    default FeedBackMessage fromId(Long id) {
        if (id == null) {
            return null;
        }
        FeedBackMessage feedBackMessage = new FeedBackMessage();
        feedBackMessage.setId(id);
        return feedBackMessage;
    }
}
