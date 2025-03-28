package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NricPredicate;
import seedu.address.model.person.Person;

import java.util.List;

/**
 * Adds an appointment to a person's appointment list.
 */
public class AddApptCommand extends Command {

    public static final String COMMAND_WORD = "addappt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an appointment for a person identified by their IC number.\n"
            + "Parameters: -IC <IC number> -D <date in dd/MM/yyyy HH:mm format>\n"
            + "Example: " + COMMAND_WORD + " -IC S1234567A -D 12/03/2025 14:30";

    public static final String MESSAGE_PATIENT_FOUND = "Patient found: %1$s";
    public static final String MESSAGE_PATIENT_NOT_FOUND = "Patient with NRIC %1$s not found";

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

        List<Person> filteredPersons = model.getFilteredPersonList();

        if (filteredPersons.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_PATIENT_NOT_FOUND, ic));
        }

        // We expect only one person to match by NRIC since NRIC is unique
        Person patientFound = filteredPersons.get(0);
        patientFound.addAppointment(date);

        model.updateFilteredPersonList(new NricPredicate(ic));

        return new CommandResult(String.format(MESSAGE_SUCCESS, ic, date));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddApptCommand
                && ic.equals(((AddApptCommand) other).ic)
                && date.equals(((AddApptCommand) other).date));
    }
}
