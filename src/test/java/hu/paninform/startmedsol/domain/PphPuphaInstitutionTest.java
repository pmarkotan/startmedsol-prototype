package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphPuphaInstitutionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphPuphaInstitution.class);
        PphPuphaInstitution pphPuphaInstitution1 = new PphPuphaInstitution();
        pphPuphaInstitution1.setId(1L);
        PphPuphaInstitution pphPuphaInstitution2 = new PphPuphaInstitution();
        pphPuphaInstitution2.setId(pphPuphaInstitution1.getId());
        assertThat(pphPuphaInstitution1).isEqualTo(pphPuphaInstitution2);
        pphPuphaInstitution2.setId(2L);
        assertThat(pphPuphaInstitution1).isNotEqualTo(pphPuphaInstitution2);
        pphPuphaInstitution1.setId(null);
        assertThat(pphPuphaInstitution1).isNotEqualTo(pphPuphaInstitution2);
    }
}
