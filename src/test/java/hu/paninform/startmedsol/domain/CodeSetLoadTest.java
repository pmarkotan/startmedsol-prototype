package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class CodeSetLoadTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CodeSetLoad.class);
        CodeSetLoad codeSetLoad1 = new CodeSetLoad();
        codeSetLoad1.setId(1L);
        CodeSetLoad codeSetLoad2 = new CodeSetLoad();
        codeSetLoad2.setId(codeSetLoad1.getId());
        assertThat(codeSetLoad1).isEqualTo(codeSetLoad2);
        codeSetLoad2.setId(2L);
        assertThat(codeSetLoad1).isNotEqualTo(codeSetLoad2);
        codeSetLoad1.setId(null);
        assertThat(codeSetLoad1).isNotEqualTo(codeSetLoad2);
    }
}
