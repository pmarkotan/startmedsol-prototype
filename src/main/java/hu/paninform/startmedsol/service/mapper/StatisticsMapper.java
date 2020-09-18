package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.StatisticsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Statistics} and its DTO {@link StatisticsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StatisticsMapper extends EntityMapper<StatisticsDTO, Statistics> {



    default Statistics fromId(Long id) {
        if (id == null) {
            return null;
        }
        Statistics statistics = new Statistics();
        statistics.setId(id);
        return statistics;
    }
}
