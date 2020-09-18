package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphCompanyTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphCompany.class);
        PphCompany pphCompany1 = new PphCompany();
        pphCompany1.setId(1L);
        PphCompany pphCompany2 = new PphCompany();
        pphCompany2.setId(pphCompany1.getId());
        assertThat(pphCompany1).isEqualTo(pphCompany2);
        pphCompany2.setId(2L);
        assertThat(pphCompany1).isNotEqualTo(pphCompany2);
        pphCompany1.setId(null);
        assertThat(pphCompany1).isNotEqualTo(pphCompany2);
    }
}
