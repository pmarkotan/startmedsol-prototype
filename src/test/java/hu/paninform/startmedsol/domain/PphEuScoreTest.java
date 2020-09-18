package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphEuScoreTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphEuScore.class);
        PphEuScore pphEuScore1 = new PphEuScore();
        pphEuScore1.setId(1L);
        PphEuScore pphEuScore2 = new PphEuScore();
        pphEuScore2.setId(pphEuScore1.getId());
        assertThat(pphEuScore1).isEqualTo(pphEuScore2);
        pphEuScore2.setId(2L);
        assertThat(pphEuScore1).isNotEqualTo(pphEuScore2);
        pphEuScore1.setId(null);
        assertThat(pphEuScore1).isNotEqualTo(pphEuScore2);
    }
}
