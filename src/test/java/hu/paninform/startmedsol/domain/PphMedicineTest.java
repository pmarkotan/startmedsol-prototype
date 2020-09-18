package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphMedicineTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphMedicine.class);
        PphMedicine pphMedicine1 = new PphMedicine();
        pphMedicine1.setId(1L);
        PphMedicine pphMedicine2 = new PphMedicine();
        pphMedicine2.setId(pphMedicine1.getId());
        assertThat(pphMedicine1).isEqualTo(pphMedicine2);
        pphMedicine2.setId(2L);
        assertThat(pphMedicine1).isNotEqualTo(pphMedicine2);
        pphMedicine1.setId(null);
        assertThat(pphMedicine1).isNotEqualTo(pphMedicine2);
    }
}
