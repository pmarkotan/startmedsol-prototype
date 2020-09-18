package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PerformedProcedureTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PerformedProcedure.class);
        PerformedProcedure performedProcedure1 = new PerformedProcedure();
        performedProcedure1.setId(1L);
        PerformedProcedure performedProcedure2 = new PerformedProcedure();
        performedProcedure2.setId(performedProcedure1.getId());
        assertThat(performedProcedure1).isEqualTo(performedProcedure2);
        performedProcedure2.setId(2L);
        assertThat(performedProcedure1).isNotEqualTo(performedProcedure2);
        performedProcedure1.setId(null);
        assertThat(performedProcedure1).isNotEqualTo(performedProcedure2);
    }
}
