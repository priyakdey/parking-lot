# **Automated Parking**

***About***
----------
_______
The application automates the process of registering, assigning a spot and issuing a ticket to any user while entering.
It also automates the process of charging the user when leaving the parking lot. 

 
***Functionality***
------
_____
- Automatic parking space allotment to an incoming car
- Automated bill generation for the time the car is parked, while exiting the lot 

***Use Cases and Assumptions***
------
____
- The Parking Lot is a Single-Level Parking Lot
- There is a single entrance/exit to the lot (Design implementation provides a way to mock other scenarios too)
- Currently, all the Parking Spots are General in Type and there is no Special Parking for different Types of vehicles
(Designed in a way to implement special Parking Spaces easily)
- Once Parking Lot is full, the next vehicles are turned back and there is no waiting queue
- Currently, the input is a file(samples under test/resources), and the output is on standard output console
- The input file supported is .txt, but designed in a way to support various other extensions in future  


***Getting Started***
-----
____

**Minimum System Requirements**
```
1. Java 13
2. Maven 3.8 (Maven wrapper included in case locally not available)
```

***If Maven is not locally installed, use the mvnw script to build the application:***

TODO: To be deleted after review

./mvnw package : package the file on linux systems

./mvnw test : to run the test suite

./mvwn verify : to run the code coverage verification

java -jar target/parking-lot.jar src/test/resources/input.txt to run the project with sample input

_____

**Sample Input file and corresponding output file is attached along with source code under src/test/resources.**








