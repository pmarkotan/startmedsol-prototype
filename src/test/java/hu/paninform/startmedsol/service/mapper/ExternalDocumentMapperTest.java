package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ExternalDocumentMapperTest {

    private ExternalDocumentMapper externalDocumentMapper;

    @BeforeEach
    public void setUp() {
        externalDocumentMapper = new ExternalDocumentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(externalDocumentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(externalDocumentMapper.fromId(null)).isNull();
    }
}
