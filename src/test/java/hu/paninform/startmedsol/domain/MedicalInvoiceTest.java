package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class MedicalInvoiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicalInvoice.class);
        MedicalInvoice medicalInvoice1 = new MedicalInvoice();
        medicalInvoice1.setId(1L);
        MedicalInvoice medicalInvoice2 = new MedicalInvoice();
        medicalInvoice2.setId(medicalInvoice1.getId());
        assertThat(medicalInvoice1).isEqualTo(medicalInvoice2);
        medicalInvoice2.setId(2L);
        assertThat(medicalInvoice1).isNotEqualTo(medicalInvoice2);
        medicalInvoice1.setId(null);
        assertThat(medicalInvoice1).isNotEqualTo(medicalInvoice2);
    }
}
