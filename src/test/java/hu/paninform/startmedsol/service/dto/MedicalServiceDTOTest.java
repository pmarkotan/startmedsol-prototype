package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class MedicalServiceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicalServiceDTO.class);
        MedicalServiceDTO medicalServiceDTO1 = new MedicalServiceDTO();
        medicalServiceDTO1.setId(1L);
        MedicalServiceDTO medicalServiceDTO2 = new MedicalServiceDTO();
        assertThat(medicalServiceDTO1).isNotEqualTo(medicalServiceDTO2);
        medicalServiceDTO2.setId(medicalServiceDTO1.getId());
        assertThat(medicalServiceDTO1).isEqualTo(medicalServiceDTO2);
        medicalServiceDTO2.setId(2L);
        assertThat(medicalServiceDTO1).isNotEqualTo(medicalServiceDTO2);
        medicalServiceDTO1.setId(null);
        assertThat(medicalServiceDTO1).isNotEqualTo(medicalServiceDTO2);
    }
}
