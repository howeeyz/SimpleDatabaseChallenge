# SimpleDatabaseChallenge Guide

## Guidelines
Guidelines can be found here: https://www.thumbtack.com/challenges/simple-database

## How to Run
Create a classes folder in the master directory where the repository is cloned.
Navigate to the bin directory. Ensure the two .bat files have the following line changed to the appropriate JAVA_HOME directory on the machine:
set JAVA_HOME="INSERT YOUR JAVA_HOME PATH HERE"

Run CompileMyDatabase.bat to compile the project. Then Run RunMyDatabase.bat to run the project.

## The Design

### HashMap
Hashmaps were used in order to align with the performance requirements in maintaining an average case run-time of O(log N).

2 hashmaps were used: 
- 'database'
- 'dbCount'

The hashmap 'database' is used to store all the data entered by the user.

The hashmap 'dbCount' is used to store the counts of the values entered by the user. This hashmap is used for the NUMEQUALTO function. Anytime a SET or UNSET function is called, 'dbCount' is affected.

### Stack
Stacks were used to maintain transaction blocks and perform the rollbacks when necessary. They are useful for the ROLLBACK function because of their FILO property and rollback should happen in the reverse order of the transaction block.

2 stacks were used:
- 'history'
- 'historyList'

The stack 'history' stores the inverse transaction if a SET or UNSET is performed. The transactions saved in this stack are only for a single transaction block.

The stack 'historyList' stores the transaction blocks. When a rollback is called, the next most recent transaction block is popped so more transactions can be added if needed. When a BEGIN is called, the current history block is pushed onto the 'historyList' stack while a new 'history' stack is created. When a COMMIT is called, the historyList is cleared.


