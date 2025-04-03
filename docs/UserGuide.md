---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# HubHealth User Guide

## About HubHealth
HubHealth is a **Clinic Appointment Management System** designed to empower clinic receptionists to **manage
patient information** and **schedule appointments** efficiently. As a clinic receptionist, you can easily manage patient 
records (including name, birth date, contact information, etc.) and track past and upcoming appointments with 
simple keyboard commands. Additionally, the tagging system allows you to tag government-related subsidies
(such as CHAS) or insurance information to your patient, simplifying patient management.

If you are unfamiliar with any terminology in the HubHealth User Guide, refer to the [Glossary](#6-glossary).

## Table of contents
  * [About HubHealth](#about-hubhealth)
  * [Table of contents](#table-of-contents)
  * [1. Quick start](#1-quick-start)
    * [1.1 Installation](#1-1-installation)
    * [1.2 Getting started](#1-2-getting-started)
  * [2. Features](#2-features)
    * [2.1 Patient management](#2-1-patient-management)
      * [2.1.1 Listing all patients : `list`](#2-1-1-listing-all-patients-list)
      * [2.1.2 Locating patients by name: `find`](#2-1-2-locating-patients-by-name-find)
      * [2.1.3 Adding a patient: `add`](#2-1-3-adding-a-patient-add)
      * [2.1.4 Removing a patient : `remove`](#2-1-4-removing-a-patient-remove)
    * [2.2 Appointment management (for patient)](#2-2-appointment-management-for-patient)
      * [2.2.1 Viewing patient details : `viewp`](#2-2-1-viewing-patient-details-viewp)
      * [2.2.2 Adding appointment to patient : `addappt`](#2-2-2-adding-appointment-to-patient-addappt)
      * [2.2.3 Removing appointment from patient : `rmappt`](#2-2-3-removing-appointment-from-patient-rmappt)
    * [2.3 HubHealth general features](#2-3-hubhealth-general-features)
      * [2.3.1 Viewing help : `help`](#2-3-1-viewing-help-help)
      * [2.3.2 Clearing all entries : `clear`](#2-3-2-clearing-all-entries-clear)
      * [2.3.3 Exiting HubHealth : `exit`](#2-3-3-exiting-hubhealth-exit)
    * [2.4 Coming soon](#2-4-coming-soon)
    * [2.5 Saving the data](#2-5-saving-the-data)
    * [2.6 Editing the data file](#2-6-editing-the-data-file)
  * [3. FAQ](#3-faq)
  * [4. Known issues](#4-known-issues)
  * [5. Command summary](#5-command-summary)
  * [6. Glossary](#6-glossary)

--------------------------------------------------------------------------------------------------------------------

## 1. Quick start
This section provides a quick start to HubHealth. If HubHealth is already installed on your computer, you may 
skip the Installation and jump to [Getting Started](#1-2-getting-started).

### 1.1 Installation

1. Ensure you have Java `17` or above installed in your Computer. This is required to run HubHealth. <br>
   - **Checking whether you have the right version of Java installed:**<br>
     - **Windows users:** Press the Windows key, or use the Start menu to search for `Java`. If there are no results that lead to an application. You do not have Java installed. Otherwise, click on `About Java`, and it show you the version of Java currently downloaded on your computer.<br>
     - **Mac users:** Open a new Terminal window, and enter `java -version`. Hit enter and you should see the Java version downloaded on your computer.<br>
   - **Installing Java:** If you do not have Java installed or do not have the right version, follow the instructions [here](https://www.java.com/en/download/help/download_options.html) to download Java `17`. <br>
     - **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/AY2425S2-CS2103T-F11-1/tp/releases). This is the HubHealth application.

### 1.2 Getting started

1. Copy the file to the folder you want to use as the _home folder_ for HubHealth.

1. Open a command terminal, and enter `cd <directory>`, where `<directory>` is the path to the folder you put the jar file in. <br>
   - For instance, if the application is in the Downloads folder, the command you enter should look something like `cd C:\Users\user\Downloads`<br>
   - (You can find the location of the application by right-clicking on it, and selecting `Properties` or `Get info` for Windows and Mac users.)

1. Next, enter the command `java -jar HubHealth.jar` and press Enter to start the application.<br>
   - A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all patients recorded in HubHealth.

   * `add -N John Doe -P 98765432 -IC S7257684E -DOB 03/10/1998` : Adds a patient named `John Doe` to HubHealth.

   * `remove -IC S7257684E` : Removes the patient with the NRIC `S7257684E`.

   * `clear` : Removes all patients recorded in HubHealth.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 2. Features

<box type="info" seamless class="info-custom">

**Notes about the command format:**<br>

* Words in `<>` are the parameters to be supplied by the user.<br>
  e.g. in `add -IC <NRIC> -N <Name> -P <Phone_Number> -DOB <Date_Of_Birth>`, `<Name>` is a parameter which can be used as `add -N John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `-N <Name> -P <Phone_Number>` in the command format, `-P <Phone_Number> -N <Name>` is also acceptable.

* Items in square brackets are optional.<br>
  e.g `find <Name> [MORE_NAMES]` can be used as `find Alex` or as `find Alex David`.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `ls`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### 2.1 Patient management

#### 2.1.1 Listing all patients : `list`

Shows you a list of all patients in HubHealth.

Format: `list`


<box type="tip" seamless class="tip-custom">

**Tip:** You can also use `ls` to list all patients in HubHealth.
</box>

#### 2.1.2 Locating patients by name: `find`

Allows you to search for patients whose names contains a given name.

Format: `find <Name> [MORE_NAMES]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Partial words will be matched e.g. `Han` will match `Hans`
* Patients whose name is matching with at least one keyword will be returned (i.e. `OR` search).
  e.g. Finding `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>


#### 2.1.3 Adding a patient: `add`

Allows you to add a new patient to HubHealth.

Format: `add -IC <NRIC> -N <Name> -P <Phone_Number> -DOB <Date_Of_Birth>`

Example:
* `add -IC T0000000A -N John Doe -P 81234567 -DOB 2005-02-02`

#### 2.1.4 Removing a patient : `remove`

Allows you to remove the specified patient from HubHealth.

Format: `remove -IC <NRIC>`
<box type="tip" seamless class="tip-custom">

**Tip:** You can also use `rm` instead of `remove`.
</box>

* Deletes the patient with the specified `<NRIC>`.

<br>
<br>

### 2.2 Appointment management (for patient)
#### 2.2.1 Viewing patient details : `viewp`

Displays all patient details to you.

Format: `viewp -IC <NRIC>`

Example:
* `viewp -IC T01234567X` displays all the details of the patient whose IC is T01234567X

#### 2.2.2 Adding appointment to patient : `addappt`

Allows you to create an appointment for a patient.

Format: `addappt -IC <NRIC> -D <date in dd/MM/yyyy HH:mm>`

Example:
* addappt -IC S9123456Z -D 25/06/2025 09:00

#### 2.2.3 Removing appointment from patient : `rmappt`

Allows you to remove an appointment from a patient at the specified index.
<box type="tip" seamless class="tip-custom">

**Tip:** Use viewp first to view the appointment list for a specified patient
</box>

Format: `rmappt -IC <NRIC> -I <index in appointment list>`

Example:
* rmappt -IC S9123456Z -I 1

<br>
<br>

### 2.3 HubHealth general features

#### 2.3.1 Viewing help : `help`

Shows you a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

#### 2.3.2 Clearing all entries : `clear`

Clears all entries from HubHealth.

Format: `clear`

<box type="tip" seamless class="tip-custom">
A useful first step to creating your own patient record: Deleting the existing sample data.
</box>

<box type="warning" seamless class="warning-custom">
We do not support undoing commands yet. Clearing of patient records is thus permanent. Use this command wisely.
</box>

#### 2.3.3 Exiting HubHealth : `exit`

Allow you to exit HubHealth.

Format: `exit`

<br>
<br>

### 2.4 Coming soon
1. Allow “/” and other special characters in name
   * Name currently only supports alphanumeric characters and spaces, and does not support characters such as “/”, 
     “@” (list is non-exhaustive) 
   * For example, the name “Ravi S/O Ramasamy” will not be allowed 
   * Support for “/”, “@” and other special characters used in the name will be added in a future release

<br>

2. Allow editing of patient information
   * For example, allow phone number and tags (tracking patient’s CHAS information, insurance information etc.) to be 
   editable
   * However, a patient’s NRIC and Date of Birth will remain un-editable once a patient has been created

<br>

3. Allow name searches to match only names that start with any of the keywords
   * The `find` command currently returns patients whose name contain any of the keywords in any part of their name
   * For example, `find Le` can return patients named "Levanne" and "Violet"
   * This search may be refined to only return patients whose name starts with a keyword in a future release.

<br>
<br>

### 2.5 Saving the data

HubHealth data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

<br>
<br>

### 2.6 Editing the data file

HubHealth data are saved automatically as a JSON file `[JAR file location]/data/HubHealth.json`. Advanced users are 
welcome to update data directly by editing that data file.


<box type="warning" seamless class="warning-custom">
If your changes to the data file makes its format invalid, HubHealth will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the HubHealth to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>


--------------------------------------------------------------------------------------------------------------------

## 3. FAQ

**Q**: Can I use HubHealth on multiple computers?<br>
**A**: No, HubHealth is built for private clinics who are moving to digital-based processes for the first time. 
HubHealth is meant to be simple, and runs in a standalone environment, and does not support multiple computers.

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install HubHealth in the other computer and overwrite the empty data file it creates with the file that contains 
the data of your previous HubHealth home folder.

**Q**: Can I undo a command if I make a mistake?<br>
**A**: No, the undo feature is currently not supported. 

--------------------------------------------------------------------------------------------------------------------

## 4. Known issues

1. **When using multiple screens**, if you move HubHealth to a secondary screen, and later switch to using only
the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the
application before running HubHealth again. Alternatively, you may press the `Windows Key` + `Right-Arrow Key`
to bring HubHealth back into the primary screen.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## 5. Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add -IC <NRIC> -N <Name> -P <Phone number> -DOB <Date of birth in dd/MM/yyyy format>`<br> e.g. `add -IC S1234567A -N John Doe -P 81234567 -DOB 02/02/1998`
**Remove** | `remove -IC <NRIC>`, `rm -IC <NRIC>`<br> e.g. `remove -IC S1234567A`
**View Patient** | `viewp -IC <NRIC>`<br> e.g. `viewp -IC S1234567A`
**Add appointment** | `addappt -IC <NRIC> -D <Date in dd/MM/yyyy HH:mm format>`<br> e.g. `addappt -IC S1234567A -D 12/03/2025 14:30`
**Remove appointment** | `rmappt -IC <NRIC> -I <Appointment index>`<br> e.g. `rmappt -IC S1234567A -I 1`
**Find**   | `find <Name> [MORE_NAMES]`<br> e.g. `find James Jake`
**Clear**  | `clear`
**List**   | `list`, `ls`
**Help**   | `help`
**Exit**   | `exit`

--------------------------------------------------------------------------------------------------------------------

## 6. Glossary

Term       |  Meaning
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**GUI**    |  The Graphical User Interface. This is what you see on the screen when you run most software. It has some visual/ graphical elements you can interact with (such as by clicking) without the use of keyboard commands.
**IC/ NRIC** |  They are used interchangeably to refer to the National Registration Identity Card number. In the current release of HubHealth, NRIC is referred to as `IC` in the commands. 
**Patient/ Person** |  In the current release of HubHealth, any reference to a `Person` is interchangeable with a patient. A `Person` in HubHealth represents a patient and their information. 
