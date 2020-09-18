package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CsProcedureMapperTest {

    private CsProcedureMapper csProcedureMapper;

    @BeforeEach
    public void setUp() {
        csProcedureMapper = new CsProcedureMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(csProcedureMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(csProcedureMapper.fromId(null)).isNull();
    }
}
