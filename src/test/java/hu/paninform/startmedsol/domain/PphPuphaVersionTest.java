package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphPuphaVersionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphPuphaVersion.class);
        PphPuphaVersion pphPuphaVersion1 = new PphPuphaVersion();
        pphPuphaVersion1.setId(1L);
        PphPuphaVersion pphPuphaVersion2 = new PphPuphaVersion();
        pphPuphaVersion2.setId(pphPuphaVersion1.getId());
        assertThat(pphPuphaVersion1).isEqualTo(pphPuphaVersion2);
        pphPuphaVersion2.setId(2L);
        assertThat(pphPuphaVersion1).isNotEqualTo(pphPuphaVersion2);
        pphPuphaVersion1.setId(null);
        assertThat(pphPuphaVersion1).isNotEqualTo(pphPuphaVersion2);
    }
}
