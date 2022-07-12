# Java Challenge - by Otavio Ferreira
>*** Thanks so much for this opportunity. I really appreciated it a lot.

## Overview
I mixed a little of everything in the application, just to show my knowledge.

I tried too to simulate a clean-arch. I used a lot of Java 8 stuff, like the File API, and normal stuff,
like default methods, method references...

At the start of the application, I tried to simulate a dependency injection, to keep only one instance of each thing running.

I created some exceptions just to show some different ways to observe then in action, and this is more clean in test assertions.

I putted a file inside resources to run along with the application, in case of the user doesn't pass a file and simply run the jar.

I created a lot of tests, so that the coverage in the Jacoco be high, in addition to javadoc, as a plus.

###
### How to use
At first, you need to clone this repository into o folder you want to.
````
git clone https://git.jobsity.com/otavio.cferreira/JavaChallenge.git
````

After that, inside the project folder, you need to run the next command:
````
mvn clean package
````

And after that, you will run that command:
````
java -jar .\target\java-challenge-1.0.0.jar
````

If you want to use your own __play file__, its path need to be passed like a parameter to the same above command:
````
java -jar .\target\java-challenge-1.0.0.jar "c:/Program Files/myfiles/myown_playfile.txt"
````

###
### Example
For a file like that:
_(real_play.txt)_
````
Jeff	10
John	3
John	7
Carl	10
Jeff	7
Jeff	3
John	6
John	3
Carl	10
Jeff	9
Jeff	0
John	10
Carl	10
Jeff	10
John	8
John	1
Carl	7
Carl	2
Jeff	0
Jeff	8
John	10
Carl	8
Carl	2
Jeff	8
Jeff	2
John	10
Carl	F
Carl	9
Jeff	F
Jeff	6
John	9
John	0
Carl	10
Jeff	10
John	7
John	3
Carl	7
Carl	3
Jeff	10
John	4
John	4
Carl	9
Carl	0
Jeff	10
Jeff	8
Jeff	1
John	0
John	9
Carl	7
Carl	3
````

We'll have an output scoreboard like that:
````
Frame           1               2               3               4               5               6               7               8               9               10
Carl
Pinfalls                X               X               X       7       2       8       /       F       9               X       7       /       9       -       7       /
Score           30              57              76              85              95              104             124             143             152             162
Jeff
Pinfalls                X       7       /       9       -               X       -       8       8       /       F       6               X               X       X       8       1
Score           20              39              48              66              74              84              90              120             148             167
John
Pinfalls        3       /       6       3               X       8       1               X               X       9       -       7       /       4       4       -       9
Score           16              25              44              53              82              101             110             124             132             141
````

###
### Features Used
| Feature  | For what                                    |
|----------|---------------------------------------------|
| Lombok   | To avoid boilerplate code.                  |
| JUnit5   | To create the test implementations.         |
| Assertj  | Better assert readability                   |
| Mockito  | To create all needed contexts for the tests |
| SureFire | To use the standard patterns for the JUnit5 |
| Jacoco   | To measure the test coverage                |