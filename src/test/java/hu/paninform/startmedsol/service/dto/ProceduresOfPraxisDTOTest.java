package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class ProceduresOfPraxisDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProceduresOfPraxisDTO.class);
        ProceduresOfPraxisDTO proceduresOfPraxisDTO1 = new ProceduresOfPraxisDTO();
        proceduresOfPraxisDTO1.setId(1L);
        ProceduresOfPraxisDTO proceduresOfPraxisDTO2 = new ProceduresOfPraxisDTO();
        assertThat(proceduresOfPraxisDTO1).isNotEqualTo(proceduresOfPraxisDTO2);
        proceduresOfPraxisDTO2.setId(proceduresOfPraxisDTO1.getId());
        assertThat(proceduresOfPraxisDTO1).isEqualTo(proceduresOfPraxisDTO2);
        proceduresOfPraxisDTO2.setId(2L);
        assertThat(proceduresOfPraxisDTO1).isNotEqualTo(proceduresOfPraxisDTO2);
        proceduresOfPraxisDTO1.setId(null);
        assertThat(proceduresOfPraxisDTO1).isNotEqualTo(proceduresOfPraxisDTO2);
    }
}
