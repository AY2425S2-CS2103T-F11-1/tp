package seedu.address.ui;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.appointment.Appointment;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonAppointmentCard extends UiPart<Region> {

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
    private Label id;
    @FXML
    private FlowPane appointments;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonAppointmentCard(Person person, int appointmentIndex) {
        super(FXML);
        this.person = person;

        VBox appointmentsContainer = new VBox();
        appointmentsContainer.setSpacing(5);
        List<Appointment> appointmentList = person.getAppointmentList();

        for (int i = 0; i < appointmentList.size(); i++) {
            VBox appointmentCard = new VBox();
            appointmentCard.setStyle("-fx-border-color: #ffffff; "
                    + "-fx-border-radius: 5; -fx-padding: 5; -fx-background-color: #f9f9f9;");

            Label title = new Label("Appointment " + (i + 1));
            title.setStyle("-fx-font-weight: bold;");

            Label time = new Label("Time: " + appointmentList.get(i));

            System.out.println(title);
            System.out.println(time);

            appointmentCard.getChildren().addAll(title, time);
            appointmentsContainer.getChildren().add(appointmentCard);
        }

        appointments.getChildren().add(appointmentsContainer);
    }
}
