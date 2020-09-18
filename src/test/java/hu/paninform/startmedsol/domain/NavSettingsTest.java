package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class NavSettingsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NavSettings.class);
        NavSettings navSettings1 = new NavSettings();
        navSettings1.setId(1L);
        NavSettings navSettings2 = new NavSettings();
        navSettings2.setId(navSettings1.getId());
        assertThat(navSettings1).isEqualTo(navSettings2);
        navSettings2.setId(2L);
        assertThat(navSettings1).isNotEqualTo(navSettings2);
        navSettings1.setId(null);
        assertThat(navSettings1).isNotEqualTo(navSettings2);
    }
}
