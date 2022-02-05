# RUKY GYMASTIC

The objective of this application is to represent the functioning of a gym. It has different options of gym members, 

___

## MEMBERS OF THE TEAM

| Name | Corporative email | Github user |
| - | - | - |
| Carlos Fernández López | c.fernandezl.2019@alumnos.urjc.es | ruky00 |
| Néstor Granado Pérez | n.granado.2019@alumnos.urjc.es | nestorgranado |
| Alessandro Nuzzi Herrero | a.nuzzi.2019@alumnos.urjc.es | AlessandroNuzziURJC |
| Andrea Nuzzi Herrero | a.nuzzi.2018@alumnos.urjc.es | AndyNuzzi |
| Fernando Sarabia Rodríguez | f.sarabia.2019@alumnos.urjc.es | f.sarabia |

___

## CORPORATIVE TOOLS

In order to plan de distribution of the amount of work, the team is going to use trello, https://trello.com/b/u60sMfLS/g3-daw-2021-22 and in case meetings are necessary the team is going to use Discord

___

## PHASE 0: APLICATION FUNCTIONALITIES

### ENTITIES

There are going to be four different entities:
* **USER** —  this entity is formed by different types of users that take part of the system
*  	Gym Member
*  	Gym Instructor
*  	Administrator 
*  **EXERCISE** — this entity is formed by different activities a gym member can perform. The member can be guided by an instructor or not.
*  **GROUP ACTIVITIES** — this entity is formed by different activities which are directed by instructors. 
*   **EXERCISE TABLES** — this entity is formed by different types of exercises. These tables are for the members.
  
### USERS

Among the user entity there are going to be different types of users:
* **Members** — are the users that have access to the different services provided by the gym
* **Instructors** — These users can book rooms to perform group activities and can assign exercise tables to the members
* **Administrator** — their main activity is to create and maintain different instances of the gym
* **Unregistered users** — these users can only access to information about the different prices and services provided by the gym

### USER PERMISSIONS

Members and Instructors, own the information they have in their profile. They can edit, creating, modifying and deleting that information:
* Profile avatar
* Email
* Password
* Change of password
* Change of password
* Percentage of corporal fat
* Height
*  Breadth
*  Gender
*  Bank account associated

Permissions associated to the administrator are: 
* Register new rooms, modify the existing ones and disable rooms that cannot be used
* Register new instructors, edit the instructors that form part of the gym and delete instructors
* See the general progress of the gym members

### IMAGES

Members and instructors will have representative avatars. They can modify it.
Administrators can edit images associated to the different entities, activities,  exercise tables…

### GRAPHICS

The administrator is the one who has de capability of visualizing the different graphics. They are mainly related to member participation graphics:
* Activities (it will be shown as bar charts)
* Exercise Tables (it will be shown as bar charts)

### COMPLEMENTARY TECHNOLOGY

Introduce the localisation of the gym using google maps
Generate different pdfs with the exercise tables

### ADVANCED CONSULTING ALGORITHM

At the end of a season the members can see the individualized progress that has improved along the season.

___
  
