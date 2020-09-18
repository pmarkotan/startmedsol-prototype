package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class FeedBackMessageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FeedBackMessage.class);
        FeedBackMessage feedBackMessage1 = new FeedBackMessage();
        feedBackMessage1.setId(1L);
        FeedBackMessage feedBackMessage2 = new FeedBackMessage();
        feedBackMessage2.setId(feedBackMessage1.getId());
        assertThat(feedBackMessage1).isEqualTo(feedBackMessage2);
        feedBackMessage2.setId(2L);
        assertThat(feedBackMessage1).isNotEqualTo(feedBackMessage2);
        feedBackMessage1.setId(null);
        assertThat(feedBackMessage1).isNotEqualTo(feedBackMessage2);
    }
}
