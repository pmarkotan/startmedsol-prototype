package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class CsCountryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CsCountry.class);
        CsCountry csCountry1 = new CsCountry();
        csCountry1.setId(1L);
        CsCountry csCountry2 = new CsCountry();
        csCountry2.setId(csCountry1.getId());
        assertThat(csCountry1).isEqualTo(csCountry2);
        csCountry2.setId(2L);
        assertThat(csCountry1).isNotEqualTo(csCountry2);
        csCountry1.setId(null);
        assertThat(csCountry1).isNotEqualTo(csCountry2);
    }
}
