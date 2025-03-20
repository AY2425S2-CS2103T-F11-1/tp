package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

/**
 * Contains unit tests for {@code NricPredicate}.
 */
public class NricPredicateTest {

    @Test
    public void test_nricMatches_returnsTrue() {
        // Same NRIC -> returns true
        NricPredicate predicate = new NricPredicate("S1234567A");
        Person person = new PersonBuilder().withName("John Doe").withNric("S1234567A").build();
        assertTrue(predicate.test(person));
    }

    @Test
    public void test_nricDoesNotMatch_returnsFalse() {
        // Different NRIC -> returns false
        NricPredicate predicate = new NricPredicate("S1234567A");
        Person person = new PersonBuilder().withName("John Doe").withNric("S7654321B").build();
        assertFalse(predicate.test(person));
    }

    @Test
    public void equals() {
        String firstNric = "S1234567A";
        String secondNric = "S7654321B";

        NricPredicate firstPredicate = new NricPredicate(firstNric);
        NricPredicate secondPredicate = new NricPredicate(secondNric);
        NricPredicate firstPredicateCopy = new NricPredicate(firstNric);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different NRIC -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
