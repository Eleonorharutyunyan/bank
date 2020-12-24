package com.bdg.bank.transaction.Bank;
import com.bdg.bank.transaction.Bank.dto.PersonDto;
import com.bdg.bank.transaction.Bank.service.BaseService;

import java.util.Set;

public interface PersonService extends BaseService<PersonDto> {

    Set<PersonDto> getPersonsOfPerson(PersonDto person);

    boolean registerPerson(PersonDto trip, PersonDto person);

    boolean cancelPerson(PersonDto trip, PersonDto person);

    void loadPersonPersonsInfoFromFile(String path);
}
