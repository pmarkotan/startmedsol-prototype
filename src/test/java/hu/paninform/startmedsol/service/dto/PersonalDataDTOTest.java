package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PersonalDataDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonalDataDTO.class);
        PersonalDataDTO personalDataDTO1 = new PersonalDataDTO();
        personalDataDTO1.setId(1L);
        PersonalDataDTO personalDataDTO2 = new PersonalDataDTO();
        assertThat(personalDataDTO1).isNotEqualTo(personalDataDTO2);
        personalDataDTO2.setId(personalDataDTO1.getId());
        assertThat(personalDataDTO1).isEqualTo(personalDataDTO2);
        personalDataDTO2.setId(2L);
        assertThat(personalDataDTO1).isNotEqualTo(personalDataDTO2);
        personalDataDTO1.setId(null);
        assertThat(personalDataDTO1).isNotEqualTo(personalDataDTO2);
    }
}
