package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class DictionaryTranslationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DictionaryTranslation.class);
        DictionaryTranslation dictionaryTranslation1 = new DictionaryTranslation();
        dictionaryTranslation1.setId(1L);
        DictionaryTranslation dictionaryTranslation2 = new DictionaryTranslation();
        dictionaryTranslation2.setId(dictionaryTranslation1.getId());
        assertThat(dictionaryTranslation1).isEqualTo(dictionaryTranslation2);
        dictionaryTranslation2.setId(2L);
        assertThat(dictionaryTranslation1).isNotEqualTo(dictionaryTranslation2);
        dictionaryTranslation1.setId(null);
        assertThat(dictionaryTranslation1).isNotEqualTo(dictionaryTranslation2);
    }
}
