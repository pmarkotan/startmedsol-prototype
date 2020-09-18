package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PuphaLoaderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PuphaLoader.class);
        PuphaLoader puphaLoader1 = new PuphaLoader();
        puphaLoader1.setId(1L);
        PuphaLoader puphaLoader2 = new PuphaLoader();
        puphaLoader2.setId(puphaLoader1.getId());
        assertThat(puphaLoader1).isEqualTo(puphaLoader2);
        puphaLoader2.setId(2L);
        assertThat(puphaLoader1).isNotEqualTo(puphaLoader2);
        puphaLoader1.setId(null);
        assertThat(puphaLoader1).isNotEqualTo(puphaLoader2);
    }
}
