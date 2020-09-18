package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DataGeneratorMapperTest {

    private DataGeneratorMapper dataGeneratorMapper;

    @BeforeEach
    public void setUp() {
        dataGeneratorMapper = new DataGeneratorMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dataGeneratorMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dataGeneratorMapper.fromId(null)).isNull();
    }
}
