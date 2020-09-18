package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class CsProcedureDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CsProcedureDTO.class);
        CsProcedureDTO csProcedureDTO1 = new CsProcedureDTO();
        csProcedureDTO1.setId(1L);
        CsProcedureDTO csProcedureDTO2 = new CsProcedureDTO();
        assertThat(csProcedureDTO1).isNotEqualTo(csProcedureDTO2);
        csProcedureDTO2.setId(csProcedureDTO1.getId());
        assertThat(csProcedureDTO1).isEqualTo(csProcedureDTO2);
        csProcedureDTO2.setId(2L);
        assertThat(csProcedureDTO1).isNotEqualTo(csProcedureDTO2);
        csProcedureDTO1.setId(null);
        assertThat(csProcedureDTO1).isNotEqualTo(csProcedureDTO2);
    }
}
