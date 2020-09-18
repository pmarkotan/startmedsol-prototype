package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MedicalServiceMapperTest {

    private MedicalServiceMapper medicalServiceMapper;

    @BeforeEach
    public void setUp() {
        medicalServiceMapper = new MedicalServiceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(medicalServiceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(medicalServiceMapper.fromId(null)).isNull();
    }
}
