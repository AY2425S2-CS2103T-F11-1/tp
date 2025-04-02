package seedu.address.ui;

import java.time.LocalDateTime;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonAppointmentCard extends UiPart<Region> {

    private static final String FXML = "PersonAppointmentListCard.fxml";

    public static final String PAST_APPOINTMENTS_LABEL= "Past appointments:";
    public static final String UPCOMING_APPOINTMENTS_LABEL= "Upcoming appointments:";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    // @FXML
    // private Label name;
    // @FXML
    // private Label id;
    // @FXML
    // private Label phone;
    // @FXML
    // private Label nric;
    // @FXML
    // private Label dateOfBirth;
    // @FXML
    // private FlowPane appointments;
    // @FXML
    // private FlowPane tags;
    @FXML
    private Label upcomingAppointmentsLabel;
    @FXML
    private Label pastAppointmentsLabel;
    @FXML
    private FlowPane upcomingAppointments;
    @FXML
    private FlowPane pastAppointments;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonAppointmentCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        // id.setText(displayedIndex + ". ");
        // name.setText(person.getName().fullName);
        // nric.setText(person.getNric().value);
        // phone.setText(person.getPhone().value);
        // dateOfBirth.setText(person.getDateOfBirth().toString());
        upcomingAppointmentsLabel.setText(UPCOMING_APPOINTMENTS_LABEL);
        pastAppointmentsLabel.setText(PAST_APPOINTMENTS_LABEL);
        person.getAppointmentList().stream()
                .filter(appointment -> appointment.getApptDateTime().isAfter(
                            // LocalDateTime.now()))
                            LocalDateTime.of(2025, 5, 12, 14, 30)))
                .sorted(Comparator.comparing(appointment -> appointment.toString()))
                .forEach(appointment -> upcomingAppointments.getChildren().add(new Label(appointment.toString())));
        person.getAppointmentList().stream()
                .filter(appointment -> !appointment.getApptDateTime().isAfter(
                            // LocalDateTime.now()))
                            LocalDateTime.of(2025, 5, 12, 14, 30)))
                .sorted(Comparator.comparing(appointment -> appointment.toString()))
                .forEach(appointment -> pastAppointments.getChildren().add(new Label(appointment.toString())));
                // .forEach(appointment -> tags.getChildren().add(new Label(appointment.toString())));
        // person.getTags().stream()
        //          .sorted(Comparator.comparing(tag -> tag.tagName))
        //          .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
