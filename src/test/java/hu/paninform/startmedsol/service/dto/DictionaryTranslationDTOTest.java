package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class DictionaryTranslationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DictionaryTranslationDTO.class);
        DictionaryTranslationDTO dictionaryTranslationDTO1 = new DictionaryTranslationDTO();
        dictionaryTranslationDTO1.setId(1L);
        DictionaryTranslationDTO dictionaryTranslationDTO2 = new DictionaryTranslationDTO();
        assertThat(dictionaryTranslationDTO1).isNotEqualTo(dictionaryTranslationDTO2);
        dictionaryTranslationDTO2.setId(dictionaryTranslationDTO1.getId());
        assertThat(dictionaryTranslationDTO1).isEqualTo(dictionaryTranslationDTO2);
        dictionaryTranslationDTO2.setId(2L);
        assertThat(dictionaryTranslationDTO1).isNotEqualTo(dictionaryTranslationDTO2);
        dictionaryTranslationDTO1.setId(null);
        assertThat(dictionaryTranslationDTO1).isNotEqualTo(dictionaryTranslationDTO2);
    }
}
