package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds an appointment to a person's appointment list.
 */
public class AddApptCommand extends Command {

    public static final String COMMAND_WORD = "addappt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an appointment for a person identified by their IC number.\n"
            + "Parameters: -IC <IC number> -D <date in dd/MM/yyyy HH:mm format>\n"
            + "Example: " + COMMAND_WORD + " -IC S1234567A -D 12/03/2025 14:30";

    public static final String MESSAGE_SUCCESS = "New appointment added for person with IC %1$s on %2$s";
    public static final String MESSAGE_FAILURE = "Failed to add appointment: %1$s";

    private final String ic;
    private final String date;

    /**
     * Constructs an AddApptCommand with the specified IC number and date.
     *
     * @param ic   The IC number of the person.
     * @param date The appointment date in dd/MM/yyyy HH:mm format.
     */
    public AddApptCommand(String ic, String date) {
        requireNonNull(ic);
        requireNonNull(date);
        this.ic = ic;
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            return new CommandResult(String.format(MESSAGE_SUCCESS, ic, date));
        } catch (Exception e) {
            throw new CommandException(String.format(MESSAGE_FAILURE, e.getMessage()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddApptCommand
                && ic.equals(((AddApptCommand) other).ic)
                && date.equals(((AddApptCommand) other).date));
    }
}
