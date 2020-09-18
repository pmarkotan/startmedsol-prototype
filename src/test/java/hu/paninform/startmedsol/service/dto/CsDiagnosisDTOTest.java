package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class CsDiagnosisDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CsDiagnosisDTO.class);
        CsDiagnosisDTO csDiagnosisDTO1 = new CsDiagnosisDTO();
        csDiagnosisDTO1.setId(1L);
        CsDiagnosisDTO csDiagnosisDTO2 = new CsDiagnosisDTO();
        assertThat(csDiagnosisDTO1).isNotEqualTo(csDiagnosisDTO2);
        csDiagnosisDTO2.setId(csDiagnosisDTO1.getId());
        assertThat(csDiagnosisDTO1).isEqualTo(csDiagnosisDTO2);
        csDiagnosisDTO2.setId(2L);
        assertThat(csDiagnosisDTO1).isNotEqualTo(csDiagnosisDTO2);
        csDiagnosisDTO1.setId(null);
        assertThat(csDiagnosisDTO1).isNotEqualTo(csDiagnosisDTO2);
    }
}
