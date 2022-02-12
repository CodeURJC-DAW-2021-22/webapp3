# RUKY GYMASTIC

The objective of this application is to represent a gym. It will allow users to store important information related to activities carried out in the gym.

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

In order to plan the distribution of work, the team is going to use trello, (https://trello.com/b/u60sMfLS/g3-daw-2021-22). If online meetings are necessary, the team will use Discord.

___

## PHASE 0: APLICATION FUNCTIONALITIES

### ENTITIES

There are going to be four different entities:
* **USER** —  This entity represents the application's users. There are different types of users that take part in the system:
  * Gym Member
  * Gym Instructor
  * Administrator 
*  **EXERCISE** — This entity represents a physical activity a gym member can perform. The member can be guided by an instructor or not.
*  **GROUP ACTIVITIES** — This entity represents a sport which is directed by instructors. Group activities can be created, modified or deleted by the administrator.
*   **EXERCISE TABLES** — This entity is formed by different types of exercises. The exercise tables are for gym members. Exercise tables can be created, modified or deleted by gym instructors.
  
### USERS

Among the user entity there are going to be different types of users:
* **Member** — is the type of user that have access to the different services provided by the gym.
* **Instructor** — this type of user can assign exercise tables to members and book rooms to perform group activities.
* **Administrator** — its main activity is to create and maintain updated the gym information.
* **Unregistered user** — this type of user can only access to information about the different prices and services provided by the gym.

### USER PERMISSIONS

Members and Instructors own the information they have in their profile. They can edit, create, modify and delete the information:
* Profile avatar
* Email
* Password
* Change of password
* Percentage of corporal fat
* Height
* Breadth
* Gender
* Bank account associated

Permissions associated to the administrator are: 
* Register new group activities, modify the existing ones and delete activities.
* Register new instructors, edit the instructors that form part of the gym and delete instructors.
* See the general progress of the gym members.

### IMAGES

Members and instructors will have representative avatars. They can modify it.
Administrators can edit images associated to the different entities: activities, exercise tables, general information of the gym...

### GRAPHICS

The administrator is the one who has de capability of visualizing the different graphics. They are mainly related to member participation graphics:
* Activities (it will be shown as bar charts)
* Exercise Tables (it will be shown as bar charts)

### COMPLEMENTARY TECHNOLOGY

Introduce the localization of the gym using Google Maps.
Generate different pdfs with the exercise tables.

### ADVANCED CONSULTING ALGORITHM

At the end of a season, members will be able to see their individual progress.

___
  
