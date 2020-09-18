package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class CsPostalCodeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CsPostalCode.class);
        CsPostalCode csPostalCode1 = new CsPostalCode();
        csPostalCode1.setId(1L);
        CsPostalCode csPostalCode2 = new CsPostalCode();
        csPostalCode2.setId(csPostalCode1.getId());
        assertThat(csPostalCode1).isEqualTo(csPostalCode2);
        csPostalCode2.setId(2L);
        assertThat(csPostalCode1).isNotEqualTo(csPostalCode2);
        csPostalCode1.setId(null);
        assertThat(csPostalCode1).isNotEqualTo(csPostalCode2);
    }
}
