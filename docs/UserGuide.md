---
layout: page
title: User Guide
---

PartyPlanet is a **desktop app for managing birthdays of contacts, optimised for use via a Command Line 
Interface** (CLI) while still having the benefits of Graphical User Interface (GUI). If you can type fast, 
PartyPlanet can get the planning of your birthday celebrations done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `PartyPlanet.jar` from [here](https://github.com/AY2021S2-CS2103-W16-3/tp/releases).

3. Double-click the file to start the app.<br>
   ![Ui](images/Ui.png)

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add -n NAME`, `NAME` is a parameter which can be used as `add -n John Doe`.

* Items in square brackets are optional.<br>
  e.g. `-n NAME [-t TAG]` can be used as `-n John Doe -t friend` or as `-n John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[-t TAG]…​` can be used as ` ` (i.e. 0 times), `-t friend`, `-t friend -t family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `-n NAME -p PHONE_NUMBER`, the alternative `-p PHONE_NUMBER -n NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `-p 12341234 -p 56785678`, only `-p 56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `exit 123`, it will be interpreted as `exit`.

</div>

### Adding contacts : `add`

Adds a person to the PartyPlanet's Contacts List.

Format: `add -n NAME [-p PHONE_NUM] [-e EMAIL] [-a ADDRESS] [-t TAG]…​ [-b BIRTHDAY]​`<br>
* The birthday must be in a valid date format, e.g. 13 Jan

Examples:
* `add -n James Ho -p 22224444 -e jamesho@example.com -a 123, Clementi Rd, 1234665 -t friend -t colleague -b 1 Jan` Adds a new person James Ho with specified details.

### Clearing all data : `clear`

Removes all contacts from the PartyPlanet's Contact List.

Format: `clear`

### Deleting contacts : `delete`

Deletes the specified person from the PartyPlanet's Contact List.

Format: `delete {INDEX [INDEX]... | -t TAG [-t TAG]...}`
* If provided with index(es)
  * Deletes the person at the specified `INDEX`.
  * All indexes refers to the index number shown in the displayed person list (without sorting).
  * All indexes must be a positive integer valid in the list.
* If provided with tags
  * Delete every person who is tagged with the specified tag.
  * If the person is tagged with another tag, only the specified tag will be removed. The contact will not be deleted.

Examples:
* `delete 3` deletes contact at 3rd index.
* `delete 3 4 5` deletes contacts at 3rd, 4th and 5th index.
* `delete -t colleague` deletes contact with tag "colleague".
* `delete -t colleague -t cs2103` deletes contacts with tag "colleague" and contacts with tag "cs2103"

### Editing contacts : `edit`

Edits an existing person in the PartyPlanet's Contact List.

Format: `edit INDEX [-n NAME] [-p PHONE_NUMBER] [-e EMAIL] [-a ADDRESS] [-t TAG]…​ [-b BIRTHDAY]`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list (not sorted). The index must be a positive integer that is a valid number in the list.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `-t` without specifying any tags after it.

Examples:
*  `edit 2 -n James Lee -e jameslee@example.com` edits the contact name to be “James Lee” and email address to be “jameslee@example.com”.
*  `edit 2 -n Betsy Crower -t` Edits the name of the 2nd person to be Betsy Crower and clears all existing tags.

### Finding contacts : `find`

Finds persons whose names contain the given keywords and/or is associated with the given tag.

Format: `find [-n NAME] [-t TAG]`

* The search is case-insensitive. e.g. `hans` will match `Hans`.
* Partial matches to names are returned, e.g. `lliam` will match `williams`.
* Only full tags will be matched.


Examples:
* `find -n Bob -t cs2103` Finds contacts where the name contains Bob and the contact contains the tag cs2103.

### Listing contacts : `list`

Shows a list of all persons in the PartyPlanet's Contact List.

Format: `list [-s SORT_ORDER]`
* List out all contacts by default.
* `-s` list out all contacts sorted according to `SORT_ORDER`.
* Possible values of `SORT_ORDER`:
  * `asc`: ascending lexicographical order
  * `desc`: descending lexicographical order
  * `bday`: in ascending order from Jan-01 to Dec-31

Examples:
* `list` Lists out all the contacts in PartyPlanet.
* `list -s asc` Lists out all the contacts in ascending lexicographical order.

### Finding tags : `tags`

Finds all tags or tags that contain the given keywords.

Format: `tags [-f KEYWORD]`

Examples:
* `tags` lists out all tags available.
* `tags -f cs2103` lists out all tags that contain `cs2103`.

### Showing help : `help`

Shows a message explaining a list of available commands.

Format: `help [COMMAND]`
* List all available commands.
* `[COMMAND]` a single parameter requesting help for a specific command's syntax.
* Any additional parameters will be ignored.
* If command is not understood then all available commands will be listed.

Examples:
* `help` lists all available commands.
* `help list` shows the syntax and description for the `list` command.

### Leaving app : `exit`

Exits the app.

Format: `exit`

### InputHistory / Keyboard shortcuts :

Retrieves previously entered input.

* Entering new command adds new entry to InputHistory.
* InputHistory will save last 20 inputs.
* Pressing `Up` arrow key in the text input panel reverts to earlier input.
* Pressing `Down` arrow key undoes the history revert.
* At the most recent input, pressing `Down` arrow key once more clears the text box.
* `ESC` key clears the text box.

### Coming Soon (Additional Features)
* Add/Edit/Delete Modules and Tasks/Deliverables
* Archiving of Data Files
* Night Mode
* Mass Operations (e.g. mass delete, mass edit)
* Calendar View
* _and much more!_

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: **What is the name of your application?**<br>
**A**: PartyPlanet

**Q**: **How much does it cost?**<br>
**A**: Free!

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add -n NAME [-p PHONE_NUM] [-e EMAIL] [-a ADDRESS] [-t TAG]…​ [-b BIRTHDAY]` <br> e.g., `add -n James Ho -p 96280000 -t friend -t colleague`
**Clear** | `clear`
**Delete** | `delete {INDEX [INDEX]... | -t TAG [-t TAG]...}`<br> e.g., `delete 3 4 5` <br> e.g., `delete -t colleague`
**Edit** | `edit INDEX [-n NAME] [-p PHONE_NUMBER] [-e EMAIL] [-a ADDRESS] [-t TAG]…​ [-b BIRTHDAY]`<br> e.g.,`edit 2 -n James Lee -e jameslee@example.com`<br> e.g., `edit 2 -n Betsy Crower -t colleague`
**Find** | `find [-n NAME] [-t TAG]`<br> e.g., `find -n Bob -t cs2103`
**List** | `list [-s SORT_ORDER]`<br> e.g., `list`<br> e.g., `list -s asc`
**Find tags** | `tags [-f KEYWORD]`<br> e.g.,`tags`<br> e.g., `tags -f cs2103`
**Help** | `help [COMMAND]`<br> e.g., `help`<br> e.g.,`help list`
**Exit** | `exit`
