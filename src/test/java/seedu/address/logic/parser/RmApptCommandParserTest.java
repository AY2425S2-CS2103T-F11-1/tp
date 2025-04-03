package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RmApptCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class RmApptCommandParserTest {

    private final RmApptCommandParser parser = new RmApptCommandParser();

    @Test
    public void parse_validArgs_returnsRmApptCommand() throws Exception {
        String nric = "S1234567A";
        int index = 2;
        String userInput = " -IC " + nric + " -I " + index;

        RmApptCommand command = parser.parse(userInput);
        assertEquals(new RmApptCommand(nric, index), command);
    }

    @Test
    public void parse_missingNricPrefix_throwsParseException() {
        String userInput = " -I 1";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_missingIndexPrefix_throwsParseException() {
        String userInput = " -IC S1234567A";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_nonIntegerIndex_throwsParseException() {
        String userInput = " -IC S1234567A -I abc";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(userInput));
        assertEquals("Invalid format for appointment index", e.getMessage());
    }

    @Test
    public void parse_zeroIndex_throwsParseException() {
        String userInput = " -IC S1234567A -I 0";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(userInput));
        assertEquals("Invalid appointment index", e.getMessage());
    }

    @Test
    public void parse_negativeIndex_throwsParseException() {
        String userInput = " -IC S1234567A -I -1";
        ParseException e = assertThrows(ParseException.class, () -> parser.parse(userInput));
        assertEquals("Invalid appointment index", e.getMessage());
    }

    @Test
    public void parse_duplicatePrefixes_throwsParseException() {
        String userInput = " -IC S1234567A -IC S7654321B -I 1";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_extraPreamble_throwsParseException() {
        String userInput = "extra -IC S1234567A -I 1";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
