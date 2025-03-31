package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.stream.Stream;

import seedu.address.logic.commands.RmApptCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RmApptCommand object.
 * Expected format: "rmappt -IC NRIC -I Appointment Index"
 */
public class RmApptCommandParser implements Parser<RmApptCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RmApptCommand
     * and returns an RmApptCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public RmApptCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NRIC, PREFIX_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_NRIC, PREFIX_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RmApptCommand.MESSAGE_USAGE));
        }

        String nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get()).toString();
        String indexStr = argMultimap.getValue(PREFIX_INDEX).get();

        int index;
        try {
            index = Integer.parseInt(indexStr);
            if (index <= 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RmApptCommand.MESSAGE_USAGE));
            }
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RmApptCommand.MESSAGE_USAGE));
        }

        return new RmApptCommand(nric, index);
    }

    /**
     * Returns true if all the specified prefixes have non-empty values in the given ArgumentMultimap.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
