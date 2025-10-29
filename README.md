# -Campus-Lost-Found-Portal
The Campus Lost &amp; Found Portal is a web app that helps students and staff report, search, and recover lost or found items on campus. Users can post details, browse listings, and contact each other to return belongings easily and efficiently.
# How to Run the Campus Lost & Found Portal
1. Clone the Repository
   bash
   git clone https://github.com/<your-username>/<your-repository-name>.git
   cd <your-repository-name>
   
2. Set Up MySQL Database
   Install MySQL and MySQL Workbench (if not already installed)

   Create a database named campus_lost_found

   Update MySQL connection details in src/main/resources/application.properties (Spring Boot backend)
   
4. Run the Backend (Spring Boot)
   Open a terminal and navigate to the backend directory:

   bash
   cd backend/lostandfound
   Run the backend server:

   bash
   mvn spring-boot:run
   The backend will start at http://localhost:8080
   
5. Run the Frontend (React)
   Open a new terminal and navigate to the frontend directory:

   bash
   cd frontend
   Install dependencies:

   bash
   npm install
   Start the frontend server:

   bash
   npm start
   The frontend will open at http://localhost:3000

6. Access the Application
   Open your browser and go to http://localhost:3000

   Register, login, and use the portal features

7. Stopping Servers
   To stop backend or frontend servers, use Ctrl+C in their respective terminal windows

Note:
    Ensure MySQL server is running before starting the backend
    For troubleshooting database or port issues, check your application logs and update application.properties as needed
