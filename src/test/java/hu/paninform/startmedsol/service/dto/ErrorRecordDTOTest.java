package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class ErrorRecordDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ErrorRecordDTO.class);
        ErrorRecordDTO errorRecordDTO1 = new ErrorRecordDTO();
        errorRecordDTO1.setId(1L);
        ErrorRecordDTO errorRecordDTO2 = new ErrorRecordDTO();
        assertThat(errorRecordDTO1).isNotEqualTo(errorRecordDTO2);
        errorRecordDTO2.setId(errorRecordDTO1.getId());
        assertThat(errorRecordDTO1).isEqualTo(errorRecordDTO2);
        errorRecordDTO2.setId(2L);
        assertThat(errorRecordDTO1).isNotEqualTo(errorRecordDTO2);
        errorRecordDTO1.setId(null);
        assertThat(errorRecordDTO1).isNotEqualTo(errorRecordDTO2);
    }
}
