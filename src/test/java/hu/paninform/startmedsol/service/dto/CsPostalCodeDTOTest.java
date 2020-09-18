package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class CsPostalCodeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CsPostalCodeDTO.class);
        CsPostalCodeDTO csPostalCodeDTO1 = new CsPostalCodeDTO();
        csPostalCodeDTO1.setId(1L);
        CsPostalCodeDTO csPostalCodeDTO2 = new CsPostalCodeDTO();
        assertThat(csPostalCodeDTO1).isNotEqualTo(csPostalCodeDTO2);
        csPostalCodeDTO2.setId(csPostalCodeDTO1.getId());
        assertThat(csPostalCodeDTO1).isEqualTo(csPostalCodeDTO2);
        csPostalCodeDTO2.setId(2L);
        assertThat(csPostalCodeDTO1).isNotEqualTo(csPostalCodeDTO2);
        csPostalCodeDTO1.setId(null);
        assertThat(csPostalCodeDTO1).isNotEqualTo(csPostalCodeDTO2);
    }
}
