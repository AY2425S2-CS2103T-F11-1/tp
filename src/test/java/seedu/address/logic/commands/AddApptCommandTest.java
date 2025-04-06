package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class AddApptCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddApptCommand("S3286024I", null));
    }

    @Test
    public void addAppt_nullNric_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddApptCommand(null, "02/02/2026 15:00"));
    }

    @Test
    public void addAppt_success() throws CommandException {
        AddApptCommand command = new AddApptCommand("S3286024I", "02/02/2026 15:00");
        CommandResult commandResult = command.execute(model);
        assertEquals(new CommandResult(String.format(AddApptCommand.MESSAGE_SUCCESS,
                "S3286024I", "02/02/2026 15:00")), commandResult);
    }

    @Test
    public void addAppt_sameAppointment_fail() throws CommandException {
        AddApptCommand command = new AddApptCommand("S3286024I", "02/02/2026 17:00");
        command.execute(model);
        assertThrows(CommandException.class, () -> command.execute(model));
    }

}
