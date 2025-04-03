---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# AB-3 User Guide

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add -IC T0000000A -N John Doe -P 81234567 -DOB 2005-02-02` : Adds a patient named `John Doe` to the Address Book.

   * `delete -IC <NRIC>` : Deletes the patient with the given NRIC.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `<>` are the parameters to be supplied by the user.<br>
  e.g. in `add -IC <NRIC> -N <Name> -P <Phone_Number> -DOB <Date_Of_Birth>`, `<Name>` is a parameter which can be used as `add -N John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `-N <Name> and -P <Phone_Number>`, `-P <Phone_Number> -N <Name>` is also acceptable.

* Items in square brackets are optional.<br>
  e.g `find <Name> [MORE_NAMES]` can be used as `find Alex` or as `find Alex David`.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `ls`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows you a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a patient: `add`

Allows you to add a new patient to the address book.

Format: `add -IC <NRIC> -N <Name> -P <Phone_Number> -DOB <Date_Of_Birth>`

Examples:
* `add -IC T0000000A -N John Doe -P 81234567 -DOB 2005-02-02`

### Listing all patients : `list`

Shows you a list of all patients in the address book.

Format: `list`

<box type="tip" seamless>

**Tip:** You can also use `ls` to list all patients in the address book.
</box>

### Locating patients by name: `find`

Allows you to search for patients whose names contains a given name.

Format: `find <Name> [MORE_NAMES]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Patients whose name is matching with at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>

### Viewing patient details

Displays all patient details to you.

Format: viewp -IC <NRIC>

Examples:
* `viewp -IC T01234567X` displays all the details of the patient whose IC is T01234567X

### Removing a patient : `remove`

Allows you to remove the specified patient from the address book.

Format: `remove -IC <NRIC>`

* Deletes the patient with the specified `<NRIC>`.

### Adding appointment to patient : `addappt`

Allows you to create an appointment for a patient.

Format: `addappt -IC <NRIC> -D <date in dd/MM/yyyy HH:mm>`

Example:
* addappt -IC S9123456Z -D 25/06/2025 09:00

### Removing appointment from patient : `rmappt`

Allows you to remove an appointment from a patient at the specified index. 
* Use viewp first to view the appointment list for a specified patient

Format: `rmappt -IC <NRIC> -I <index in appointment list>`

Example:
* rmappt -IC S9123456Z -I 1

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Allow you to exit the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add add -IC <NRIC> -N <Name> -P <Phone_Number> -DOB <Date_of_Birth> <br>` e.g., `add -IC T0000000A -N John Doe -P 81234567 -DOB 2005-02-02`
**Remove** | `remove -IC <NRIC>`
**View Patient** | `viewp -IC <NRIC>`
**Find**   | `find <Name> [MORE_NAMES]`<br> e.g., `find James Jake`
**Add Appointment** | `addappt -IC <NRIC> -D <date in dd/MM/yy HH:mm>`
**Remove Appointment** | `rmappt -IC <NRIC> -I <index>`
**Clear**  | `clear`
**List**   | `list`, `ls`
**Help**   | `help`
