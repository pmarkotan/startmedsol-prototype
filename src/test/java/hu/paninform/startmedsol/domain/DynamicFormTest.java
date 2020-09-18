package hu.paninform.startmedsol.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import hu.paninform.startmedsol.web.rest.TestUtil;

public class DynamicFormTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DynamicForm.class);
        DynamicForm dynamicForm1 = new DynamicForm();
        dynamicForm1.setId(1L);
        DynamicForm dynamicForm2 = new DynamicForm();
        dynamicForm2.setId(dynamicForm1.getId());
        assertThat(dynamicForm1).isEqualTo(dynamicForm2);
        dynamicForm2.setId(2L);
        assertThat(dynamicForm1).isNotEqualTo(dynamicForm2);
        dynamicForm1.setId(null);
        assertThat(dynamicForm1).isNotEqualTo(dynamicForm2);
    }
}
