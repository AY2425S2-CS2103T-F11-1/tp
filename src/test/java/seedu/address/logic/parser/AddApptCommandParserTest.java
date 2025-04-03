package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddApptCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddApptCommandParserTest {

    private final AddApptCommandParser parser = new AddApptCommandParser();

    @Test
    public void parse_validArgs_returnsAddApptCommand() throws Exception {
        // valid input with no preamble and one occurrence of each required prefix
        String validInput = " -IC S1234567A -D 25/12/2025 14:30";
        AddApptCommand expectedCommand = new AddApptCommand("S1234567A", "25/12/2025 14:30");
        AddApptCommand result = parser.parse(validInput);
        assertEquals(expectedCommand, result);
    }

    @Test
    public void parse_missingNricPrefix_throwsParseException() {
        // missing NRIC prefix, only date provided
        String inputMissingNric = " -D 25/12/2025 14:30";
        assertThrows(ParseException.class, () -> parser.parse(inputMissingNric));
    }

    @Test
    public void parse_missingDatePrefix_throwsParseException() {
        // missing date prefix, only NRIC provided
        String inputMissingDate = " -IC S1234567A";
        assertThrows(ParseException.class, () -> parser.parse(inputMissingDate));
    }

    @Test
    public void parse_nonEmptyPreamble_throwsParseException() {
        // preamble should be empty, but a stray word is provided at the start
        String inputWithPreamble = "extra -IC S1234567A -D 25/12/2025 14:30";
        ParseException exception = assertThrows(ParseException.class, () -> parser.parse(inputWithPreamble));
        // Optionally, check that the error message matches the expected invalid command format.
        assertEquals(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddApptCommand.MESSAGE_USAGE), exception.getMessage());
    }

    @Test
    public void parse_duplicateNricPrefix_throwsParseException() {
        // duplicate NRIC prefixes provided should cause an exception.
        String inputWithDuplicateNric = " -IC S1234567A -IC S7654321B -D 25/12/2025 14:30";
        assertThrows(ParseException.class, () -> parser.parse(inputWithDuplicateNric));
    }

    @Test
    public void parse_duplicateDatePrefix_throwsParseException() {
        // duplicate date prefixes provided should cause an exception.
        String inputWithDuplicateDate = " -IC S1234567A -D 25/12/2025 14:30 -D 26/12/2025 10:00";
        assertThrows(ParseException.class, () -> parser.parse(inputWithDuplicateDate));
    }
}

