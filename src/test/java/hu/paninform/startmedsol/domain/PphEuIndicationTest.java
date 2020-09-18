package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphEuIndicationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphEuIndication.class);
        PphEuIndication pphEuIndication1 = new PphEuIndication();
        pphEuIndication1.setId(1L);
        PphEuIndication pphEuIndication2 = new PphEuIndication();
        pphEuIndication2.setId(pphEuIndication1.getId());
        assertThat(pphEuIndication1).isEqualTo(pphEuIndication2);
        pphEuIndication2.setId(2L);
        assertThat(pphEuIndication1).isNotEqualTo(pphEuIndication2);
        pphEuIndication1.setId(null);
        assertThat(pphEuIndication1).isNotEqualTo(pphEuIndication2);
    }
}
