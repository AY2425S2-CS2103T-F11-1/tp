package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Removes an appointment for a person identified by their NRIC.
 */
public class RmApptCommand extends Command {

    public static final String COMMAND_WORD = "rmappt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes an appointment for a person identified by their NRIC.\n"
            + "Parameters: -IC <NRIC> -I <Appointment Index>\n"
            + "Example: " + COMMAND_WORD + " -IC S1234567A -I 2";

    public static final String MESSAGE_SUCCESS = "Appointment at index %1$d removed for person with NRIC %2$s.";
    public static final String MESSAGE_FAILURE = "Failed to remove appointment: %1$s";

    private final String nric;
    private final int index;

    /**
     * Constructs a RmApptCommand with the specified NRIC and appointment index.
     *
     * @param nric  The NRIC of the person.
     * @param index The 1-based appointment index to remove.
     */
    public RmApptCommand(String nric, int index) {
        requireNonNull(nric);
        this.nric = nric;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            //model.removeAppointment(nric, index);
            return new CommandResult(String.format(MESSAGE_SUCCESS, index, nric));
        } catch (Exception e) {
            throw new CommandException(String.format(MESSAGE_FAILURE, e.getMessage()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof RmApptCommand
                && nric.equals(((RmApptCommand) other).nric)
                && index == ((RmApptCommand) other).index);
    }
}
