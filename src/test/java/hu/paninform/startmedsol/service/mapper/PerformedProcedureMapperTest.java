package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PerformedProcedureMapperTest {

    private PerformedProcedureMapper performedProcedureMapper;

    @BeforeEach
    public void setUp() {
        performedProcedureMapper = new PerformedProcedureMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(performedProcedureMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(performedProcedureMapper.fromId(null)).isNull();
    }
}
