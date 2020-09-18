package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class CaseTextDocumentationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseTextDocumentationDTO.class);
        CaseTextDocumentationDTO caseTextDocumentationDTO1 = new CaseTextDocumentationDTO();
        caseTextDocumentationDTO1.setId(1L);
        CaseTextDocumentationDTO caseTextDocumentationDTO2 = new CaseTextDocumentationDTO();
        assertThat(caseTextDocumentationDTO1).isNotEqualTo(caseTextDocumentationDTO2);
        caseTextDocumentationDTO2.setId(caseTextDocumentationDTO1.getId());
        assertThat(caseTextDocumentationDTO1).isEqualTo(caseTextDocumentationDTO2);
        caseTextDocumentationDTO2.setId(2L);
        assertThat(caseTextDocumentationDTO1).isNotEqualTo(caseTextDocumentationDTO2);
        caseTextDocumentationDTO1.setId(null);
        assertThat(caseTextDocumentationDTO1).isNotEqualTo(caseTextDocumentationDTO2);
    }
}
