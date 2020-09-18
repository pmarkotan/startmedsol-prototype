package hu.paninform.startmedsol.service.mapper;


import hu.paninform.startmedsol.domain.*;
import hu.paninform.startmedsol.service.dto.ContactPersonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ContactPerson} and its DTO {@link ContactPersonDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ContactPersonMapper extends EntityMapper<ContactPersonDTO, ContactPerson> {



    default ContactPerson fromId(Long id) {
        if (id == null) {
            return null;
        }
        ContactPerson contactPerson = new ContactPerson();
        contactPerson.setId(id);
        return contactPerson;
    }
}
