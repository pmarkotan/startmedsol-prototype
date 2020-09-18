package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class PphQualificationDoctorAllTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PphQualificationDoctorAll.class);
        PphQualificationDoctorAll pphQualificationDoctorAll1 = new PphQualificationDoctorAll();
        pphQualificationDoctorAll1.setId(1L);
        PphQualificationDoctorAll pphQualificationDoctorAll2 = new PphQualificationDoctorAll();
        pphQualificationDoctorAll2.setId(pphQualificationDoctorAll1.getId());
        assertThat(pphQualificationDoctorAll1).isEqualTo(pphQualificationDoctorAll2);
        pphQualificationDoctorAll2.setId(2L);
        assertThat(pphQualificationDoctorAll1).isNotEqualTo(pphQualificationDoctorAll2);
        pphQualificationDoctorAll1.setId(null);
        assertThat(pphQualificationDoctorAll1).isNotEqualTo(pphQualificationDoctorAll2);
    }
}
