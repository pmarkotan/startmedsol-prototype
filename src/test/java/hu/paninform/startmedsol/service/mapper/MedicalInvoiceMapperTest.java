package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MedicalInvoiceMapperTest {

    private MedicalInvoiceMapper medicalInvoiceMapper;

    @BeforeEach
    public void setUp() {
        medicalInvoiceMapper = new MedicalInvoiceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(medicalInvoiceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(medicalInvoiceMapper.fromId(null)).isNull();
    }
}
