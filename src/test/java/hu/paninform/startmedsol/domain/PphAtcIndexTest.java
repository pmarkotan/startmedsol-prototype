package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphAtcIndexTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphAtcIndex.class);
        PphAtcIndex pphAtcIndex1 = new PphAtcIndex();
        pphAtcIndex1.setId(1L);
        PphAtcIndex pphAtcIndex2 = new PphAtcIndex();
        pphAtcIndex2.setId(pphAtcIndex1.getId());
        assertThat(pphAtcIndex1).isEqualTo(pphAtcIndex2);
        pphAtcIndex2.setId(2L);
        assertThat(pphAtcIndex1).isNotEqualTo(pphAtcIndex2);
        pphAtcIndex1.setId(null);
        assertThat(pphAtcIndex1).isNotEqualTo(pphAtcIndex2);
    }
}
