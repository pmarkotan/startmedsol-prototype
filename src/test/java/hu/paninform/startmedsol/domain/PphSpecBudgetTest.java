package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphSpecBudgetTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphSpecBudget.class);
        PphSpecBudget pphSpecBudget1 = new PphSpecBudget();
        pphSpecBudget1.setId(1L);
        PphSpecBudget pphSpecBudget2 = new PphSpecBudget();
        pphSpecBudget2.setId(pphSpecBudget1.getId());
        assertThat(pphSpecBudget1).isEqualTo(pphSpecBudget2);
        pphSpecBudget2.setId(2L);
        assertThat(pphSpecBudget1).isNotEqualTo(pphSpecBudget2);
        pphSpecBudget1.setId(null);
        assertThat(pphSpecBudget1).isNotEqualTo(pphSpecBudget2);
    }
}
