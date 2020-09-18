package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class CsCountryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CsCountryDTO.class);
        CsCountryDTO csCountryDTO1 = new CsCountryDTO();
        csCountryDTO1.setId(1L);
        CsCountryDTO csCountryDTO2 = new CsCountryDTO();
        assertThat(csCountryDTO1).isNotEqualTo(csCountryDTO2);
        csCountryDTO2.setId(csCountryDTO1.getId());
        assertThat(csCountryDTO1).isEqualTo(csCountryDTO2);
        csCountryDTO2.setId(2L);
        assertThat(csCountryDTO1).isNotEqualTo(csCountryDTO2);
        csCountryDTO1.setId(null);
        assertThat(csCountryDTO1).isNotEqualTo(csCountryDTO2);
    }
}
