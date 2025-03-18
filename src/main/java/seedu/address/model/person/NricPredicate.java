package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests if a {@code Person}'s {@code Nric} matches the given NRIC.
 */
public class NricPredicate implements Predicate<Person> {
    private final String nric;

    public NricPredicate(String nric) {
        this.nric = nric;
    }

    @Override
    public boolean test(Person person) {
        return person.getNric().value.equals(nric);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NricPredicate // instanceof handles nulls
                && nric.equals(((NricPredicate) other).nric)); // state check
    }
}
