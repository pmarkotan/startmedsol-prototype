package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PphMedicineQualifiedNameMapperTest {

    private PphMedicineQualifiedNameMapper pphMedicineQualifiedNameMapper;

    @BeforeEach
    public void setUp() {
        pphMedicineQualifiedNameMapper = new PphMedicineQualifiedNameMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(pphMedicineQualifiedNameMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(pphMedicineQualifiedNameMapper.fromId(null)).isNull();
    }
}
