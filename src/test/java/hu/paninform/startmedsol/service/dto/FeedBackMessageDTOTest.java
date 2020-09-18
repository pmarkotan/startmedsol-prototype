package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class FeedBackMessageDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FeedBackMessageDTO.class);
        FeedBackMessageDTO feedBackMessageDTO1 = new FeedBackMessageDTO();
        feedBackMessageDTO1.setId(1L);
        FeedBackMessageDTO feedBackMessageDTO2 = new FeedBackMessageDTO();
        assertThat(feedBackMessageDTO1).isNotEqualTo(feedBackMessageDTO2);
        feedBackMessageDTO2.setId(feedBackMessageDTO1.getId());
        assertThat(feedBackMessageDTO1).isEqualTo(feedBackMessageDTO2);
        feedBackMessageDTO2.setId(2L);
        assertThat(feedBackMessageDTO1).isNotEqualTo(feedBackMessageDTO2);
        feedBackMessageDTO1.setId(null);
        assertThat(feedBackMessageDTO1).isNotEqualTo(feedBackMessageDTO2);
    }
}
