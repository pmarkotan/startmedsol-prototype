package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class EhrDocumentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EhrDocumentDTO.class);
        EhrDocumentDTO ehrDocumentDTO1 = new EhrDocumentDTO();
        ehrDocumentDTO1.setId(1L);
        EhrDocumentDTO ehrDocumentDTO2 = new EhrDocumentDTO();
        assertThat(ehrDocumentDTO1).isNotEqualTo(ehrDocumentDTO2);
        ehrDocumentDTO2.setId(ehrDocumentDTO1.getId());
        assertThat(ehrDocumentDTO1).isEqualTo(ehrDocumentDTO2);
        ehrDocumentDTO2.setId(2L);
        assertThat(ehrDocumentDTO1).isNotEqualTo(ehrDocumentDTO2);
        ehrDocumentDTO1.setId(null);
        assertThat(ehrDocumentDTO1).isNotEqualTo(ehrDocumentDTO2);
    }
}
