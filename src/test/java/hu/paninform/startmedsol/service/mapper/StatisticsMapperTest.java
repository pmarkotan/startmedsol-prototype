package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StatisticsMapperTest {

    private StatisticsMapper statisticsMapper;

    @BeforeEach
    public void setUp() {
        statisticsMapper = new StatisticsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(statisticsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(statisticsMapper.fromId(null)).isNull();
    }
}
