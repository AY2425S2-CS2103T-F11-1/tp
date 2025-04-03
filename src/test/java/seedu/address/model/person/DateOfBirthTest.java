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
        assertFalse(DateOfBirth.isValidDate("01/13/1991")); // month is over 12
        assertFalse(DateOfBirth.isValidDate("01/01/1880")); // date is before 1990
        assertFalse(DateOfBirth.isValidDate("31/12/1889")); // boundary condition, date is before 1900
        // date is after "today's date", note that test will fail after 2028
        assertFalse(DateOfBirth.isValidDate("08/08/2028"));

        // valid date of birth
        assertTrue(DateOfBirth.isValidDate("15/01/2001"));
        assertTrue(DateOfBirth.isValidDate("22/05/1980"));
        assertTrue(DateOfBirth.isValidDate("08/07/1993"));
    }

    @Test
    public void equals() {
        DateOfBirth dateOfBirth = new DateOfBirth("22/05/1980");

        // same values -> returns true
        assertTrue(dateOfBirth.equals(new DateOfBirth("22/05/1980")));

        // same object -> returns true
        assertTrue(dateOfBirth.equals(dateOfBirth));

        // null -> returns false
        assertFalse(dateOfBirth.equals(null));

        // different values -> returns false
        assertFalse(dateOfBirth.equals(new DateOfBirth("08/07/1993")));
    }
}
