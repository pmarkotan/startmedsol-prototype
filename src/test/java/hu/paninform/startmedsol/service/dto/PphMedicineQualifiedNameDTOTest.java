package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphMedicineQualifiedNameDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphMedicineQualifiedNameDTO.class);
        PphMedicineQualifiedNameDTO pphMedicineQualifiedNameDTO1 = new PphMedicineQualifiedNameDTO();
        pphMedicineQualifiedNameDTO1.setId(1L);
        PphMedicineQualifiedNameDTO pphMedicineQualifiedNameDTO2 = new PphMedicineQualifiedNameDTO();
        assertThat(pphMedicineQualifiedNameDTO1).isNotEqualTo(pphMedicineQualifiedNameDTO2);
        pphMedicineQualifiedNameDTO2.setId(pphMedicineQualifiedNameDTO1.getId());
        assertThat(pphMedicineQualifiedNameDTO1).isEqualTo(pphMedicineQualifiedNameDTO2);
        pphMedicineQualifiedNameDTO2.setId(2L);
        assertThat(pphMedicineQualifiedNameDTO1).isNotEqualTo(pphMedicineQualifiedNameDTO2);
        pphMedicineQualifiedNameDTO1.setId(null);
        assertThat(pphMedicineQualifiedNameDTO1).isNotEqualTo(pphMedicineQualifiedNameDTO2);
    }
}
