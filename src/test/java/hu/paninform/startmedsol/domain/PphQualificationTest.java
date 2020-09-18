package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphQualificationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphQualification.class);
        PphQualification pphQualification1 = new PphQualification();
        pphQualification1.setId(1L);
        PphQualification pphQualification2 = new PphQualification();
        pphQualification2.setId(pphQualification1.getId());
        assertThat(pphQualification1).isEqualTo(pphQualification2);
        pphQualification2.setId(2L);
        assertThat(pphQualification1).isNotEqualTo(pphQualification2);
        pphQualification1.setId(null);
        assertThat(pphQualification1).isNotEqualTo(pphQualification2);
    }
}
