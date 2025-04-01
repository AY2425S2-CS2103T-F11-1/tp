package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import seedu.address.logic.commands.ViewpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewpCommand object.
 */
public class ViewpCommandParser implements Parser<ViewpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewpCommand
     * and returns a ViewpCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewpCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NRIC);

        if (args.trim().isEmpty() || !argMultimap.getValue(PREFIX_NRIC).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewpCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NRIC);
        String nric = argMultimap.getValue(PREFIX_NRIC).get().toUpperCase();
        return new ViewpCommand(nric);
    }
}
