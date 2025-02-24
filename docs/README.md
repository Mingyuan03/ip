# YasuMax (安Max) - Your Personal Study Companion! 🧑‍🎓👩‍🎓
YasuMax is a user-friendly academic task tracker designed to help students manage their study commitments with ease.<br>
It is optimised for use via a **Command Line Interface** (CLI) while having a nice *Graphical User Interface* (GUI).<br>
Whether you're juggling assignment deadlines in JC, hackathon project TODOs in poly, or your upcoming university FYPs,
YasuMax keeps you on track for a stress-free academic life!
---
## Why YasuMax? 🤔
"Yasu" means peace in Japanese, and BayMax is the gentle giant (*~awww~*) we all adored in Disney's Big Hero 6 (2014).<br>
When school feels overwhelming and tasks seem endless, we yearn for a reliable companion to assist us.<br>
While BayMax isn't real...YasuMax is! Try it below and call it your dedicated study buddy today!
---
## User Guide Sections
- Installation Guide
- Quick Demo
- Features
  - Task Insertion
    - Adding a todo task: `todo [description]`
    - Adding a deadline task: `deadline [description] /by [datetime]`
    - Adding an event task: `event [description] /from [datetime] /to [datetime]`
  - Task Deletion
      - Deleting a task by insertion index: `delete [index]`
      - Clearing all tasks: `clear`
  - Task Annotation
      - Marking a task as complete: `mark [index]`
      - Unmarking a task as incomplete: `unmark [index]`
  - Task Search
    - Finding a task by insertion index: `find_index [index]`
    - Finding a task by character(s): `find [keyword]`
    - Listing all tasks: `list`
  - Miscellaneous Features
      - Viewing general help: `help`
      - Viewing specific help: `help [valid_command]`
      - Exiting app: `bye`
---
## Installation Guide
1. Ensure you have Java `17` or above installed in your Computer.<br>
**Mac users:** Ensure you have the precise JDK version [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).<br>
You can check your Java version on any OS by running in your terminal: `java -version`
2. Navigate to the [YasuMax GitHub Releases](https://github.com/Mingyuan03/ip/releases) page.
3. Download the latest `YasuMax.jar` file to a local home directory of your choice.
4. Run `YasuMax` via this command in your terminal: `java -jar YasuMax.jar`. This should launch YasuMax in GUI mode ![here](./Start.png).
---
## Quick Demo
1. Type `help` in GUI mode for all available commands alongside their formats. A resizable pop-up window should appear:![here](./help.png)
   1. Take note that this pop-up window must be closed explicitly to ensure you have read through them thoroughly!
   2. `YasuMax` allows you to further customise your `help` by specifying 1 additional command to get its format!<b>
   E.g.: Running `help help` (which is distinct from `help`!) should render:![here](./help-help.png)
2. Try out the following sample commands in sequence:
   ```
   help help
   deadline CS2103T /by 20250224
   list
   mark 1
   event NUS Math Underground Seminar /from 20250224 /to 2025028
   todo send-off papa at Changi Terminal 2
   find_index 2
   find 2
   mark 3
   unmark 1
   list
   delete 3
   clear
   bye
3. Ensure you get the result for Step 2 as depicted in the 6 images below:
   ![sample-1](./sample-1.png)<b>
   ![sample-2](./sample-2.png)<b>
   ![sample-3](./sample-3.png)<b>
   ![sample-4](./sample-4.png)<b>
   ![sample-5](./sample-5.png)<b>
   ![sample-6](./sample-6.png)<b>
4. You are good to go, captain!
---
## Features
### Adding a todo task
- Format: `todo [description]`
- Notes 📝:
  - Description cannot be empty, else `YasuMax` kindly gives a `todo` custom help message akin to `help todo`.
### Adding a deadline task:
- Format: `deadline [description] /by [datetime]`
- Notes 📝:
  - Description and datetime cannot be empty, else `YasuMax` kindly gives a `deadline` custom help message
  akin to `help deadline`.
### Adding an event task:
- Format: `event [description] /from [datetime] /to [datetime]`
- Notes 📝:
  - Description and both datetime cannot be empty, else `YasuMax` kindly gives a `event` custom help message
  akin to `help event`.
  - Both datetime cannot be invalid, else `YasuMax` kindly gives a list of valid ISO8601-like datetime it recognises,
  as seen here: ![here](./invalid-datetime.png)
### Deleting a task by insertion index:
- Format: `delete [index]`
- Notes 📝:
  - Index must be a positive integer and not out of bound. If no tasks are stored, `YasuMax` short-circuits successfully
  independent of index's validity.
### Clearing all tasks:
- Format: `clear`
- Notes 📝:
    - Any text following this command is ignored.
### Marking a task as complete:
- Format: `mark [index]`
- Notes 📝:
  - Index must be a positive integer and not out of bound. If no tasks are stored, `YasuMax` short-circuits successfully
  independent of index's validity. 
### Unmarking a task as incomplete:
- Format: `unmark [index]`
- Notes 📝:
  - Index must be a positive integer and not out of bound. If no tasks are stored, `YasuMax` short-circuits successfully
  independent of index's validity.
### Finding a task by insertion index:
- Format: `find_index [index]`
- Notes 📝:
  - Index must be a positive integer and not out of bound. If no tasks are stored, `YasuMax` short-circuits successfully
  independent of index's validity.
### Finding a task by character(s):
- Format: `find [keyword]`
- Notes 📝:
  - This command is more flexible than a pedantic search, i.e. it finds all matching char sequences no matter if they
  form a separate word in each task. This better mimics app search in real-life.
### Listing all tasks:
- Format: `list`
- Notes 📝:
  - Any text following this command is ignored.
### Viewing general help:
- Format: `help`
- Notes 📝:
  - This command defaults to getting full help message if no text follows, else it requires a single-word valid command.
### Viewing specific help:
- Format: `help [valid_command]`
- Notes 📝:
  - If text following `help` is invalid or not a single word, this command defaults back to getting full help message
  akin to standalone `help` above.
### Exiting app:
- Format: `bye`
- Notes 📝:
  - Special command to terminate bot interaction normally.<b>
  - **BEWARE** force-closure of GUI window via builtin top-right
  ❌ button won't properly save data from past sessions to cache, likely corrupting data.
---