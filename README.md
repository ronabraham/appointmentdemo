# appointmentdemo
____________________
A Interview Assignment

Appointments Demo is a CRUD API demo developed using Spring Boot Framework.
Basic Error Checking and Validations are present for scheduling of appointments.
Authentication has not been implemented due to shortage of time.
Unit Test Cases have not been requested .
Note - Duration of appointment requested in the assignment can be achieved by calculating the difference between starttime and endtime from the database record. There is no endpoint for the same.
HOW TO INSTALL / EXECUTE
________________________
Note - Please ensure you have Java 8 JRE / SKD ($java -version) and maven installed.
JAVA_HOME is the environment variable that must be set to run maven commands .
Clone the Source Url and "cd" to appointmentdemo folder
Based on dependencies added in pom.xml (jpa , h2, spring web) please execute a 
$maven update from the terminal
$mvn spring-boot:run (or open the source in Spring / Eclipse and run the maven command from the menu

The message appears in the terminal which should tell you the port number and wether the app has started.
2022-08-04 16:31:56.679  INFO 7566 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-08-04 16:31:56.690  INFO 7566 --- [           main] c.e.a.AppointmentsApplication            : Started AppointmentsApplication in 4.114 seconds (JVM running for 4.619)

Once booted, based on entries in application.properties file in src/main/resources the h2 console will be found at http://localhost:<port>/h2-console
Go to your favourite browser / rest client / postman .

Following api methods have been implemented as requested in the S30 Java Assignment

1.Create Appointment 
2.List Appointments
3.List Appointment by Date Range
4.Update Appointment
5.Delete Appointment
--------------------------------------------------------------------
1. Create Appointment
URL to test -> http://localhost:8080/create
Http Header FOR ALL api endpoints - Content-Type: application/json
Request Body format
{    
    "title": "Visit to the Baker",
    "date": "2022-02-09",
    "startTime": "08:58:00",
    "endTime": "08:59:00"
}
Note - Date is in YYYY-MM-DD format and Time is in HH:MM:SS format. Other formats will give Bad request error on submission

2. List Appointment 
URL to test -> http://localhost:8080/list

3. List Appointment by Date Range
Note - Pass dates as request parameters
URL to test (example) -> http://localhost:8080/listByDateRange?startDate=2022-02-08&endDate=2022-02-10

4. Update Appointment
URL to test -> http://localhost:8080/update
Request Body Format
{
    "id": 1,
    "title": "Visit to the Baker",
    "date": "2022-02-09",
    "startTime": "08:58:00",
    "endTime": "08:59:00"
}
Note - Successful Updation results in a success message, else Error Message on failed Updation
5. Delete Appointment
URL to test -> http://localhost:8080/delete/<id>
Note - Successful Deletion results in a success message, else Error Message on failed Deletion
