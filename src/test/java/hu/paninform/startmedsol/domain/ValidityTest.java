package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class ValidityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Validity.class);
        Validity validity1 = new Validity();
        validity1.setId(1L);
        Validity validity2 = new Validity();
        validity2.setId(validity1.getId());
        assertThat(validity1).isEqualTo(validity2);
        validity2.setId(2L);
        assertThat(validity1).isNotEqualTo(validity2);
        validity1.setId(null);
        assertThat(validity1).isNotEqualTo(validity2);
    }
}
