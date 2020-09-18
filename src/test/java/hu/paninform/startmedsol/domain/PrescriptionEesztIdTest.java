package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PrescriptionEesztIdTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrescriptionEesztId.class);
        PrescriptionEesztId prescriptionEesztId1 = new PrescriptionEesztId();
        prescriptionEesztId1.setId(1L);
        PrescriptionEesztId prescriptionEesztId2 = new PrescriptionEesztId();
        prescriptionEesztId2.setId(prescriptionEesztId1.getId());
        assertThat(prescriptionEesztId1).isEqualTo(prescriptionEesztId2);
        prescriptionEesztId2.setId(2L);
        assertThat(prescriptionEesztId1).isNotEqualTo(prescriptionEesztId2);
        prescriptionEesztId1.setId(null);
        assertThat(prescriptionEesztId1).isNotEqualTo(prescriptionEesztId2);
    }
}
