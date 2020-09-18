package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class DataGeneratorDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataGeneratorDTO.class);
        DataGeneratorDTO dataGeneratorDTO1 = new DataGeneratorDTO();
        dataGeneratorDTO1.setId(1L);
        DataGeneratorDTO dataGeneratorDTO2 = new DataGeneratorDTO();
        assertThat(dataGeneratorDTO1).isNotEqualTo(dataGeneratorDTO2);
        dataGeneratorDTO2.setId(dataGeneratorDTO1.getId());
        assertThat(dataGeneratorDTO1).isEqualTo(dataGeneratorDTO2);
        dataGeneratorDTO2.setId(2L);
        assertThat(dataGeneratorDTO1).isNotEqualTo(dataGeneratorDTO2);
        dataGeneratorDTO1.setId(null);
        assertThat(dataGeneratorDTO1).isNotEqualTo(dataGeneratorDTO2);
    }
}
