package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class ProceduresOfPraxisTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProceduresOfPraxis.class);
        ProceduresOfPraxis proceduresOfPraxis1 = new ProceduresOfPraxis();
        proceduresOfPraxis1.setId(1L);
        ProceduresOfPraxis proceduresOfPraxis2 = new ProceduresOfPraxis();
        proceduresOfPraxis2.setId(proceduresOfPraxis1.getId());
        assertThat(proceduresOfPraxis1).isEqualTo(proceduresOfPraxis2);
        proceduresOfPraxis2.setId(2L);
        assertThat(proceduresOfPraxis1).isNotEqualTo(proceduresOfPraxis2);
        proceduresOfPraxis1.setId(null);
        assertThat(proceduresOfPraxis1).isNotEqualTo(proceduresOfPraxis2);
    }
}
