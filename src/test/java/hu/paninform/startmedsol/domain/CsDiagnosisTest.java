package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class CsDiagnosisTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CsDiagnosis.class);
        CsDiagnosis csDiagnosis1 = new CsDiagnosis();
        csDiagnosis1.setId(1L);
        CsDiagnosis csDiagnosis2 = new CsDiagnosis();
        csDiagnosis2.setId(csDiagnosis1.getId());
        assertThat(csDiagnosis1).isEqualTo(csDiagnosis2);
        csDiagnosis2.setId(2L);
        assertThat(csDiagnosis1).isNotEqualTo(csDiagnosis2);
        csDiagnosis1.setId(null);
        assertThat(csDiagnosis1).isNotEqualTo(csDiagnosis2);
    }
}
