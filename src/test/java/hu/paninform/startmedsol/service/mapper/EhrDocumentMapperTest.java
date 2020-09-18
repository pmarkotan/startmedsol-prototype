package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EhrDocumentMapperTest {

    private EhrDocumentMapper ehrDocumentMapper;

    @BeforeEach
    public void setUp() {
        ehrDocumentMapper = new EhrDocumentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ehrDocumentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ehrDocumentMapper.fromId(null)).isNull();
    }
}
