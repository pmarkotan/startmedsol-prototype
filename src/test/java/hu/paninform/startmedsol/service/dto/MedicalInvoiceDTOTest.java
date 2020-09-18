package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class MedicalInvoiceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MedicalInvoiceDTO.class);
        MedicalInvoiceDTO medicalInvoiceDTO1 = new MedicalInvoiceDTO();
        medicalInvoiceDTO1.setId(1L);
        MedicalInvoiceDTO medicalInvoiceDTO2 = new MedicalInvoiceDTO();
        assertThat(medicalInvoiceDTO1).isNotEqualTo(medicalInvoiceDTO2);
        medicalInvoiceDTO2.setId(medicalInvoiceDTO1.getId());
        assertThat(medicalInvoiceDTO1).isEqualTo(medicalInvoiceDTO2);
        medicalInvoiceDTO2.setId(2L);
        assertThat(medicalInvoiceDTO1).isNotEqualTo(medicalInvoiceDTO2);
        medicalInvoiceDTO1.setId(null);
        assertThat(medicalInvoiceDTO1).isNotEqualTo(medicalInvoiceDTO2);
    }
}
