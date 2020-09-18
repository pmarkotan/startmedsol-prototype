package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CaseTextDocumentationMapperTest {

    private CaseTextDocumentationMapper caseTextDocumentationMapper;

    @BeforeEach
    public void setUp() {
        caseTextDocumentationMapper = new CaseTextDocumentationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(caseTextDocumentationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(caseTextDocumentationMapper.fromId(null)).isNull();
    }
}
