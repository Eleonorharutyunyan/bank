package com.bdg.bank.transaction.Bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@Data
public class Person {
    public long id;

    @NotNull
    @Size(min = 3, max = 50, message = "name length can be 3 to 50 simbol")
    public String name;

    @NotNull
    @Size(min = 3, max = 50, message = "last name length can be 3 to 50 simbol")
    public String lastname;

    @NotNull
    @Size(min = 4, max = 5, message = "role can be 'admin' or 'user'")
    public String role;

//    public void addPerson(Person person) {
//        person.addPerson(person);
//        person.getPerson().add(this);
//    }
//
//        public void removePerson(Person person) {
//        person.removePerson(person);
//        person.getPerson().remove(this);
//    }




//    public void setPerson(PersonMethods person) {
//        this.person = person;
//    }
}
