package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class MedicalCaseDiagnosisTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicalCaseDiagnosis.class);
        MedicalCaseDiagnosis medicalCaseDiagnosis1 = new MedicalCaseDiagnosis();
        medicalCaseDiagnosis1.setId(1L);
        MedicalCaseDiagnosis medicalCaseDiagnosis2 = new MedicalCaseDiagnosis();
        medicalCaseDiagnosis2.setId(medicalCaseDiagnosis1.getId());
        assertThat(medicalCaseDiagnosis1).isEqualTo(medicalCaseDiagnosis2);
        medicalCaseDiagnosis2.setId(2L);
        assertThat(medicalCaseDiagnosis1).isNotEqualTo(medicalCaseDiagnosis2);
        medicalCaseDiagnosis1.setId(null);
        assertThat(medicalCaseDiagnosis1).isNotEqualTo(medicalCaseDiagnosis2);
    }
}
