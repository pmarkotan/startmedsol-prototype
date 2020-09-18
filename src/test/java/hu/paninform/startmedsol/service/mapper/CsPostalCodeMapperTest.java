package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CsPostalCodeMapperTest {

    private CsPostalCodeMapper csPostalCodeMapper;

    @BeforeEach
    public void setUp() {
        csPostalCodeMapper = new CsPostalCodeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(csPostalCodeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(csPostalCodeMapper.fromId(null)).isNull();
    }
}
