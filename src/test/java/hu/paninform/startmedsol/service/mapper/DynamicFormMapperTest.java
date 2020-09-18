package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DynamicFormMapperTest {

    private DynamicFormMapper dynamicFormMapper;

    @BeforeEach
    public void setUp() {
        dynamicFormMapper = new DynamicFormMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dynamicFormMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dynamicFormMapper.fromId(null)).isNull();
    }
}
