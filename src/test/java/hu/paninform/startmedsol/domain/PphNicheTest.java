package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphNicheTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphNiche.class);
        PphNiche pphNiche1 = new PphNiche();
        pphNiche1.setId(1L);
        PphNiche pphNiche2 = new PphNiche();
        pphNiche2.setId(pphNiche1.getId());
        assertThat(pphNiche1).isEqualTo(pphNiche2);
        pphNiche2.setId(2L);
        assertThat(pphNiche1).isNotEqualTo(pphNiche2);
        pphNiche1.setId(null);
        assertThat(pphNiche1).isNotEqualTo(pphNiche2);
    }
}
