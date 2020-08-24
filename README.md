# avaJ-Launcher

### Score TBC/100
#### Mandatory
TBC/100

#### Bonus
TBC/25

#### Project Overview:
1st Java project @ WeThinkCode_
Summary: Implement a minimal aircraft simulation program based on a given UML class diagram.

### Installing & Running:
Ensure you have the latest JRE & JDK (14 at time of development).

Clone project, `cd src`. 

2 shell scripts are provided.

* Run `sh compile.sh` : You may encounter an error thrown by the compiler about a missing symbol. That just seems to be the compiler skipping the first line of the `sources.txt` file.
* Identify the "missing" symbol and compile the file manually (`javac path/to/java/file`), then rerun the compile shell script.
Assuming you didn't get any errors on the second run, you should now be able to run the simulation using one of the 2 supplied scenario files.
* `java com.avajlauncher.simulator.Simulator scenario.txt`

Output is generated to the `simulations.txt` file.

* Running `sh clean.sh` will remove any Java class files, as well as `sources.txt` and `simulations.txt`