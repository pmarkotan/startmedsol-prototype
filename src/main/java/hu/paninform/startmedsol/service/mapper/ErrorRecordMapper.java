package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.ErrorRecordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ErrorRecord} and its DTO {@link ErrorRecordDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ErrorRecordMapper extends EntityMapper<ErrorRecordDTO, ErrorRecord> {



    default ErrorRecord fromId(Long id) {
        if (id == null) {
            return null;
        }
        ErrorRecord errorRecord = new ErrorRecord();
        errorRecord.setId(id);
        return errorRecord;
    }
}
