package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphMotivationGroupTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphMotivationGroup.class);
        PphMotivationGroup pphMotivationGroup1 = new PphMotivationGroup();
        pphMotivationGroup1.setId(1L);
        PphMotivationGroup pphMotivationGroup2 = new PphMotivationGroup();
        pphMotivationGroup2.setId(pphMotivationGroup1.getId());
        assertThat(pphMotivationGroup1).isEqualTo(pphMotivationGroup2);
        pphMotivationGroup2.setId(2L);
        assertThat(pphMotivationGroup1).isNotEqualTo(pphMotivationGroup2);
        pphMotivationGroup1.setId(null);
        assertThat(pphMotivationGroup1).isNotEqualTo(pphMotivationGroup2);
    }
}
