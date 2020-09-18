package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class ExternalDocumentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExternalDocumentDTO.class);
        ExternalDocumentDTO externalDocumentDTO1 = new ExternalDocumentDTO();
        externalDocumentDTO1.setId(1L);
        ExternalDocumentDTO externalDocumentDTO2 = new ExternalDocumentDTO();
        assertThat(externalDocumentDTO1).isNotEqualTo(externalDocumentDTO2);
        externalDocumentDTO2.setId(externalDocumentDTO1.getId());
        assertThat(externalDocumentDTO1).isEqualTo(externalDocumentDTO2);
        externalDocumentDTO2.setId(2L);
        assertThat(externalDocumentDTO1).isNotEqualTo(externalDocumentDTO2);
        externalDocumentDTO1.setId(null);
        assertThat(externalDocumentDTO1).isNotEqualTo(externalDocumentDTO2);
    }
}
