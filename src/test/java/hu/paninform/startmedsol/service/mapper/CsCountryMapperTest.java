package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CsCountryMapperTest {

    private CsCountryMapper csCountryMapper;

    @BeforeEach
    public void setUp() {
        csCountryMapper = new CsCountryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(csCountryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(csCountryMapper.fromId(null)).isNull();
    }
}
