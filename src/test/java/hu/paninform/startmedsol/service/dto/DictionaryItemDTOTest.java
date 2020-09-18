package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class DictionaryItemDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DictionaryItemDTO.class);
        DictionaryItemDTO dictionaryItemDTO1 = new DictionaryItemDTO();
        dictionaryItemDTO1.setId(1L);
        DictionaryItemDTO dictionaryItemDTO2 = new DictionaryItemDTO();
        assertThat(dictionaryItemDTO1).isNotEqualTo(dictionaryItemDTO2);
        dictionaryItemDTO2.setId(dictionaryItemDTO1.getId());
        assertThat(dictionaryItemDTO1).isEqualTo(dictionaryItemDTO2);
        dictionaryItemDTO2.setId(2L);
        assertThat(dictionaryItemDTO1).isNotEqualTo(dictionaryItemDTO2);
        dictionaryItemDTO1.setId(null);
        assertThat(dictionaryItemDTO1).isNotEqualTo(dictionaryItemDTO2);
    }
}
