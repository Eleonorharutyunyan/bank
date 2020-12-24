package com.bdg.bank.transaction.Bank.implementation;//package com.bdg.bank.transaction.Bank.implementation;
//
//import org.springframework.stereotype.Service;
//
//public class PersonImplementation {
//}
        import com.bdg.bank.transaction.Bank.PersonService;
        import com.bdg.bank.transaction.Bank.dto.PersonDto;
        import com.bdg.bank.transaction.Bank.entity.Person;
        import com.bdg.bank.transaction.Bank.repository.AccountRepository;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.data.domain.Sort;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import javax.persistence.EntityNotFoundException;
        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Paths;
        import java.util.HashSet;
        import java.util.List;
        import java.util.Optional;
        import java.util.Set;
        import java.util.stream.Collectors;
        import java.util.stream.Stream;


@Service
public class PersonImplementation implements PersonService {

    public PersonImplementation(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public PersonDto get(Long id) {
        if (id < 1) throw new IllegalArgumentException("id cannot be less than 1");
        Optional<Peson> optionalPassenger = accountRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            return PassengerDto.builder()
                    .id(person.getId())
                    .name(person.getName())
                    .lname(person.getLname())
                    .role(person.getLname())
                    .build();
        } else throw new EntityNotFoundException(String.format("person by id: %s not found.", id));
    }

    @Override
    public Set<PersonDto> getAll() {
        Set<PersonDto> person = new HashSet<>();
        for (Person person : personRepository.findAll())
            person.add(PersonDto.builder()
                    .id(person.getId())
                    .name(person.getName())
                    .lname(person.getLname())
                    .role(person.getLname())
                    .build());
        return person;
    }

    @Override
    public List<PersonDto> getCertainCrowd(int limit, int offset, String... sortKeys) {
        if (limit < 1 || offset < 1) throw new IllegalArgumentException("illegal argument present");
        PageRequest pageRequest = PageRequest.of(offset, limit);
        if (sortKeys != null && sortKeys.length != 0) pageRequest.getSortOr(Sort.by(sortKeys));
        return personRepository.findAll(pageRequest).map(person -> PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .name(person.getLname())
                .role(person.getRole())
                .build()).toList();
    }

    @Override
    public PersonDto create(PersonDto personDto) {
        if (personDto == null) throw new IllegalArgumentException("person cannot be null");
        PersonDto personDto = personDto.getPerson();
        Person saved = personRepository.save(new Person(personDto.getName(),personDto.getLname(), personDto.getRole()));
        return PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .lname(person.getLname())
                .role(person.getLname())
                .build();
    }

    @Override
    public PersonDto edit(PersonDto personDto) {
        if (personDto == null) throw new IllegalArgumentException("person cannot be null");
        PersonDto addressDto = personDto.getPerson();
        Person person = new Person(personDto.getName(), personDto.getLname(), PersonDto.getRole());
        Person editing = new Person(personDto.getName(), personDto.getLname(), PersonDto.getRole());
        editing.setId(personDto.getId());
        editing = personRepository.save(editing);
        return PersonDto.builder()
                .id(perso.getId())
                .name(person.getName())
                .lname(person.getLname())
                .role(person.getLname())
                .build();
    }

    @Override
    public void remove(PersonDto person Dto) {
        if (passengerDto == null) throw new IllegalArgumentException("passenger cannot be null");
        Address address = new Address(passengerDto.getAddress().getCountry(), passengerDto.getAddress().getCity());
        address.setId(passengerDto.getAddress().getId());
        Passenger deleting = new Passenger(passengerDto.getName(), passengerDto.getPhone(), address);
        deleting.setId(passengerDto.getId());
        passengerRepository.delete(deleting);
    }

    @Override
    public void removeById(Long passengerId) {
        if (passengerId < 1) throw new IllegalArgumentException("id cannot be less than 1");
        passengerRepository.deleteById(passengerId);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<TripDto> getTripsOfPassenger(PassengerDto passengerDto) {
        if (passengerDto == null) throw new IllegalArgumentException("passenger cannot be null");
        Optional<Passenger> optionalPassenger = passengerRepository.findById(passengerDto.getId());
        if (optionalPassenger.isPresent()) {
            Passenger passenger = optionalPassenger.get();
            Set<TripDto> tripDtos = new HashSet<>();
            for (Trip trip : passenger.getTrips()) {
                tripDtos.add(TripDto.builder()
                        .id(trip.getId())
                        .company(CompanyDto.builder()
                                .id(trip.getCompany().getId())
                                .name(trip.getCompany().getName())
                                .foundingDate(trip.getCompany().getFoundingDate()).build())
                        .passengers(trip.getPassengers().stream().map(passenger1 -> PassengerDto.builder()
                                .id(passenger1.getId())
                                .name(passenger1.getName())
                                .phone(passenger1.getPhone()).build()).collect(Collectors.toSet()))
                        .fromCity(trip.getFromCity())
                        .toCity(trip.getToCity())
                        .timeIn(trip.getTimeIn())
                        .timeOut(trip.getTimeOut()).build());
            }
            return tripDtos;
        } else throw new EntityNotFoundException(String.format("passenger by id: %s not found.", passengerDto.getId()));
    }


    @Override
    @Transactional
    public boolean registerTrip(TripDto tripDto, PassengerDto passengerDto) {
        if (passengerDto == null) throw new IllegalArgumentException("passenger cannot be null");
        if (tripDto == null) throw new IllegalArgumentException("trip cannot be null");
        Trip trip = tripRepository.findById(tripDto.getId()).orElse(null);
        Passenger passenger = passengerRepository.findById(passengerDto.getId()).orElse(null);
        if (trip != null && passenger != null) {
            if (trip.getPassengers() == null) trip.setPassengers(new HashSet<>());
            if (passenger.getTrips() == null) passenger.setTrips(new HashSet<>());
            trip.getPassengers().add(passenger);
            passenger.getTrips().add(trip);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean cancelTrip(TripDto tripDto, PassengerDto passengerDto) {
        if (passengerDto == null) throw new IllegalArgumentException("passenger cannot be null");
        if (tripDto == null) throw new IllegalArgumentException("trip cannot be null");
        Trip trip = tripRepository.findById(tripDto.getId()).orElse(null);
        Passenger passenger = passengerRepository.findById(passengerDto.getId()).orElse(null);
        if (trip != null && passenger != null) {
            if (trip.getPassengers() == null || !trip.getPassengers().contains(passenger)) return false;
            if (passenger.getTrips() == null || !passenger.getTrips().contains(trip)) return false;
            trip.getPassengers().remove(passenger);
            passenger.getTrips().remove(trip);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void loadPassengersTripsInfoFromFile(String path) {
        if (path == null || path.isEmpty()) throw new IllegalArgumentException("illegal path");
        List<String> lines = null;
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            lines = stream.skip(1).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        if (lines == null || lines.isEmpty()) return;
        for (String line : lines) {
            String[] data = line.split(",");
            Passenger passenger = passengerRepository.findById(Long.parseLong(data[0].trim())).orElse(null);
            Trip trip = tripRepository.findById(Long.parseLong(data[1].trim())).orElse(null);
            if (passenger != null && trip != null) {
                if (passenger.getTrips() == null) passenger.setTrips(new HashSet<>());
                if (trip.getPassengers() == null) trip.setPassengers(new HashSet<>());
                passenger.getTrips().add(trip);
                trip.getPassengers().add(passenger);
                System.out.println("Passenger trip successfully registered");
            } else
                System.err.println("Failed to register passenger trip, please check passenger and trip info end try again.");
        }
    }

    @Override
    @Transactional
    public void loadEntitiesInfoFromFileAndCreateAll(String path) {
        if (path == null || path.isEmpty()) throw new IllegalArgumentException("illegal path");
        List<String> lineData = null;
        try (Stream<String> fileContent = Files.lines(Paths.get(path))) {
            lineData = fileContent.skip(1).map(String::trim).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: message: " + e.getMessage());
        }
        if (lineData == null || lineData.isEmpty()) return;
        for (String line : lineData) {
            String[] data = line.split(",");
            Address address = addressRepository.getAddressByCountryAndCity(data[2].trim(), data[3].trim());
            if (address == null) address = new Address(data[2].trim(), data[3].trim());
            Passenger passenger = new Passenger(data[0].trim(), data[1].trim(), address);
            if (address.getPassengers() == null) address.setPassengers(new HashSet<>());
            address.getPassengers().add(passenger);
            addressRepository.save(address);
        }
    }

    @Override
    public Set<PersonDto> getPersonsOfPerson(PersonDto person) {
        return null;
    }

    @Override
    public boolean registerPerson(PersonDto trip, PersonDto person) {
        return false;
    }

    @Override
    public boolean cancelPerson(PersonDto trip, PersonDto person) {
        return false;
    }

    @Override
    public void loadPersonPersonsInfoFromFile(String path) {

    }
}