package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PerformedProcedureDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PerformedProcedureDTO.class);
        PerformedProcedureDTO performedProcedureDTO1 = new PerformedProcedureDTO();
        performedProcedureDTO1.setId(1L);
        PerformedProcedureDTO performedProcedureDTO2 = new PerformedProcedureDTO();
        assertThat(performedProcedureDTO1).isNotEqualTo(performedProcedureDTO2);
        performedProcedureDTO2.setId(performedProcedureDTO1.getId());
        assertThat(performedProcedureDTO1).isEqualTo(performedProcedureDTO2);
        performedProcedureDTO2.setId(2L);
        assertThat(performedProcedureDTO1).isNotEqualTo(performedProcedureDTO2);
        performedProcedureDTO1.setId(null);
        assertThat(performedProcedureDTO1).isNotEqualTo(performedProcedureDTO2);
    }
}
