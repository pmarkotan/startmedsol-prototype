package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidityMapperTest {

    private ValidityMapper validityMapper;

    @BeforeEach
    public void setUp() {
        validityMapper = new ValidityMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(validityMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(validityMapper.fromId(null)).isNull();
    }
}
