package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        // return new Person[] {
        Person[] personList = {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Nric("S9132623Z"),
                new DateOfBirth("01/01/1991"), getTagSet("CHAS-Green")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Nric("S4643396C"),
                new DateOfBirth("12/06/1970"), getTagSet("Raffles-Medical")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Nric("S3164793B"),
                new DateOfBirth("03/03/1945"), getTagSet("ElderShield")),
            new Person(new Name("David Li"), new Phone("91031282"), new Nric("S1289342F"),
                new DateOfBirth("09/05/1940"), getTagSet("CHAS-Blue")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Nric("S4674105F"),
                new DateOfBirth("03/02/1960"), getTagSet("CHAS-Blue")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Nric("S4921002G"),
                new DateOfBirth("03/07/1950"), getTagSet("CHAS-Green"))
        };

        personList[0].addAppointment("01/08/2024 10:00");
        personList[0].addAppointment("19/11/2024 12:00");
        personList[0].addAppointment("19/04/2025 20:00");
        personList[0].addAppointment("26/05/2025 13:00");

        return personList;
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
