package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.DataGeneratorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DataGenerator} and its DTO {@link DataGeneratorDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DataGeneratorMapper extends EntityMapper<DataGeneratorDTO, DataGenerator> {



    default DataGenerator fromId(Long id) {
        if (id == null) {
            return null;
        }
        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.setId(id);
        return dataGenerator;
    }
}
