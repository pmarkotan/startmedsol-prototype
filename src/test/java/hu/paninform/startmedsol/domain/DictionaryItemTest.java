package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class DictionaryItemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DictionaryItem.class);
        DictionaryItem dictionaryItem1 = new DictionaryItem();
        dictionaryItem1.setId(1L);
        DictionaryItem dictionaryItem2 = new DictionaryItem();
        dictionaryItem2.setId(dictionaryItem1.getId());
        assertThat(dictionaryItem1).isEqualTo(dictionaryItem2);
        dictionaryItem2.setId(2L);
        assertThat(dictionaryItem1).isNotEqualTo(dictionaryItem2);
        dictionaryItem1.setId(null);
        assertThat(dictionaryItem1).isNotEqualTo(dictionaryItem2);
    }
}
