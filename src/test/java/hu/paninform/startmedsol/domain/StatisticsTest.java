package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class StatisticsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Statistics.class);
        Statistics statistics1 = new Statistics();
        statistics1.setId(1L);
        Statistics statistics2 = new Statistics();
        statistics2.setId(statistics1.getId());
        assertThat(statistics1).isEqualTo(statistics2);
        statistics2.setId(2L);
        assertThat(statistics1).isNotEqualTo(statistics2);
        statistics1.setId(null);
        assertThat(statistics1).isNotEqualTo(statistics2);
    }
}
