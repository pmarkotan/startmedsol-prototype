package hu.paninform.startmedsol.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class ContactPersonDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactPersonDTO.class);
        ContactPersonDTO contactPersonDTO1 = new ContactPersonDTO();
        contactPersonDTO1.setId(1L);
        ContactPersonDTO contactPersonDTO2 = new ContactPersonDTO();
        assertThat(contactPersonDTO1).isNotEqualTo(contactPersonDTO2);
        contactPersonDTO2.setId(contactPersonDTO1.getId());
        assertThat(contactPersonDTO1).isEqualTo(contactPersonDTO2);
        contactPersonDTO2.setId(2L);
        assertThat(contactPersonDTO1).isNotEqualTo(contactPersonDTO2);
        contactPersonDTO1.setId(null);
        assertThat(contactPersonDTO1).isNotEqualTo(contactPersonDTO2);
    }
}
