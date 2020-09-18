package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class ExternalDocumentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExternalDocument.class);
        ExternalDocument externalDocument1 = new ExternalDocument();
        externalDocument1.setId(1L);
        ExternalDocument externalDocument2 = new ExternalDocument();
        externalDocument2.setId(externalDocument1.getId());
        assertThat(externalDocument1).isEqualTo(externalDocument2);
        externalDocument2.setId(2L);
        assertThat(externalDocument1).isNotEqualTo(externalDocument2);
        externalDocument1.setId(null);
        assertThat(externalDocument1).isNotEqualTo(externalDocument2);
    }
}
