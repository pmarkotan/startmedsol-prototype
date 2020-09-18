package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PphMedicineMapperTest {

    private PphMedicineMapper pphMedicineMapper;

    @BeforeEach
    public void setUp() {
        pphMedicineMapper = new PphMedicineMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(pphMedicineMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(pphMedicineMapper.fromId(null)).isNull();
    }
}
