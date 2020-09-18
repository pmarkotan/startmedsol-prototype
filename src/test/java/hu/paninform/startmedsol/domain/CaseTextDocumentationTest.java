package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class CaseTextDocumentationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaseTextDocumentation.class);
        CaseTextDocumentation caseTextDocumentation1 = new CaseTextDocumentation();
        caseTextDocumentation1.setId(1L);
        CaseTextDocumentation caseTextDocumentation2 = new CaseTextDocumentation();
        caseTextDocumentation2.setId(caseTextDocumentation1.getId());
        assertThat(caseTextDocumentation1).isEqualTo(caseTextDocumentation2);
        caseTextDocumentation2.setId(2L);
        assertThat(caseTextDocumentation1).isNotEqualTo(caseTextDocumentation2);
        caseTextDocumentation1.setId(null);
        assertThat(caseTextDocumentation1).isNotEqualTo(caseTextDocumentation2);
    }
}
