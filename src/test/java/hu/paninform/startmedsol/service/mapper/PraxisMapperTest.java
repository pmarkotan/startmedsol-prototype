package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PraxisMapperTest {

    private PraxisMapper praxisMapper;

    @BeforeEach
    public void setUp() {
        praxisMapper = new PraxisMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(praxisMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(praxisMapper.fromId(null)).isNull();
    }
}
