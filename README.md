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

## Phase 1: Layout of pages

In order to help clarifying the references to the amount of screens that conform the web application, the team has used some codes:
* USRADM_XX: Screens that are dedicated for the administrator.
* USRMEM_XX: Screens that are dedicated for the members of the gym.
* USRMON_XX: Screens that are dedicated for the monitors of the gym.
* USRNR_XX: Screens that are dedicated for the unregistered users.

### Screens

The application consists of the following screens:

* USRADM_01Statistics: This page shows the different statistics the administrator is able to see.
  ![](phase3_Doc_Imgs/admin/Ruky-Gymastic-ADMIN-statistics.png)
* USRADM_02ActivitiesList: This screen shows the different grupal activities that are registered in the system.
  ![](phase3_Doc_Imgs/admin/Ruky-Gymastic-ADMIN-activities.png)
* USRADM_03AddActivity: This screen is a form. On it, you can add the information needed for creating a new activity.
  ![](phase3_Doc_Imgs/admin/Ruky-Gymastic-ADMIN-newActivity.png)
* USRADM_04SeeActivityInfo: This screen shows the information of an activity.
  ![](phase3_Doc_Imgs/admin/Ruky-Gymastic-ADMIN-viewActivityInfo.png)
* USRADM_05EditActivity: This screen allows the administrator to change the information of an specific activities.
  ![](phase3_Doc_Imgs/admin/Ruky-Gymastic-ADMIN-editActivity.png)
* USRADM_06EditMembers: The administrator can edit the amount of members the gym has. The administrator can add a new member or delete an existing member.
  ![](phase3_Doc_Imgs/admin/Ruky-Gymastic-ADMIN-members.png)
* USRADM_07MonitorsList: This screen contains a list of monitors that are working for the gym.
  ![](phase3_Doc_Imgs/admin/Ruky-Gymastic-ADMIN-monitor.png)
* USRADM_08Add_MonitorsList: This screen shows how to add a new monitor to the gym.
  ![](phase3_Doc_Imgs/admin/Ruky-Gymastic-ADMIN-addMonitor.png)
* USRADM_09SeeMonitorInfo: This screen shows the information about an specific monitor.
  ![](phase3_Doc_Imgs/admin/Ruky-Gymastic-ADMIN-monitorInfo.png)
* USRADM_10EditMonitor: The administrator can edit the information of instructors the gym has.
  ![](phase3_Doc_Imgs/admin/Ruky-Gymastic-ADMIN-editMonitor.png)
* USRMEM_01ExerciseTable: The user can see the different exercise tables associated by the instructor.
  ![](phase3_Doc_Imgs/member/Ruky-Gymastic-MEMBER-exerciseTables.png)
* USRMEM_02Profile: The member can see the information used in the register.
  ![](phase3_Doc_Imgs/member/Ruky-Gymastic-MEMBER-profile.png)
* USRMEM_03EditProfile: Members can edit their private information.
  ![](/Users/andreanuzziherrero/Desktop/phase3_Doc_Imgs/member/Ruky-Gymastic-MEMBER-editProfile.png)
* USRMEM_04Statistics: User can see their progress along the season. This progress is going to be shown as different charts.
  ![](phase3_Doc_Imgs/member/Ruky-Gymastic-MEMBER-statistics.png)
* USRMEM_05Activities: The member can see the activities they are subscribed.
  ![](phase3_Doc_Imgs/member/Ruky-Gymastic-MEMBER-activities.png)
* USRMON_01Schedule: This page shows monitor schedule.
  ![](phase3_Doc_Imgs/monitor/Ruky-Gymastic-MONITOR-schedule.png)
* USRMON_02Profile: The monitor can see the information used in the register.
  ![](phase3_Doc_Imgs/monitor/Ruky-Gymastic-MONITOR-profile.png)
* USRMON_03EditProfile: Monitors can edit their private information.
  ![](phase3_Doc_Imgs/monitor/Ruky-Gymastic-MONITOR-editProfile.png)
* USRMON_04ExerciseTable: This page shows the activities the monitor will teach.
  ![](phase3_Doc_Imgs/monitor/Ruky-Gymastic-MONITOR-exerciseTables.png)
* USRMON_05AddExerciseTable: This screen allows the monitor to add a new exercise table to the system.
  ![](phase3_Doc_Imgs/monitor/Ruky-Gymastic-MONITOR-addExerciseTables.png)
* USRMON_06GrupalActivities: This page show the exercises tables the monitor uploads. Exercices tables can be uploaded and deleted.
  ![](phase3_Doc_Imgs/monitor/Ruky-Gymastic-MONITOR-activities.png)
* USRNR_01GymMainPage: Main page that all users will see upon entering and briefly explains the gym's services.
  ![](phase3_Doc_Imgs/nonRegUsers/Ruky-Gymastic-Main.png)
* USRNR_02ContactUsGym: Page with contact information of the gym as well as a map with the location of the gym.
  ![](phase3_Doc_Imgs/nonRegUsers/Ruky-Gymastic-Contact.png)
* USRNR_03PricesGym: This page shows the three possible rates you can choose to join the gym.
  ![](phase3_Doc_Imgs/nonRegUsers/Ruky-Gymastic-rate.png)
* USRNR_04activitiesGym: This page has two parts, one contains the individual activities offered by the gym and the other the group activities.
  ![](phase3_Doc_Imgs/nonRegUsers/Ruky-Gymastic-Activities.png)
* USRNR_05SignInGym: Gym registration page.
  ![](phase3_Doc_Imgs/nonRegUsers/Ruky-Gymastic-sign_in.png)
* USRNR_06LogInGym: Page used by regsitrated users to enter to their account in the gym.
  ![](phase3_Doc_Imgs/nonRegUsers/Ruky-Gymastic-login.png)
* USRNR_07LogInGym: Page used to show if mistakes were made when trying to log in to the app.
  ![](phase3_Doc_Imgs/nonRegUsers/Ruky-Gymastic-login-ERROR.png)


### Navigation diagram

The following diagram shows how users can navigate through the different pages of the application according to their role.
  ![](phase2_Doc_Imgs/NavigationDiagram.png)

___


## PHASE 2: WEB WITH HTML GENERATED IN SERVER AND AJAX

### EXECUTION INSTRUCTIONS

#### VERSIONS

The different technologies used among this phase are the following:
  * Java: 17.0.2
  * Maven: 4.0.0 
  * Spring Boot: 2.6.3
  * Itextpdf: 5.5.13.3
  * Postgres: 14.2
  * PgAdmin: 4v6.6

#### INSTALL POSTGRES

In order to install the data base, the following steps are needed:
  * The first thing needed is install docker desktop: https://www.docker.com/get-started
  * Open shell and introduce: 
  		* windows/mac: docker run --rm -e POSTGRES_PASSWORD=password -e POSTGRES_DB=posts -p 5432:5432 -d postgres:14.2
  		* ubuntu 20.4: sudo apt install postgresql postgresql-contrib

#### INSTALL PGAdMIN

In order to use PGAdmin, it needs to be installed: https://www.pgadmin.org/download/

#### CONFIGURE POSTGRES WITH PGADMIN

By the time PGAdmin is downloaded, in the directory tree press "Servers" with the right button. Then choose "register" option and then "Server" option.

Right after that, among the diferent pages of the configuration of the server, we have changed:
  * Name -- Local
  * Host -- localhost
  * Password -- password

#### PROYECT DOWNLOAD

In order to get our code, you have to enter to github https://github.com/CodeURJC-DAW-2021-22/webapp3, press code button and download zip.

#### ERROR

There have been some problems with Maven dependencies in pom.xml file. The problems are related with the complementary technology of creating pdfs, you have to enter to pom.xml file and remove the dependency *itextpdf*. Right after that you have to reload the file, and put the dependency back. Then you will have to reload the pom again.

#### PROYECT EXECUTION

The IDE used to program this application is IntelliJ. In order to run the application you have to presh the button run located on the IDE and make sure you are located in *backend* directory. 
Then you have to open a browser, all the members of the team have used Google Chrome, so we recommend that browser. 
To see the application you have to type on the search bar *https://localhost:8443/USR_mainpage*. That is the main page of the application.

### DATA BASE ENTITIES DIAGRAM

![](phase2_Doc_Imgs/ERD_diagram.png)

### CLASSES AND TEMPLATES DIAGRAM

![](phase2_Doc_Imgs/diagram.png)

### MEMBERS PARTICIPATION

#### Nestor Granado Perez

##### DESCRIPTION OF TASKS

I have worked on the functionalities of non-registered users, I have carried out the implementation and management with the rest of the parts of spring security and ultimately I have helped to correct errors

##### COMMITS DONE

| Name | Description |
| - | - |
| 1 | Pages security, add log in and sign in actions and registered mode (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/e27f6fda844e03f079614b0769ee2cf635d16c0b) |
| 2 | Client security and update files (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/f6f1026f28356ffe65b9fb8bc3d66dca1508e091) |
| 3 | Web security version 1: Security structure,login error page and private and public pages control(https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/798ed523c55ef53cb34f1cfdf733196013a90363) |
| 4 | Test DDBB link, add image files to main page and activity page, update files(https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/fc790210cc2484824e8ddc8261e4655ca2ae4e34) |
| 5 | update error files (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/8af07a2fcc8617150e02f873512074f6b10518fd) |

##### FILES PARTICIPATED

| File name | Link to github |
| - | - |
| 1 | NonRegController.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/java/webapp3/webapp3/controller/NonRegController.java) |
| 2 | SecurityConfiguration.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/java/webapp3/webapp3/security/SecurityConfiguration.java) |
| 3 | USR_01mainPage.html(https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/resources/templates/USR_01mainPage.html) |
| 4 | USR_03schedule.html(https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/resources/templates/USR_03schedule.html) |
| 5 | USR_07sign_in.html(https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/resources/templates/USR_07sign_in.html) |

#### Fernando Sarabia Rodriguez

##### DESCRIPTION OF TASKS

I have worked in the monitors funcionality, introducing Mustache to the pages associated to the monitors of the gym and introducing this functionality to the monitor controller. I have also created headers and footers related to monitors. I have helped migrating H2 to PostgreSQL. And I have also helped fixing several errors.

##### COMMITS DONE

| Name | Description |
| - | - |
| 1 | Adding Exercise table page and see exercise table info page. (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/486d10a498b73277bc5890eda37a7b6459b8101c) |
| 2 | Adding Grupal activities page and see activities info page. (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/3a3d9fc2a3e66e63fdd2baf031599584b3902a17) |
| 3 | Adding Profile and edit profile for monitors.(https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/ff289067d8fc827566c45adc304afdd8e54aca7f) |
| 4 | Adding Grupal activities schedule for monitors (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/51c817c70bcae4fedcac91db143c1215717b96ff) |
| 5 | Adding header, footer and monitor controller(https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/54855ab48f370926dcf42b04f158cee6701cf9f9) |

##### FILES PARTICIPATED

| File name | Link to github |
| - | - |
| 1 | MonitorController.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/java/webapp3/webapp3/controller/MonitorController.java) |
| 2 | USRMON_01Schedule.html (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/resources/templates/USRMON_01Schedule.html) |
| 3 | USRMON_04GrupalActivities.html (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/resources/templates/USRMON_04GrupalActivities.html) |
| 4 | USRMON_02Pofile.html (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/resources/templates/USRMON_02Pofile.html) |
| 5 | USRMON_05EditProfile.html (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/resources/templates/USRMON_05EditProfile.html) |

#### Alessandro Nuzzi Herrero

##### DESCRIPTION OF TASKS

My part was to create administrator functionality, adapt the DataBase to the system requirements, migrate from H2 database to PostgreSQL, fix monitors' pages, fix members' profile and edit profile, develop statistics for the administrator and for members (advanced consulting algorithm) and add images and some data to data base.

##### COMMITS DONE
| Name | Description |
| - | - |
| 1 | AdministratorController class' development (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/8c8c595c2fc519c0c7c44bd3a9fd5b6b5b69f3dc) |
| 2 | Adapting html templates to data model (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/e09edbb1043c94808fec3c8b0705bfef91e6b949) |
| 3 | Add some data to DataBase (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/c30d9967c7c3a7857a2ca5f09042d3586fa1e8b9) |
| 4 | Statistics added (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/ca343586c22a9f45b7a3f91356d403f555225d96) |
| 5 | Advanced algorithm (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/13702eae010f34628c9239310c94e09f0d220698) |

##### FILES PARTICIPATED
| File name | Link |
| - | - |
| 1 | AdminController.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/java/webapp3/webapp3/controller/AdminController.java) |
| 2 | USRADM_03MonitorsList.html (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/resources/templates/USRADM_03MonitorsList.html) |
| 3 | DataSampleService.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/java/webapp3/webapp3/service/DataSampleService.java) |
| 4 | UserAdminStatistics.html (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/resources/templates/USRADM_01Statistics.html) |
| 5 | UserTableService.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/java/webapp3/webapp3/service/UserExerciseTableService.java) |
#### Andrea Nuzzi Herrero

##### DESCRIPTION OF TASKS

I have worked in introducing Moustache to the pages associated to the members of the gym. I have also worked in the respective controllers and creating headers and footers related to members, administrators and monitors functionality. I have created the complementary technology that consists of downloading pdfs with the exercise tables. And I have also created ajax associated to the pages of the exercise tables and clients in administrator pages.

##### COMMITS DONE

| Name | Description |
| - | - |
| 1 | Adding pdfs to exercise table page with postgres data base (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/0e66a4d9631374fac32cae19e3c6f43098a4f228) |
| 2 | Adding AJAX to exercise tables members (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/3eb2727900c86744ac75cc6a38fa740e7fe9cf8a) |
| 3 |Adding AJAX to administrator client pages(https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/c671a4f0e66077ed0187d4b4ef6c0cd7f9d1d0c5) |
| 4 | Refactoring header member (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/d97e86509fdd9575b2bae3ac8264297841647ba8) |
| 5 | Adding memberController (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/76a633153595c15dcbe62c164bc8c69e9d40bfbd) |

##### FILES PARTICIPATED

| File name | Link to github |
| - | - |
| 1 | AdminController.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/java/webapp3/webapp3/controller/AdminController.java) |
| 2 | MemberController.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/java/webapp3/webapp3/controller/MemberController.java) |
| 3 | USRMEM_header.html(https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/resources/templates/USRMEM_header.html) |
| 4 | USRADM_12Clients.html(https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/resources/templates/USRADM_12Clients.html) |
| 5 | ExerciseTableService.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/A-N-S-merge/backend/src/main/java/webapp3/webapp3/service/ExerciseTableService.java) |

#### Carlos Fernandez Lopez

##### DESCRIPTION OF TASKS

I have worked on data base creation and implementantion. I  have carried out the insertion of data too, such as exercises, exercise tables and grupal activities. Furthermore I did the HTML for adding and deleting activities.

##### COMMITS DONE

| Name | Description |
| - | - |
| 1 | Repositories, DataBaseInitializer and services (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/5f559634fd1f76b37d2fe2a876f8f528d3946092) |
| 2 | ServicesII (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/edde6e31b502afbb6ed6d55ea082122f340a509c) |
| 3 | Relations(https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/02c1b2df6bf877ff982bc46515fd35d8cb13d1c5) |
| 4 |HTML for Adding and Deleting Activities.(https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/7b5b517d79b5486ef9c34cb532289566cd45c828) |
| 5 | ExerciseTable Entity (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/0ed75404963045b82f2f4a8236e1f501ab66138f) |


##### FILES PARTICIPATED

| File name | Link to github |
| - | - |
| 1 | DataBaseInitializer.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/Carlos/backend/target/classes/templates/src/main/java/services/DataBaseInitializer.java) |
| 2 |ExcerciseService.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/Carlos/backend/target/classes/templates/src/main/java/services/ExcerciseService.java) |
| 3 | ExcerciseTableRepository.java(https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/Carlos/backend/target/classes/templates/src/main/java/repository/ExcerciseTableRepository.java) |
| 4 | ExerciseTable.java(https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/Carlos/backend/target/classes/templates/src/main/java/model/ExerciseTable.java) |
| 5 | GroupActivitiy.java(https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/Carlos/backend/target/classes/templates/src/main/java/services/GroupActivitiy.java) |

___


## PHASE 3: REST API INCORPORATION AND DOCKER DEPLOYMENT

### EXECUTION INSTRUCTIONS

#### EXECUTION INSTRUCTIONS TO DOCKERIZE THE APP  

Firstly, install Docker in your computer. (You can download this program on: https://www.docker.com/get-started/)
Next, open command console. Change your working directory to the file where docker-compose.yml is. (/docker)
Execute 

```$ docker-compose up```

Once the app is running, open in your browser on https://localhost:8443/

#### DOCKER IMAGE CONSTRUCTION

You need to log in to DockerHub before you execute the create_image.sh script. This script creates the new image and pushes it to DockerHub.

Create an account in DockerHub and execute in your command console:

``` $ docker login ```

Execute 

``` $ ./create_image.sh  <name_of_the_new_image> ```

Note that you must edit the script in order to **add your DockerHub user**. If not, you won't be able to upload the image.


#### DEPLOYMENT IN HEROKU

If you aren't signed up in Heroku, create a new account in https://signup.heroku.com/
Before all, execute in your command console:

``` $ heroku login ```

``` $ heroku container:login ```

Execute these commands

``` $ heroku create <application_name> ```

(Creates a new application on Heroku)

``` $ heroku addons:create heroku-postgresql --app <application_name> ```

(Adds the postgresql database to the application)

``` $ heroku config:set SERVER_SSL_ENABLED=false --app <application_name> ```

``` $ heroku config:set SPRING_JPA_HIBERNATE_DDL-AUTO=update --app <application_name> ```

(These commands stablish some environment variables)

If you haven't uploaded the app image  to DockerHub is time to do it ;).

``` $ docker pull <dockerHub_image_name> ```

(Downloads the image from DockerHub)

``` $ docker tag <dockerHub_image_name> registry.heroku.com/<application_name>/web ```

(Prepares it to be pushed to Heroku)

``` $ docker push registry.heroku.com/<application_name>/web ```

(Pushes it into Heroku private registry)

``` $ heroku container:release web -a <application_name> ```

(Releases the web app)

``` $ heroku logs --tail -a <application_name> ```

(Optional. This one allows you to see the information related to the app on your commands console)


#### HEROKU LINK

The link needed in order to access to the Heroku app ![](https://codeurjc-daw-2021-22-webapp3.herokuapp.com/)
There are different types of users to test the app. These are some examples:

* CLIENT
    * Client name: suuu@gmail.com
    * Client password: password
* MONITOR
    * Monitor name: joselu@gmail.com
    * Monitor password: monitor
* ADMINISTRATOR
    * Administrator name: admin@admin.com
    * Administrator password: admin


### API DOCS
The links to access to API DOCS documentation are:

* .yaml file
  ![](https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/main/api-docs/api-docs.yaml)
* link to .html
  ![](https://rawcdn.githack.com/CodeURJC-DAW-2021-22/webapp3/560155efa464a38baf89c8d12083b8c46e7b6fd3/api-docs/api-docs.html)

### UPDATE DIAGRAM CLASS

![](phase3_Doc_Imgs/UML_phase3.png)

### MEMBERS PARTICIPATION

#### Nestor Granado Perez

##### DESCRIPTION OF TASKS

I have worked on the functionalities of users api, I have carried out the implementation and management with the rest of the parts of api security and ultimately I have helped to correct errors

##### COMMITS DONE

| Name | Description |
| - | - |
|1 | ApiSecurity: fixing errors and adding links security (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/640ab6ff5d19708e3f9cab2df762af9f83a6f09f) |
|2 | ApiRest: UserRestController user log functionality (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/584aa7c369635e4468c088dd89b24e6d7e9da3c2) |
|3 | Open api specification and update files (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/529c191ddd8fac1df4f5a47b9da9aa44db6eb33d) |
|4 | Api docs update (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/6e7ff5f3f2491f193fa6be8479e9284d552d8c15) |
|5 | api-docs documentation (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/4c6d801a20b2cacf4f8b6996cc78edf32483ba8e) |


##### FILES PARTICIPATED

| File name | Link to github |
| - | - |
|1 | Api-docs documentation (https://github.com/CodeURJC-DAW-2021-22/webapp3/tree/API-REST-adjustments/backend/api-docs) |
|2 | UserRestController.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/API-REST-adjustments/backend/src/main/java/webapp3/webapp3/controller/UserRestController.java) |
|3 | RestSecurityConfiguration.java(https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/API-REST-adjustments/backend/src/main/java/webapp3/webapp3/security/RestSecurityConfiguration.java) |
|4 | User.java(https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/API-REST-adjustments/backend/src/main/java/webapp3/webapp3/model/User.java) |
|5 | ExerciseRestController.java(https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/API-REST-adjustments/backend/src/main/java/webapp3/webapp3/controller/ExerciseRestController.java) |

#### Carlos Fernandez Lopez

##### DESCRIPTION OF TASKS

I have worked on the functionalities of the ExcercisesRestControler and I used pair programing to due to several issues with my framework, I have helped out with the implementation and management of the Api security and with the Api-docs, ultimately I have helped to correct errors.

##### COMMITS DONE

| Name | Description |
| - | - |
|1 | Exercise Rest Controller GET (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/2b96d3d136a778faf48c781c146768de5bb99dc5) |
|2 | Api Docs Update (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/6e7ff5f3f2491f193fa6be8479e9284d552d8c15) |
|3 | UpdateFiles(https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/823393de446618876ddfd68c37a3bc7a0f45cda9) |
|4 | Api Docs Update(https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/c19c9ea37efe93cbe4a4a4858716fda571ddbe29) |
|5 | ExerciseTableRestController API DOCS update (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/5bfff6a0d5ec00bd13ea289034ec6ee844d7e04c) |

: (Security structure,login error page and private and public pages control

##### FILES PARTICIPATED

Emphasize that most of the commits made have been on a single file.

| File name | Link to github |
| - | - |
|1 | GroupActivitiesRestController.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/API-REST-adjustments/backend/src/main/java/webapp3/webapp3/controller/GroupActivitiesRestController.java) |
|2 | UserRestController.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/API-REST-adjustments/backend/src/main/java/webapp3/webapp3/controller/UserRestController.java) |
|3 | RestSecurityConfiguration.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/API-REST-adjustments/backend/src/main/java/webapp3/webapp3/security/RestSecurityConfiguration.java) |
|4 | ExerciseRestController.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/API-REST-adjustments/backend/src/main/java/webapp3/webapp3/controller/ExerciseRestController.java) |
|5 | ExerciseTableRestController.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/API-REST-adjustments/backend/src/main/java/webapp3/webapp3/controller/ExerciseTableRestController.java) |

#### Fernando Sarabia Rodriguez

##### DESCRIPTION OF TASKS

I have worked on the functionalities of the UserRestController, I have helped out with the implementation and management of the Api security and with the Api-docs, ultimately I have helped to correct errors.

##### COMMITS DONE

| Name | Description |
| - | - |
|1 | User Rest Controller GET (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/103d11a20a64e2ac586f93c188031128cfc6587d) |
|2 | User Rest Controller POST (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/ad9cbb5cf29136079f3d1ceb41ab39999dd5814e) |
|3 | User Rest Controller PUT(https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/2c924c3238304d15a6054e4552ca3a74628dbbba) |
|4 | User Rest Controller DELETE(https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/bc1ce67aeffb3baddb6106cbb6f62c6e467abe67) |
|5 | GroupActivitiesRestController api docs update (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/44922edb5b1c4e6bef9e38f7d191161cda25c97c) |

: (Security structure,login error page and private and public pages control

##### FILES PARTICIPATED

Emphasize that most of the commits made have been on a single file.

| File name | Link to github |
| - | - |
|1 | GroupActivitiesRestController.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/API-REST-adjustments/backend/src/main/java/webapp3/webapp3/controller/GroupActivitiesRestController.java) |
|2 | UserRestController.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/API-REST-adjustments/backend/src/main/java/webapp3/webapp3/controller/UserRestController.java) |
|3 | RestSecurityConfiguration.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/API-REST-adjustments/backend/src/main/java/webapp3/webapp3/security/RestSecurityConfiguration.java) |
|4 | ExerciseRestController.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/API-REST-adjustments/backend/src/main/java/webapp3/webapp3/controller/ExerciseRestController.java) |
|5 | ExerciseTableRestController.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/API-REST-adjustments/backend/src/main/java/webapp3/webapp3/controller/ExerciseTableRestController.java) |

#### Andrea Nuzzi Herrero

##### DESCRIPTION OF TASKS

I have worked on the functionalities of the ExerciseTableRestController, ExerciseRestController and GroupActivitesRestController. I have also worked on the security of the REST API along with my colleages.

##### COMMITS DONE

| Name | Description |
| - | - |
|1 | Adding ExerciseTable API REST (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/acea2cd25e8e19c75fa5e917a06aea4689f1fad0) |
|2 | Creating group activity API REST with images (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/7f7f9ce9113e695fc5d45c8fe7b01ffc2c1209ff) |
|3 | Adding exercise REST API (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/4d2b525d3b080af9bbda42c1f6150d34375215c0) |
|4 | Adding security for REST API (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/a19182cb8655e4e7ec613f75ff0efff08d93dc58) |
|5 | Adding pagination to administrator (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/187fdc62ab75484990b35ce34e1a088c0660c641) |


##### FILES PARTICIPATED

| File name | Link to github |
| - | - |
|1 | GroupActivitiesRestController.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/7f7f9ce9113e695fc5d45c8fe7b01ffc2c1209ff#diff-86c36d1803bdf861ea6cd642fe8cbe94b671660c8e4dcd7e6299875c4eab876e) |
|2 | UserRestController.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/a19182cb8655e4e7ec613f75ff0efff08d93dc58#diff-be99885dd750b429760f0f577492f2f4fd41d7b77c39bd51ae2432e6dd60d1ed) |
|3 | RestSecurityConfiguration.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/acea2cd25e8e19c75fa5e917a06aea4689f1fad0#diff-5e4dedf5d54c5afd7fe4b0aec89785a442c1aa301424172cf169dd1902becc64) |
|4 | ExerciseRestController.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/4d2b525d3b080af9bbda42c1f6150d34375215c0#diff-3dc06012b964baf2d2819ae726d7c650cf62804faa3a0ad67cdcdccdadfeb749) |
|5 | ExerciseTableRestController.java (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/acea2cd25e8e19c75fa5e917a06aea4689f1fad0#diff-eb45d02acd32b35a705d74dec02a297f2277ce23fa047becf45c870b384ad288) |

#### Alessandro Nuzzi Herrero

##### DESCRIPTION OF TASKS

I have worked on the deployment of the application, development of REST APIs and correcting errors. I have extracted some functionallity to the corresponding services to avoid duplication of code. I have also collaborated in open api generation.

##### COMMITS DONE

| Name | Description |
| - | - |
|1 | Paged exercise table list. (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/0b8233377a90cb028878ce3812db4851af6235b4) |
|2 | Extracting duplicated code (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/4d178da98a0fa852e0ae7b14fa64aab353c26107) |
|3 | API REST Statistics implemented(https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/dbc1f0e22e6a178622a19b8ca62e466e8fd86db1) |
|4 | docker-compose working (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/c7e81cfc0a33888e06019295d61a810c2a89b507) |
|5 | Scripts deployApp created (https://github.com/CodeURJC-DAW-2021-22/webapp3/commit/a5dc2bfe808aaf1844fc199e27d02fc47761a497) |


##### FILES PARTICIPATED

| File name | Link to github |
| - | - |
|1 | createApp.sh (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/main/docker/createApp.sh) |
|2 | create_image.sh (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/main/docker/create_image.sh) |
|3 | deployApp.sh (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/main/docker/deployApp.sh) |
|4 | docker-compose.yml ( https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/main/docker/docker-compose.yml) |
|5 | dockerfile (https://github.com/CodeURJC-DAW-2021-22/webapp3/blob/main/docker/dockerfile) |

___
