package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphAidISOBookTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphAidISOBook.class);
        PphAidISOBook pphAidISOBook1 = new PphAidISOBook();
        pphAidISOBook1.setId(1L);
        PphAidISOBook pphAidISOBook2 = new PphAidISOBook();
        pphAidISOBook2.setId(pphAidISOBook1.getId());
        assertThat(pphAidISOBook1).isEqualTo(pphAidISOBook2);
        pphAidISOBook2.setId(2L);
        assertThat(pphAidISOBook1).isNotEqualTo(pphAidISOBook2);
        pphAidISOBook1.setId(null);
        assertThat(pphAidISOBook1).isNotEqualTo(pphAidISOBook2);
    }
}
