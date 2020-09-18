package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class SpecialistsAdviceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SpecialistsAdvice.class);
        SpecialistsAdvice specialistsAdvice1 = new SpecialistsAdvice();
        specialistsAdvice1.setId(1L);
        SpecialistsAdvice specialistsAdvice2 = new SpecialistsAdvice();
        specialistsAdvice2.setId(specialistsAdvice1.getId());
        assertThat(specialistsAdvice1).isEqualTo(specialistsAdvice2);
        specialistsAdvice2.setId(2L);
        assertThat(specialistsAdvice1).isNotEqualTo(specialistsAdvice2);
        specialistsAdvice1.setId(null);
        assertThat(specialistsAdvice1).isNotEqualTo(specialistsAdvice2);
    }
}
