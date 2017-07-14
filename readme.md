**This project implements the query converter written with SQL on NoSQL.**

The solution to this problem is quite simple and concise.
Written sql, application using a parser that adapts the entered strings to the parameters that form the query for nosql, 
using the Aggregation class from the org.springframework.data.mongodb.core library.

**To run the project you need:**
1. installed java 8.
2. Download the project from the repository
3. Go to the root of the project using the terminal and run the command "java -jar client-0.0.1-SNAPSHOT.jar"

Example for input:

SELECT * FROM entity WHERE name = 'lol' GROUP BY name, sex, object ORDER BY name ASC LIMIT 3 OFFSET 0