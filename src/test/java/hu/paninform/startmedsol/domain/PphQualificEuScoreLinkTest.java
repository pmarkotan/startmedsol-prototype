package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphQualificEuScoreLinkTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphQualificEuScoreLink.class);
        PphQualificEuScoreLink pphQualificEuScoreLink1 = new PphQualificEuScoreLink();
        pphQualificEuScoreLink1.setId(1L);
        PphQualificEuScoreLink pphQualificEuScoreLink2 = new PphQualificEuScoreLink();
        pphQualificEuScoreLink2.setId(pphQualificEuScoreLink1.getId());
        assertThat(pphQualificEuScoreLink1).isEqualTo(pphQualificEuScoreLink2);
        pphQualificEuScoreLink2.setId(2L);
        assertThat(pphQualificEuScoreLink1).isNotEqualTo(pphQualificEuScoreLink2);
        pphQualificEuScoreLink1.setId(null);
        assertThat(pphQualificEuScoreLink1).isNotEqualTo(pphQualificEuScoreLink2);
    }
}
