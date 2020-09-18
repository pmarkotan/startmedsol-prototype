package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FeedBackMessageMapperTest {

    private FeedBackMessageMapper feedBackMessageMapper;

    @BeforeEach
    public void setUp() {
        feedBackMessageMapper = new FeedBackMessageMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(feedBackMessageMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(feedBackMessageMapper.fromId(null)).isNull();
    }
}
