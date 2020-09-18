package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class CsProcedureTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CsProcedure.class);
        CsProcedure csProcedure1 = new CsProcedure();
        csProcedure1.setId(1L);
        CsProcedure csProcedure2 = new CsProcedure();
        csProcedure2.setId(csProcedure1.getId());
        assertThat(csProcedure1).isEqualTo(csProcedure2);
        csProcedure2.setId(2L);
        assertThat(csProcedure1).isNotEqualTo(csProcedure2);
        csProcedure1.setId(null);
        assertThat(csProcedure1).isNotEqualTo(csProcedure2);
    }
}
