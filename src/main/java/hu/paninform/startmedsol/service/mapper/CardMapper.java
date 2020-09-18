package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.CardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Card} and its DTO {@link CardDTO}.
 */
@Mapper(componentModel = "spring", uses = {PatientMapper.class})
public interface CardMapper extends EntityMapper<CardDTO, Card> {

    @Mapping(source = "patient.id", target = "patientId")
    CardDTO toDto(Card card);

    @Mapping(source = "patientId", target = "patient")
    Card toEntity(CardDTO cardDTO);

    default Card fromId(Long id) {
        if (id == null) {
            return null;
        }
        Card card = new Card();
        card.setId(id);
        return card;
    }
}
