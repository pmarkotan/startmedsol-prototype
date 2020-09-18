package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class CsSenderOrganizationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CsSenderOrganization.class);
        CsSenderOrganization csSenderOrganization1 = new CsSenderOrganization();
        csSenderOrganization1.setId(1L);
        CsSenderOrganization csSenderOrganization2 = new CsSenderOrganization();
        csSenderOrganization2.setId(csSenderOrganization1.getId());
        assertThat(csSenderOrganization1).isEqualTo(csSenderOrganization2);
        csSenderOrganization2.setId(2L);
        assertThat(csSenderOrganization1).isNotEqualTo(csSenderOrganization2);
        csSenderOrganization1.setId(null);
        assertThat(csSenderOrganization1).isNotEqualTo(csSenderOrganization2);
    }
}
