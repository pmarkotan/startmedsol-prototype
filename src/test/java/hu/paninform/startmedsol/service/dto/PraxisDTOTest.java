package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PraxisDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PraxisDTO.class);
        PraxisDTO praxisDTO1 = new PraxisDTO();
        praxisDTO1.setId(1L);
        PraxisDTO praxisDTO2 = new PraxisDTO();
        assertThat(praxisDTO1).isNotEqualTo(praxisDTO2);
        praxisDTO2.setId(praxisDTO1.getId());
        assertThat(praxisDTO1).isEqualTo(praxisDTO2);
        praxisDTO2.setId(2L);
        assertThat(praxisDTO1).isNotEqualTo(praxisDTO2);
        praxisDTO1.setId(null);
        assertThat(praxisDTO1).isNotEqualTo(praxisDTO2);
    }
}
