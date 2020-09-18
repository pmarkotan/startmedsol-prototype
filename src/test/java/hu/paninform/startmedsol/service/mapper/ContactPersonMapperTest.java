package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ContactPersonMapperTest {

    private ContactPersonMapper contactPersonMapper;

    @BeforeEach
    public void setUp() {
        contactPersonMapper = new ContactPersonMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(contactPersonMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(contactPersonMapper.fromId(null)).isNull();
    }
}
