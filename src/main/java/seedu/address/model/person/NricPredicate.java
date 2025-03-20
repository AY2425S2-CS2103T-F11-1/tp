package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Nric} matches the given NRIC.
 */
public class NricPredicate implements Predicate<Person> {
    private final String nricValue;

    public NricPredicate(String nricValue) {
        this.nricValue = nricValue;
    }

    @Override
    public boolean test(Person person) {
        return person.getNric().toString().equals(nricValue);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof NricPredicate)) {
            return false;
        }
        NricPredicate otherPredicate = (NricPredicate) other;
        return nricValue.equals(otherPredicate.nricValue);
    }
}
