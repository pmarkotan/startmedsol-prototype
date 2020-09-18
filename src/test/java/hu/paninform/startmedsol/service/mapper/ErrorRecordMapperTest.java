package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ErrorRecordMapperTest {

    private ErrorRecordMapper errorRecordMapper;

    @BeforeEach
    public void setUp() {
        errorRecordMapper = new ErrorRecordMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(errorRecordMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(errorRecordMapper.fromId(null)).isNull();
    }
}
