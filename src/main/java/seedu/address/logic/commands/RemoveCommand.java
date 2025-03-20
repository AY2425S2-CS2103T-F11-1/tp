package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by their NRIC.\n"
            + "Parameters: " + PREFIX_NRIC + " NRIC (must be the complete and valid NRIC)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NRIC + " S1234567A";

    public static final String MESSAGE_REMOVE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Nric targetNric;

    public RemoveCommand(Nric targetNric) {
        this.targetNric = targetNric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToDelete = model.findPerson(targetNric);

        if (personToDelete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_NRIC);
        }

        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_REMOVE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemoveCommand)) {
            return false;
        }

        RemoveCommand otherRemoveCommand = (RemoveCommand) other;
        return targetNric.equals(otherRemoveCommand.targetNric);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetNRIC", targetNric)
                .toString();
    }
}
