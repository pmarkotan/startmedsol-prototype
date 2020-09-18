package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class DataGeneratorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataGenerator.class);
        DataGenerator dataGenerator1 = new DataGenerator();
        dataGenerator1.setId(1L);
        DataGenerator dataGenerator2 = new DataGenerator();
        dataGenerator2.setId(dataGenerator1.getId());
        assertThat(dataGenerator1).isEqualTo(dataGenerator2);
        dataGenerator2.setId(2L);
        assertThat(dataGenerator1).isNotEqualTo(dataGenerator2);
        dataGenerator1.setId(null);
        assertThat(dataGenerator1).isNotEqualTo(dataGenerator2);
    }
}
