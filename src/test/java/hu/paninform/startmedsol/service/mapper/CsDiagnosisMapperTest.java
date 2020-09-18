package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CsDiagnosisMapperTest {

    private CsDiagnosisMapper csDiagnosisMapper;

    @BeforeEach
    public void setUp() {
        csDiagnosisMapper = new CsDiagnosisMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(csDiagnosisMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(csDiagnosisMapper.fromId(null)).isNull();
    }
}
