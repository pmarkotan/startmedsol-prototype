package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class ValidityDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ValidityDTO.class);
        ValidityDTO validityDTO1 = new ValidityDTO();
        validityDTO1.setId(1L);
        ValidityDTO validityDTO2 = new ValidityDTO();
        assertThat(validityDTO1).isNotEqualTo(validityDTO2);
        validityDTO2.setId(validityDTO1.getId());
        assertThat(validityDTO1).isEqualTo(validityDTO2);
        validityDTO2.setId(2L);
        assertThat(validityDTO1).isNotEqualTo(validityDTO2);
        validityDTO1.setId(null);
        assertThat(validityDTO1).isNotEqualTo(validityDTO2);
    }
}
