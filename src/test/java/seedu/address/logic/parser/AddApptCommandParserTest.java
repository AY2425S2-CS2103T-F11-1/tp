package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddApptCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddApptCommandParserTest {

    private final AddApptCommandParser parser = new AddApptCommandParser();

    @Test
    public void parse_validArgs_returnsAddApptCommand() throws Exception {
        String nric = "S1234567A";
        String date = "01/05/2025 14:30";
        String userInput = " -IC " + nric + " -D " + date;

        AddApptCommand command = parser.parse(userInput);
        assertEquals(new AddApptCommand(nric, date), command);
    }

    @Test
    public void parse_missingNricPrefix_throwsParseException() {
        String userInput = " -D 01/05/2025 14:30";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_missingDatePrefix_throwsParseException() {
        String userInput = " -IC S1234567A";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidDateFormat_throwsParseException() {
        String userInput = " -IC S1234567A -D 2025-05-01";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_duplicatePrefixes_throwsParseException() {
        String userInput = " -IC S1234567A -IC S7654321B -D 01/05/2025 14:30";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_extraPreamble_throwsParseException() {
        String userInput = "extra -IC S1234567A -D 01/05/2025 14:30";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
