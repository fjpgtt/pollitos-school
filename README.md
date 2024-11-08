# pollitos-school
This repo will be used to learn how to create controllers an other type of beans using a school scenario.

Scenario
There will be 2 schools, both of them will be of the same type, one named GerardoInstitute and the other ZetCollege

In each school there will be students, courses and grades.
For the students we need one ID, firstName, LastName and age.
For the courses we need an ID, name and professorName.
For the grades, it will have the score, the student and the course

We would need two have 2 profiles: The default one that will be empty and the populated, 
that will have at least 2 students and 2 courses already created in each school.

For the grade we would implement a limit what is the highest score, by default will be 100 but it can be overriden by a property

We need to create the following endpoints (in each request you should indicate what is the league you want to modify):
* Create student
* Create course
* Create a grade
* Get all the students
* Get all the courses
* Get all the grades from a specific student
* Edit the course information
* Edit the student information
* Delete all the grades of a student
* Delete all the grades of a course

Criteria to evaluate:
* Creation of beans
* Usage of the annotation in the Controller layer
* Usage of Spring and Spring boot annotation
* Implementation of logs
* Naming convention

Extra points:
* Creation of unit test or integration
* Correct usage of interfaces and abstract classes
* Proper instructions in the PR (description, title, steps to test)