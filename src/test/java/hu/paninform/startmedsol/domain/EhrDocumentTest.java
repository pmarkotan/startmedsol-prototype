package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class EhrDocumentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EhrDocument.class);
        EhrDocument ehrDocument1 = new EhrDocument();
        ehrDocument1.setId(1L);
        EhrDocument ehrDocument2 = new EhrDocument();
        ehrDocument2.setId(ehrDocument1.getId());
        assertThat(ehrDocument1).isEqualTo(ehrDocument2);
        ehrDocument2.setId(2L);
        assertThat(ehrDocument1).isNotEqualTo(ehrDocument2);
        ehrDocument1.setId(null);
        assertThat(ehrDocument1).isNotEqualTo(ehrDocument2);
    }
}
