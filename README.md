# AAI-EAP

The project is using SpringBoot 3.1.9, Java 17 for the backend and React for the frontend.

It was implemented functions including CRUD, pagination, import from excel, and search.

## To Run

make sure your default java is java 17 and maven is installed.<br>
check by:
```bash
mvn -version
```
start backend:
```bash
cd ./Extension
mvn spring-boot:run
```
the backend server will run in port 8080.

start frontend:
```bash
cd ./react-aai-eap
npm install
npm start
```
the frontend will start in port 3000.

You can access by localhost:3000