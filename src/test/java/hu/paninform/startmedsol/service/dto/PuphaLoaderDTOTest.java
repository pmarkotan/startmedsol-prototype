package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PuphaLoaderDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PuphaLoaderDTO.class);
        PuphaLoaderDTO puphaLoaderDTO1 = new PuphaLoaderDTO();
        puphaLoaderDTO1.setId(1L);
        PuphaLoaderDTO puphaLoaderDTO2 = new PuphaLoaderDTO();
        assertThat(puphaLoaderDTO1).isNotEqualTo(puphaLoaderDTO2);
        puphaLoaderDTO2.setId(puphaLoaderDTO1.getId());
        assertThat(puphaLoaderDTO1).isEqualTo(puphaLoaderDTO2);
        puphaLoaderDTO2.setId(2L);
        assertThat(puphaLoaderDTO1).isNotEqualTo(puphaLoaderDTO2);
        puphaLoaderDTO1.setId(null);
        assertThat(puphaLoaderDTO1).isNotEqualTo(puphaLoaderDTO2);
    }
}
