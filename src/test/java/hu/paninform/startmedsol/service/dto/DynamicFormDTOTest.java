package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class DynamicFormDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DynamicFormDTO.class);
        DynamicFormDTO dynamicFormDTO1 = new DynamicFormDTO();
        dynamicFormDTO1.setId(1L);
        DynamicFormDTO dynamicFormDTO2 = new DynamicFormDTO();
        assertThat(dynamicFormDTO1).isNotEqualTo(dynamicFormDTO2);
        dynamicFormDTO2.setId(dynamicFormDTO1.getId());
        assertThat(dynamicFormDTO1).isEqualTo(dynamicFormDTO2);
        dynamicFormDTO2.setId(2L);
        assertThat(dynamicFormDTO1).isNotEqualTo(dynamicFormDTO2);
        dynamicFormDTO1.setId(null);
        assertThat(dynamicFormDTO1).isNotEqualTo(dynamicFormDTO2);
    }
}
