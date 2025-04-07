package seedu.address.ui;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

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

    public static final String UPCOMING_APPOINTMENTS_LABEL = "Upcoming appointments:";
    public static final String PAST_APPOINTMENTS_LABEL = "Past appointments:";

    private static final String FXML = "PersonAppointmentListCard.fxml";

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
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label nric;
    @FXML
    private Label dateOfBirth;
    @FXML
    private FlowPane tags;
    @FXML
    private Label pastAppointmentsLabel;
    @FXML
    private Label upcomingAppointmentsLabel;
    @FXML
    private FlowPane pastAppointments;
    @FXML
    private FlowPane upcomingAppointments;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonAppointmentCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        AtomicInteger index = new AtomicInteger(1);

        name.setText(person.getName().fullName);
        nric.setText(person.getNric().value);
        phone.setText(person.getPhone().value);
        dateOfBirth.setText(person.getDateOfBirth().toString());
        person.getTags().stream()
                 .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        pastAppointmentsLabel.setText(PAST_APPOINTMENTS_LABEL);
        upcomingAppointmentsLabel.setText(UPCOMING_APPOINTMENTS_LABEL);
        person.getAppointmentList().stream()
                .filter(appointment -> !appointment.getApptDateTime().isAfter(LocalDateTime.now()))
                .forEach(appointment -> {
                    int i = index.getAndIncrement();
                    pastAppointments.getChildren().add(new Label(i + ". " + appointment.toString()));
                });
        person.getAppointmentList().stream()
                .filter(appointment -> appointment.getApptDateTime().isAfter(LocalDateTime.now()))
                .forEach(appointment -> {
                    int i = index.getAndIncrement();
                    upcomingAppointments.getChildren().add(new Label(i + ". " + appointment.toString()));
                });
    }
}
