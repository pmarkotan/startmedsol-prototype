package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class ContactPersonTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContactPerson.class);
        ContactPerson contactPerson1 = new ContactPerson();
        contactPerson1.setId(1L);
        ContactPerson contactPerson2 = new ContactPerson();
        contactPerson2.setId(contactPerson1.getId());
        assertThat(contactPerson1).isEqualTo(contactPerson2);
        contactPerson2.setId(2L);
        assertThat(contactPerson1).isNotEqualTo(contactPerson2);
        contactPerson1.setId(null);
        assertThat(contactPerson1).isNotEqualTo(contactPerson2);
    }
}
