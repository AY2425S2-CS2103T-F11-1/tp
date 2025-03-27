package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateOfBirthTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateOfBirth(null));
    }

    @Test
    public void constructor_invalidDateOfBirth_throwsIllegalArgumentException() {
        String invalidDateOfBirth = "";
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(invalidDateOfBirth));
    }

    @Test
    public void isValidDateOfBirth() {
        // null date of birth
        assertThrows(NullPointerException.class, () -> DateOfBirth.isValidDate(null));

        // invalid date of birth
        assertFalse(DateOfBirth.isValidDate("")); // empty string
        assertFalse(DateOfBirth.isValidDate(" ")); // spaces only
        assertFalse(DateOfBirth.isValidDate("1991-13-01")); // month is over 12

        // valid date of birth
        assertTrue(DateOfBirth.isValidDate("2001-01-15"));
        assertTrue(DateOfBirth.isValidDate("1980-05-22"));
        assertTrue(DateOfBirth.isValidDate("1993-07-08"));
    }

    @Test
    public void equals() {
        DateOfBirth dateOfBirth = new DateOfBirth("1980-05-22");

        // same values -> returns true
        assertTrue(dateOfBirth.equals(new DateOfBirth("1980-05-22")));

        // same object -> returns true
        assertTrue(dateOfBirth.equals(dateOfBirth));

        // null -> returns false
        assertFalse(dateOfBirth.equals(null));

        // different values -> returns false
        assertFalse(dateOfBirth.equals(new DateOfBirth("1993-07-08")));
    }
}
