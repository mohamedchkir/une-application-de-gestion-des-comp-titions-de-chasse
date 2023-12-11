## Aftas Club - Competition Management Application

**Description:**

This application is designed to manage the underwater hunting competitions organized by the Aftas Club. It offers features for both club administration and jury members.

**Functionalities:**

* **Add new competition:** Define details like date, time, location, and participant limit.
* **List competitions:** Browse all existing competitions, with the option to filter by status (ongoing, closed).
* **Member registration:** Register club members for competitions.
* **Submit competition results:** Jury members can submit the catch results for the day's competition.
* **View podium:** See the top 3 participants for the current competition.
* **Member search:** Admin can search for members by ID, name, or last name.
* **Data validation:** Ensure data integrity through strict validation rules.
* **Exception handling:** Robust error handling mechanism for unforeseen situations.
* **Pagination:** Efficiently navigate through large datasets.

**Technologies:**

* **Backend:** Spring Boot
* **Frontend:** Angular
* **Database:** (Specify actual database used)

**Project Setup:**

**1. Clone the repository:**

```bash
git clone https://github.com/mohamedchkir/une-application-de-gestion-des-comp-titions-de-chasse.git
```

**2. Install dependencies:**

```bash
cd une-application-de-gestion-des-comp-titions-de-chasse
cd backend
mvn package
cd ../frontend
npm install
```

**3. Configure database connection:**

- Update database connection details in `backend/src/main/resources/application.properties`.

**4. Run the application:**

```bash
cd backend
mvn spring-boot:run
cd ../frontend
ng serve -o
```

**5. Access the application:**

Open http://localhost:4200 in your web browser.
