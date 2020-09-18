package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PuphaLoaderMapperTest {

    private PuphaLoaderMapper puphaLoaderMapper;

    @BeforeEach
    public void setUp() {
        puphaLoaderMapper = new PuphaLoaderMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(puphaLoaderMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(puphaLoaderMapper.fromId(null)).isNull();
    }
}
