package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HashTagMapperTest {

    private HashTagMapper hashTagMapper;

    @BeforeEach
    public void setUp() {
        hashTagMapper = new HashTagMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(hashTagMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(hashTagMapper.fromId(null)).isNull();
    }
}
