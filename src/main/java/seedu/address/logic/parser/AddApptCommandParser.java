package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddApptCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import seedu.address.model.person.Nric;

/**
 * Parses input arguments and creates a new AddApptCommand object.
 * Expected format: "addappt -IC <IC number> -D <date in dd/MM/yyyy HH:mm format>"
 */
public class AddApptCommandParser implements Parser<AddApptCommand> {

    public static final Prefix PREFIX_IC = new Prefix("-IC");
    public static final Prefix PREFIX_DATE = new Prefix("-D");

    /**
     * Parses the given {@code String} of arguments in the context of the AddApptCommand
     * and returns an AddApptCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public AddApptCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NRIC, PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_IC, PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddApptCommand.MESSAGE_USAGE));
        }

        Nric nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NAME).get());
        String date = argMultimap.getValue(PREFIX_DATE).get();

        return new AddApptCommand(nric, date);
    }

    /**
     * Returns true if all the specified prefixes have non-empty values in the given ArgumentMultimap.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
