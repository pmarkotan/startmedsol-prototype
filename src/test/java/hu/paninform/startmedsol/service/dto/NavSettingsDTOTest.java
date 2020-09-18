package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class NavSettingsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NavSettingsDTO.class);
        NavSettingsDTO navSettingsDTO1 = new NavSettingsDTO();
        navSettingsDTO1.setId(1L);
        NavSettingsDTO navSettingsDTO2 = new NavSettingsDTO();
        assertThat(navSettingsDTO1).isNotEqualTo(navSettingsDTO2);
        navSettingsDTO2.setId(navSettingsDTO1.getId());
        assertThat(navSettingsDTO1).isEqualTo(navSettingsDTO2);
        navSettingsDTO2.setId(2L);
        assertThat(navSettingsDTO1).isNotEqualTo(navSettingsDTO2);
        navSettingsDTO1.setId(null);
        assertThat(navSettingsDTO1).isNotEqualTo(navSettingsDTO2);
    }
}
