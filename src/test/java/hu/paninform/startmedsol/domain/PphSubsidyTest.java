package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphSubsidyTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphSubsidy.class);
        PphSubsidy pphSubsidy1 = new PphSubsidy();
        pphSubsidy1.setId(1L);
        PphSubsidy pphSubsidy2 = new PphSubsidy();
        pphSubsidy2.setId(pphSubsidy1.getId());
        assertThat(pphSubsidy1).isEqualTo(pphSubsidy2);
        pphSubsidy2.setId(2L);
        assertThat(pphSubsidy1).isNotEqualTo(pphSubsidy2);
        pphSubsidy1.setId(null);
        assertThat(pphSubsidy1).isNotEqualTo(pphSubsidy2);
    }
}
