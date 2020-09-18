package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class ErrorRecordTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ErrorRecord.class);
        ErrorRecord errorRecord1 = new ErrorRecord();
        errorRecord1.setId(1L);
        ErrorRecord errorRecord2 = new ErrorRecord();
        errorRecord2.setId(errorRecord1.getId());
        assertThat(errorRecord1).isEqualTo(errorRecord2);
        errorRecord2.setId(2L);
        assertThat(errorRecord1).isNotEqualTo(errorRecord2);
        errorRecord1.setId(null);
        assertThat(errorRecord1).isNotEqualTo(errorRecord2);
    }
}
