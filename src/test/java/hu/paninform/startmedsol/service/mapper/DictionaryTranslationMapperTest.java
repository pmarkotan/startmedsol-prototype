package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DictionaryTranslationMapperTest {

    private DictionaryTranslationMapper dictionaryTranslationMapper;

    @BeforeEach
    public void setUp() {
        dictionaryTranslationMapper = new DictionaryTranslationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dictionaryTranslationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dictionaryTranslationMapper.fromId(null)).isNull();
    }
}
