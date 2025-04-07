package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class RmApptCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void addAppt_nullNric_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RmApptCommand(null, 1));
    }

    @Test
    public void removeAppt_invalidPerson_fail() {
        RmApptCommand command = new RmApptCommand("S1234567A", 1); // no person yet
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void removeAppt_validAppointment_success() throws CommandException {
        AddApptCommand addApptCommand = new AddApptCommand("S3286024I", "04/04/2025 16:00");
        addApptCommand.execute(model);
        RmApptCommand command = new RmApptCommand("S3286024I", 1);
        assertDoesNotThrow(() -> command.execute(model));
    }

    @Test
    public void equals() {
        RmApptCommand commandFirst = new RmApptCommand("S1234567A", 1);
        RmApptCommand commandSecond = new RmApptCommand("S1234567A", 1);
        RmApptCommand commandThird = new RmApptCommand("S1234567A", 2);
        RmApptCommand commandFourth = new RmApptCommand("S3286024I", 1);

        assertEquals(commandFirst, commandSecond);
        assertNotEquals(commandFirst, commandThird);
        assertNotEquals(commandFirst, commandFourth);
    }

}
