package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class BillingInformationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BillingInformation.class);
        BillingInformation billingInformation1 = new BillingInformation();
        billingInformation1.setId(1L);
        BillingInformation billingInformation2 = new BillingInformation();
        billingInformation2.setId(billingInformation1.getId());
        assertThat(billingInformation1).isEqualTo(billingInformation2);
        billingInformation2.setId(2L);
        assertThat(billingInformation1).isNotEqualTo(billingInformation2);
        billingInformation1.setId(null);
        assertThat(billingInformation1).isNotEqualTo(billingInformation2);
    }
}
