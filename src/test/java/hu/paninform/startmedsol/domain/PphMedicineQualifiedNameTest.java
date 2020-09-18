package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphMedicineQualifiedNameTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphMedicineQualifiedName.class);
        PphMedicineQualifiedName pphMedicineQualifiedName1 = new PphMedicineQualifiedName();
        pphMedicineQualifiedName1.setId(1L);
        PphMedicineQualifiedName pphMedicineQualifiedName2 = new PphMedicineQualifiedName();
        pphMedicineQualifiedName2.setId(pphMedicineQualifiedName1.getId());
        assertThat(pphMedicineQualifiedName1).isEqualTo(pphMedicineQualifiedName2);
        pphMedicineQualifiedName2.setId(2L);
        assertThat(pphMedicineQualifiedName1).isNotEqualTo(pphMedicineQualifiedName2);
        pphMedicineQualifiedName1.setId(null);
        assertThat(pphMedicineQualifiedName1).isNotEqualTo(pphMedicineQualifiedName2);
    }
}
