package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NricPredicate;
import seedu.address.model.person.Person;

/**
 * Views patients in the address book by filtering with a specific NRIC.
 */
public class ViewpCommand extends Command {

    public static final String COMMAND_WORD = "viewp";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays details of a specific patient by NRIC.\n"
            + "Parameters: " + PREFIX_NRIC + "NRIC\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NRIC + "S1234567A";

    public static final String MESSAGE_PATIENT_FOUND = "Patient found: %1$s";
    public static final String MESSAGE_PATIENT_NOT_FOUND = "Patient with NRIC %1$s not found";

    private final String nric;

    /**
     * Creates a ViewpCommand to find and display a patient by the given NRIC.
     */
    public ViewpCommand(String nric) {
        requireNonNull(nric);
        this.nric = nric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredPersonList(new NricPredicate(nric));
        List<Person> filteredPersons = model.getFilteredPersonList();

        if (filteredPersons.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_PATIENT_NOT_FOUND, nric));
        }

        // We expect only one person to match by NRIC since NRIC is unique
        Person patientFound = filteredPersons.get(0);
        return new CommandResult(String.format(MESSAGE_PATIENT_FOUND, patientFound.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ViewpCommand)) {
            return false;
        }

        ViewpCommand otherCommand = (ViewpCommand) other;
        return nric.equals(otherCommand.nric);
    }
}
