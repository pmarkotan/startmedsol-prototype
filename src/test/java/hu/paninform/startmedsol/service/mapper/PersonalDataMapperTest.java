package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonalDataMapperTest {

    private PersonalDataMapper personalDataMapper;

    @BeforeEach
    public void setUp() {
        personalDataMapper = new PersonalDataMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(personalDataMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(personalDataMapper.fromId(null)).isNull();
    }
}
