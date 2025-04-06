package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
        // starts with -, illegal
        assertThrows(IllegalArgumentException.class, () -> new Tag("-"));
        // 31 characters - too long
        assertThrows(IllegalArgumentException.class, () -> new Tag("abcdefghijklmnopqrstuvwxyzabcde"));
    }

    @Test
    public void constructor_validTagName_returnsTag() {
        assertDoesNotThrow(() -> new Tag("Test"));
        assertDoesNotThrow(() -> new Tag("Test-test"));
        assertDoesNotThrow(() -> new Tag("T"));
        assertDoesNotThrow(() -> new Tag("1"));
        assertDoesNotThrow(() -> new Tag("012345678901234567890123456789")); // Exactly 30 characters long
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

}
