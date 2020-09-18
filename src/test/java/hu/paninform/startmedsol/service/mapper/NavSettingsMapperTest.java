package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NavSettingsMapperTest {

    private NavSettingsMapper navSettingsMapper;

    @BeforeEach
    public void setUp() {
        navSettingsMapper = new NavSettingsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(navSettingsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(navSettingsMapper.fromId(null)).isNull();
    }
}
