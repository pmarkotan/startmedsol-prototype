package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PraxisTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Praxis.class);
        Praxis praxis1 = new Praxis();
        praxis1.setId(1L);
        Praxis praxis2 = new Praxis();
        praxis2.setId(praxis1.getId());
        assertThat(praxis1).isEqualTo(praxis2);
        praxis2.setId(2L);
        assertThat(praxis1).isNotEqualTo(praxis2);
        praxis1.setId(null);
        assertThat(praxis1).isNotEqualTo(praxis2);
    }
}
