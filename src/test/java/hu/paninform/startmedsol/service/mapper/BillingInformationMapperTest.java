package hu.paninform.startmedsol.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BillingInformationMapperTest {

    private BillingInformationMapper billingInformationMapper;

    @BeforeEach
    public void setUp() {
        billingInformationMapper = new BillingInformationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(billingInformationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(billingInformationMapper.fromId(null)).isNull();
    }
}
