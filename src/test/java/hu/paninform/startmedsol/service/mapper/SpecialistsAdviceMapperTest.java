package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SpecialistsAdviceMapperTest {

    private SpecialistsAdviceMapper specialistsAdviceMapper;

    @BeforeEach
    public void setUp() {
        specialistsAdviceMapper = new SpecialistsAdviceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(specialistsAdviceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(specialistsAdviceMapper.fromId(null)).isNull();
    }
}
