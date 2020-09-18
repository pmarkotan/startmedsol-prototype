package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class MedicalCaseDiagnosisDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicalCaseDiagnosisDTO.class);
        MedicalCaseDiagnosisDTO medicalCaseDiagnosisDTO1 = new MedicalCaseDiagnosisDTO();
        medicalCaseDiagnosisDTO1.setId(1L);
        MedicalCaseDiagnosisDTO medicalCaseDiagnosisDTO2 = new MedicalCaseDiagnosisDTO();
        assertThat(medicalCaseDiagnosisDTO1).isNotEqualTo(medicalCaseDiagnosisDTO2);
        medicalCaseDiagnosisDTO2.setId(medicalCaseDiagnosisDTO1.getId());
        assertThat(medicalCaseDiagnosisDTO1).isEqualTo(medicalCaseDiagnosisDTO2);
        medicalCaseDiagnosisDTO2.setId(2L);
        assertThat(medicalCaseDiagnosisDTO1).isNotEqualTo(medicalCaseDiagnosisDTO2);
        medicalCaseDiagnosisDTO1.setId(null);
        assertThat(medicalCaseDiagnosisDTO1).isNotEqualTo(medicalCaseDiagnosisDTO2);
    }
}
