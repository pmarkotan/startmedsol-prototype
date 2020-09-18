package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class BillingInformationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillingInformationDTO.class);
        BillingInformationDTO billingInformationDTO1 = new BillingInformationDTO();
        billingInformationDTO1.setId(1L);
        BillingInformationDTO billingInformationDTO2 = new BillingInformationDTO();
        assertThat(billingInformationDTO1).isNotEqualTo(billingInformationDTO2);
        billingInformationDTO2.setId(billingInformationDTO1.getId());
        assertThat(billingInformationDTO1).isEqualTo(billingInformationDTO2);
        billingInformationDTO2.setId(2L);
        assertThat(billingInformationDTO1).isNotEqualTo(billingInformationDTO2);
        billingInformationDTO1.setId(null);
        assertThat(billingInformationDTO1).isNotEqualTo(billingInformationDTO2);
    }
}
