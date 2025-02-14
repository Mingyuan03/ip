<<<<<<< HEAD
=======
# SteadyLah project template

This is a project template for a greenfield Java project.
While it's initially named after the Java mascot _Duke_, the owner of this project has renamed it to _SteadyLah!_,
an ingenious twist to fit its primary purpose of helping students schedule their academic commitments on-time
while staying true to the local Singlish vernacular! Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
```plaintext
 ____    __                       __           __                __      __     
/\  _`\ /\ \__                   /\ \         /\ \              /\ \    /\ \    
\ \,\L\_\ \ ,_\    __     __     \_\ \  __  __\ \ \         __  \ \ \___\ \ \   
 \/_\__ \\ \ \/  /'__`\ /'__`\   /'_` \/\ \/\ \\ \ \  __  /'__`\ \ \  _ `\ \ \  
   /\ \L\ \ \ \_/\  __//\ \L\.\_/\ \L\ \ \ \_\ \\ \ \L\ \/\ \L\.\_\ \ \ \ \ \_\ 
   \ `\____\ \__\ \____\ \__/.\_\ \___,_\/`____ \\ \____/\ \__/.\_\\ \_\ \_\/\_\
    \/_____/\/__/\/____/\/__/\/_/\/__,_ /`/___/> \\/___/  \/__/\/_/ \/_/\/_/\/_/
                                            /\___/                              
                                            \/__/             
```


**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.
