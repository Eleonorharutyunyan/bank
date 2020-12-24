package com.bdg.bank.transaction.Bank.dto;

import lombok.*;

//import javax.validation.constraints.Size;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
public class PersonDto {

    private long id;

//    @Size(min = 3, max = 100)
    private String name;

//    @Size(max = 150)
    private String lname;

    private String role;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<PersonDto> trips;
}
