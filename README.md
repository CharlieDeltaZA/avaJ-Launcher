# avaJ-Launcher

### Score 114/100
#### Mandatory
100/100

#### Bonus
14/25

#### Project Overview:
1st Java project @ WeThinkCode_

Summary: Implement a minimal aircraft simulation program based on a given UML class diagram.

### Installing & Running:
Ensure you have the latest JRE & JDK (14 at time of development).

Clone project, `cd src`. 

2 shell scripts are provided.

* Run `sh compile.sh` : This will compile the .java files to .class files.
* Run the simulation : `java com.avajlauncher.simulator.Simulator scenario.txt`

Output is generated to the `simulation.txt` file.

* Running `sh clean.sh` will remove any Java class files, as well as `sources.txt` and `simulation.txt`

-----

* The original compilation commands from the subject pdf  (`find -name *.java > sources.txt` & `javac -sourcepath @sources.txt`) cause a missing symbol error. To get around this, `-sourcepath` was removed from the compiler command.
