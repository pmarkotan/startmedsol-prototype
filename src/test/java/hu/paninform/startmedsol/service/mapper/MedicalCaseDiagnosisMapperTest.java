package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MedicalCaseDiagnosisMapperTest {

    private MedicalCaseDiagnosisMapper medicalCaseDiagnosisMapper;

    @BeforeEach
    public void setUp() {
        medicalCaseDiagnosisMapper = new MedicalCaseDiagnosisMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(medicalCaseDiagnosisMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(medicalCaseDiagnosisMapper.fromId(null)).isNull();
    }
}
