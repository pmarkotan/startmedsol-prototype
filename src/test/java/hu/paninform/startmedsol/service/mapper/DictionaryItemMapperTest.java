package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DictionaryItemMapperTest {

    private DictionaryItemMapper dictionaryItemMapper;

    @BeforeEach
    public void setUp() {
        dictionaryItemMapper = new DictionaryItemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dictionaryItemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dictionaryItemMapper.fromId(null)).isNull();
    }
}
