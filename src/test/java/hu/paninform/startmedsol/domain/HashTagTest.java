package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class HashTagTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HashTag.class);
        HashTag hashTag1 = new HashTag();
        hashTag1.setId(1L);
        HashTag hashTag2 = new HashTag();
        hashTag2.setId(hashTag1.getId());
        assertThat(hashTag1).isEqualTo(hashTag2);
        hashTag2.setId(2L);
        assertThat(hashTag1).isNotEqualTo(hashTag2);
        hashTag1.setId(null);
        assertThat(hashTag1).isNotEqualTo(hashTag2);
    }
}
