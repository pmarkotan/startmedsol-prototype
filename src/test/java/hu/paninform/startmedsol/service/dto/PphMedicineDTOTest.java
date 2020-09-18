package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphMedicineDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphMedicineDTO.class);
        PphMedicineDTO pphMedicineDTO1 = new PphMedicineDTO();
        pphMedicineDTO1.setId(1L);
        PphMedicineDTO pphMedicineDTO2 = new PphMedicineDTO();
        assertThat(pphMedicineDTO1).isNotEqualTo(pphMedicineDTO2);
        pphMedicineDTO2.setId(pphMedicineDTO1.getId());
        assertThat(pphMedicineDTO1).isEqualTo(pphMedicineDTO2);
        pphMedicineDTO2.setId(2L);
        assertThat(pphMedicineDTO1).isNotEqualTo(pphMedicineDTO2);
        pphMedicineDTO1.setId(null);
        assertThat(pphMedicineDTO1).isNotEqualTo(pphMedicineDTO2);
    }
}
