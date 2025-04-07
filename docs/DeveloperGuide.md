---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# HubHealth Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

The layout and design elements are adapted from the User Guide
[here](https://ay2223s1-cs2103t-w16-2.github.io/tp/UserGuide).

LLM was used to edit the [User stories](#user-stories) section for consistency and clarity.

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2425S2-CS2103T-F11-1/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2425S2-CS2103T-F11-1/tp/blob/master/src/main/java/seedu/address/MainApp.java) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): All things UI-related for the App.
* [**`Logic`**](#logic-component): The command parser and executor.
   * **`Commands`**: Available commands for the App.
   * **`Parser`**: Input parsing and validation.
* [**`Model`**](#model-component): Holds the data of the App in memory and contains all definable classes.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user 
issues the command `remove S1234567A`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

In this interaction, the `UI` component takes in the command `remove S1234567A`, which is passed to the `Logic` 
component. It checks whether this command exists, and whether the syntax is valid. If so, it executes, removing the 
person with the NRIC of `S1234567A` from `Model`. This change needs to be saved in order for it to persist, and so 
`Storage` saves this change to the file on the hard disk. 

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2425S2-CS2103T-F11-1/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2425S2-CS2103T-F11-1/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2425S2-CS2103T-F11-1/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

<box type="info" seamless class="info-custom">

**Notes about NRIC when using various commands**<br>

* For example when removing a person:
* Rationale for not supporting `remove INDEX_NUMBER`, where `INDEX_NUMBER` is the index of a patient in HubHealth.
* While `remove INDEX_NUMBER` is an easier command, this is a deliberate design decision to ensure that
  you go through 1 more round of checking (**implicit verification built into HubHealth** as opposed to explicit
  verification, asking "Are you sure you want to delete patient John Doe, NRIC: S9123456Z").
* This ensures patient records are not accidentally deleted, for example using `delete 3` vs `delete 4`.

  </box>

### Logic component

**API** : [`Logic.java`](https://github.com/AY2425S2-CS2103T-F11-1/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2425S2-CS2103T-F11-1/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless class="info-custom">

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>

<box type="info" seamless class="info-custom">

**Note: To avoid overzealous input validation for appointment date and time, we allow:**
* Appointments with similar start times to be created,
* Multiple patients to have the same appointment start time, and
* Appointments to be created with a starting date that is in the past (relative to the local machine), for record keeping purposes.

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2425S2-CS2103T-F11-1/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Extend Person class to support child subclasses

#### Proposed Implementation
Currently, HubHealth only supports 1 type of Person - patient. Therefore, the model continues to use Person for the 
name as part of the model, and is representing the patient.

As some clinics have locum doctors, the Person object will be extended in the future to support Patient and Doctor
subclasses. Common identity fields such as Name, Phone will be kept in the parent class, while data fields such as tags
and appointmentList will be moved to the Patient subclass.

<puml src="diagrams/PersonModelClassDiagram.puml" width="450" />

<br>

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:
Clinic receptionist that needs to track patients contact and appointments using CLI.

* has a need to manage a significant number of patients' contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Provide fast access to patients contact details, and manage upcoming appointments, optimized for users who prefer a CLI.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a/an …​                | I want to …​                                        | So that I can …​                                                      |
|----------|---------------------------|-----------------------------------------------------|-----------------------------------------------------------------------|
| `* * *`  | daily user                | add contact details of new patients                 | track all patients' contact details                                   |
| `* * *`  | temporary user            | learn the commands quickly                          | perform my role soon after getting my job                             |
| `* *`    | potential user            | see the app populated with sample data              | see what the app looks like when it is in use                         |
| `* *`    | user ready to use the app | clear the sample data                               | start using the app                                                   |
| `* *`    | daily user                | tag groups of patients                              | find these patients more easily in the future                         |
| `* *`    | daily user                | identify which patients I need to contact           | ensure no one is missed                                               |
| `* *`    | long-time user            | quickly update changes to contact information       | respond to requests even during peak hours                            |
| `* *`    | long-time user            | view patients' appointments                         | contact the patients to remind them of their upcoming appointments    |
| `* *`    | new user                  | find my way around the app                          | eventually learn to use the app effectively                           |
| `* *`    | new user                  | receive reminders about the correct command formats | use the correct commands without having to refer to the documentation |
| `* *`    | user                      | undo changes I made                                 | correct errors I made while entering the data                         |
| `* *`    | user                      | know if I entered some invalid data                 | quickly catch typos I made                                            |
| `* *`    | user                      | know patients' past medication                      | support the doctors' needs                                            |
| `*`      | long-time user            | delete patients who no longer come to the clinic    | focus on patients the clinic has                                      |
| `*`      | long-time user            | delete patients who have passed away                | comply with PDPA regulations                                          |
| `*`      | long-time user            | sift through the data quickly                       | find patient information quickly                                      |
| `*`      | user                      | track patients' CHAS colour                         | know if they qualify for subsidies                                    |

### Use cases

<box type="info" seamless class="info-custom">

For all use cases below, the **System** refers to `HubHealth` and the **Actor** is the `User`, unless specified 
otherwise.

</box>

#### **Use case: UC01 List all patients**

**MSS**

1. User requests to see a list of all patients.
2. HubHealth shows a list of patients.

    Use case ends.

#### **Use case: UC02 Add a patient**

**MSS**

1. User requests to add a new patient to the list.
2. HubHealth adds the new patient to the list.

    Use case ends.

**Extensions**

- 1a. HubHealth detects an error in the entered details.
    - 1a1. HubHealth shows an error message.

        Use case ends.

- 1b. HubHealth detects that the patient exists in the list.
    - 1b1. HubHealth shows an error message.

        Use case ends.

#### **Use case: UC03 Remove a patient**

**MSS**

1. User requests to remove a patient from the list.
2. HubHealth removes the patient from the list.

    Use case ends.

**Extensions**

- 1a. HubHealth detects that the patient does not exist.
    - 1a1. HubHealth shows an error message.

        Use case ends.

#### **Use case: UC04 View a patient's details**

**MSS**

1. User requests to view a patient’s details.
2. HubHealth shows the patient’s details.

    Use case ends.

**Extensions**

- 1a. HubHealth detects that the patient does not exist.
    - 1a1. HubHealth shows an error message.

        Use case ends.

#### **Use case: UC05 Add an appointment to a patient**

**MSS**

1. User requests to add an appointment to a patient.
2. HubHealth updates and shows the patient’s details.

    Use case ends.

**Extensions**

- 1a. HubHealth detects an error in the entered details.
    - 1a1. HubHealth shows an error message.

        Use case ends.

- 1b. HubHealth detects that the patient does not exist.
    - 1b1. HubHealth shows an error message.

        Use case ends.

#### **Use case: UC06 Delete an appointment from a patient**

**MSS**

1. User requests to remove an appointment from a patient.
2. HubHealth updates and shows the patient’s details.

    Use case ends.

**Extensions**

- 1a. HubHealth detects that the patient or the appointment does not exist.
    - 1a1. HubHealth shows an error message.

        Use case ends.

*{More to be added}*

### Non-Functional Requirements

1.  HubHealth should work on any _mainstream OS_ as long as it has Java `17` or above installed.
2.  HubHealth should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical 
    usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  HubHealth should work independent of internet connectivity.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **PDPA**: The Personal Data Protection Act (PDPA) 2012 sets out the law on data protection in Singapore. The PDPA
regulates the processing of personal data in the private sector.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file
       Expected: GUI appears and shows a set of sample patients. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Adding a patient

1. Adding a patient while all patients are being shown

   1. Prerequisites: List all patients using the `list` or `ls` command.

   1. Test case: `add -IC T0288759A -N John Tan -P 89897777 -DOB 02/02/2002`<br>
      Expected: New patient is added into the list. Details of the new patient shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `add -IC T -N John Tan -P 89897777 -DOB 02/02/2002`<br>
      Expected: No patient is added. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect add commands to try: `add`, `add -IC a -N John Tan -P c -DOB d`, `...` (where a, b, c, d are invalid NRIC, phone number and date respectively)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Remove a patient

1. Remove a patient while all patients are being shown

   1. Prerequisites: List all patients using the `list` or `ls` command. Multiple patients in the list. Patient with NRIC T0288759A in the list.

   1. Test case: `remove -IC T0288759A` or `rm -IC T0288759A`<br>
      Expected: Patient with NRIC T0288759A is removed from the list. Details of the removed patient shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `remove -IC 1`<br>
      Expected: No patient is removed. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect remove commands to try: `remove`, `remove x`, `...` (where x is a NRIC that is not in the list)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### View a patient's details

1. View a patient's details

   1. Prerequisites: Patient with NRIC T0288759A in the list.

   1. Test case: `viewp -IC T0288759A`<br>
      Expected: Details of the patient shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `viewp -IC 1`<br>
      Expected: Error details shown in the status message. Status bar remains the same.

   1. Other incorrect add commands to try: `viewp`, `viewp -a`, `...` (where a is an invalid flag)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Add an appointment to a patient

1. Add an appointment to a patient while the patient's details are being shown

   1. Prerequisites: Patient with NRIC T0288759A in the list. View patient's details using `viewp -IC T0288759A` command.

   1. Test case: `addappt -IC T0288759A -D 25/06/2025 17:00`<br>
      Expected: New appointment is added to the patient. Details of the new appointment shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `addappt -IC 1 -D 25/06/2025 -T 17:00`<br>
      Expected: Error details shown in the status message. Status bar remains the same.

   1. Other incorrect add commands to try: `addappt -IC x -D y`, `addappt`, `...` (where x, y are invalid NRIC, datetime respectively)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Remove an appointment from a patient

1. Remove an appointment from a patient while the patient's details are being shown

   1. Prerequisites: Patient with NRIC T0288759A in the list. Patient have multiple appointments. View patient's details using `viewp -IC T0288759A` command.

   1. Test case: `rmappt -IC T0288759A -I 1`<br>
      Expected: Appointment with index 1 is removed from the patient's details. Details of the removed appointment shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `rmappt -IC T0288759A -I 0`<br>
      Expected: Error details shown in the status message. Status bar remains the same.

   1. Other incorrect add commands to try: `rmappt -IC T0288759A -I x`, `rmappt`, `...` (where x is larger than the number of appointments the patient has)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _Prerequisites: At least one valid patient present in the list. Validity can be confirmed by the appearance of 
      the patient when listed with `list` or `ls`. The `data` folder should be present in the same location as the 
      jar file for the app. This can be guaranteed by starting the app once and closing it. 

   1. Test case (Missing data file): Close HubHealth (if it is open). Navigate to the `data` folder. Delete the `addressbook.json` file if it exists. Launch the app once again by double-clicking it.
      Expected: Default list of patients shown. There should be a list of patients displayed, starting with a 
      patient named "Alex Yeoh"
      
   1. Test case (Corrupted data files - invalid data): Close HubHealth (if it is open). Navigate to the `data` 
      folder. Change the birth year of any patient to 1899 and save the changes using any text editor. Launch the 
      app again by double-clicking it.
      Expected: No patients shown. 

   1. Test case (Corrupted data files - wrong format): Close HubHealth (if it is open). Navigate to the `data`
      folder. Delete the birth year of any patient (leaving something like 02/02/ in the file) and save the changes 
      using any text editor. Launch the app again by double-clicking it.
      Expected: No patients shown.

   1. Other ways to corrupt file data to try: Delete closing parentheses from the `addressbook.json` file, or delete 
      the NRIC completely from an entry in the file. Alternatively, change the NRIC values to illegal values like 
      "abc". Save the corrupting changes and launch the app again by double-clicking it. 
      Expected: Similar to previous.

1. _{ more test cases …​ }_

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Planned enhancements**

Team size: 5

1. Allow “/” and other special characters in name
    * Name currently only supports alphanumeric characters and spaces, and does not support characters such as “/”,
      “@” (list is non-exhaustive).
    * For example, the name “Ravi S/O Ramasamy” will not be allowed.
    * Support for “/”, “@” and other special characters used in the name will be added in a future release.

<br>

2. Allow editing of patient information
    * For example, allow phone number and tags (tracking patient’s CHAS information, insurance information etc.) to be
      editable.
    * However, a patient’s NRIC and Date of Birth will remain un-editable once a patient has been created.

<br>

3. Allow phone number to have verified/unverified option 
    * With the addition of OTP (One-Time Password) verification for phone numbers in a future release, this removes 
      the need for overzealous input validation on the phone number.

<br>

4. Allow editing of appointment information
    * For example, to be able to change the starting time of an appointment

<br>

5. Support multiple doctors per clinic 
   * HubHealth will not enforce appointment limits across patients with the same appointment time, as clinics may have multiple doctors handling separate appointments simultaneously. 
   * Duplicate time slots will only be restricted per doctor, not globally
