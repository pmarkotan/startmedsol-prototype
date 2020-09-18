package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphMedicineFormTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphMedicineForm.class);
        PphMedicineForm pphMedicineForm1 = new PphMedicineForm();
        pphMedicineForm1.setId(1L);
        PphMedicineForm pphMedicineForm2 = new PphMedicineForm();
        pphMedicineForm2.setId(pphMedicineForm1.getId());
        assertThat(pphMedicineForm1).isEqualTo(pphMedicineForm2);
        pphMedicineForm2.setId(2L);
        assertThat(pphMedicineForm1).isNotEqualTo(pphMedicineForm2);
        pphMedicineForm1.setId(null);
        assertThat(pphMedicineForm1).isNotEqualTo(pphMedicineForm2);
    }
}
