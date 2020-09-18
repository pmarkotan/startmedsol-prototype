package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProceduresOfPraxisMapperTest {

    private ProceduresOfPraxisMapper proceduresOfPraxisMapper;

    @BeforeEach
    public void setUp() {
        proceduresOfPraxisMapper = new ProceduresOfPraxisMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(proceduresOfPraxisMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(proceduresOfPraxisMapper.fromId(null)).isNull();
    }
}
