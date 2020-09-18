package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class SpecialistsAdviceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SpecialistsAdviceDTO.class);
        SpecialistsAdviceDTO specialistsAdviceDTO1 = new SpecialistsAdviceDTO();
        specialistsAdviceDTO1.setId(1L);
        SpecialistsAdviceDTO specialistsAdviceDTO2 = new SpecialistsAdviceDTO();
        assertThat(specialistsAdviceDTO1).isNotEqualTo(specialistsAdviceDTO2);
        specialistsAdviceDTO2.setId(specialistsAdviceDTO1.getId());
        assertThat(specialistsAdviceDTO1).isEqualTo(specialistsAdviceDTO2);
        specialistsAdviceDTO2.setId(2L);
        assertThat(specialistsAdviceDTO1).isNotEqualTo(specialistsAdviceDTO2);
        specialistsAdviceDTO1.setId(null);
        assertThat(specialistsAdviceDTO1).isNotEqualTo(specialistsAdviceDTO2);
    }
}
