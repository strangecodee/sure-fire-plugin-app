# Student Result Checker\n\n<div align="center">\n\n![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=openjdk&logoColor=white)\n![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.14-brightgreen?style=flat-square&logo=spring&logoColor=white)\n![Maven](https://img.shields.io/badge/Maven-3.x-red?style=flat-square&logo=apache-maven&logoColor=white)\n![Tests](https://img.shields.io/badge/Tests-16%20Passing-success?style=flat-square&logo=junit5&logoColor=white)\n\n</div>

A simple Spring Boot app I built to demonstrate how Maven Surefire Plugin works in real projects. Perfect for anyone trying to understand automated testing in Java/DevOps workflows.

---

## <img src="https://cdn.simpleicons.org/java" width="24" height="24" /> What This Does

Basically, it's a student result checking system. You enter marks (0-100), and it tells you if you passed or failed. The passing threshold is **40 marks**.

But the real purpose? Showing how Maven Surefire automatically runs your tests during the build process - no manual intervention needed.

---

## <img src="https://cdn.simpleicons.org/markdown" width="24" height="24" /> Quick Screenshots

### Home Page
![Home Page](screenshots/home-page.png)
*The main result checker interface - clean and straightforward*

### API Documentation
![API Docs](screenshots/api-docs.png)
*Interactive API docs where you can test endpoints live*

### About Surefire
![About Surefire](screenshots/about-surefire.png)
*Educational page explaining how Surefire fits into Maven lifecycle*

### Test Results
![Test Results](screenshots/test-results.png)
*Surefire running 16 tests automatically during `mvn test`*

---

## <img src="https://cdn.simpleicons.org/technology" width="24" height="24" /> Tech Stack

<img src="https://cdn.simpleicons.org/java" width="40" height="40" /> **Java 17**

<img src="https://cdn.simpleicons.org/springboot" width="40" height="40" /> **Spring Boot 3.5.14**

<img src="https://cdn.simpleicons.org/thymeleaf" width="40" height="40" /> **Thymeleaf** (for the web pages)

<img src="https://cdn.simpleicons.org/junit5" width="40" height="40" /> **JUnit 5** (testing framework)

<img src="https://cdn.simpleicons.org/apachemaven" width="40" height="40" /> **Maven Surefire Plugin 3.2.5** (the star of the show)

---

## <img src="https://cdn.simpleicons.org/terminal" width="24" height="24" /> How to Run It

### Prerequisites
- Java 17 or higher
- Maven installed

### Running the App

```bash
# Clone and navigate to the project
cd surefiretestdemo

# Build and run tests (this is where Surefire kicks in)
mvn clean test

# Start the application
mvn spring-boot:run
```

Once it's running, open your browser and go to:
**http://localhost:8080**

---

## <img src="https://cdn.simpleicons.org/googlechrome" width="24" height="24" /> What You'll See

### Web Interface
The home page has a simple form where you can enter student marks. Hit "Check Result" and you'll instantly see if the student passed or failed, along with a detailed breakdown.

There are also quick-test buttons if you just want to click around and see how it works.

### REST API
Prefer working with JSON? There's a REST endpoint at:
```
GET http://localhost:8080/api/result/{marks}
```

Example response:
```json
{
  "marks": 75,
  "result": "Pass",
  "message": "Congratulations! You passed."
}
```

### API Documentation
Hit `/api-docs` to see interactive documentation where you can actually test the API endpoints right from the browser.

---

## <img src="https://cdn.simpleicons.org/jest" width="24" height="24" /> The Testing Part (Where Surefire Shines)

This project comes with **15 comprehensive test cases** that cover:

- Typical passing scenarios (75, 85, 100 marks)
- Typical failing scenarios (20, 0, 35 marks)
- Boundary conditions (exactly 40, 39, 41 marks)
- Edge cases (0 marks, perfect 100)
- Multiple value tests for consistency
- Null-safety checks

When you run `mvn test`, Surefire automatically:
1. Finds all test files matching `*Test.java` or `*Tests.java`
2. Runs every single test method
3. Generates reports in `target/surefire-reports/`
4. Fails the build if any test breaks

You'll see output like:
```
[INFO] Tests run: 16, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

That 16? It's 15 tests from `ResultServiceTest` + 1 Spring context load test.

---

## <img src="https://cdn.simpleicons.org/docker" width="24" height="24" /> Why This Matters for DevOps

In real-world projects, you don't manually run tests before every deployment. That's where Surefire + CI/CD comes in:

- **Every code push** → Tests run automatically
- **Tests fail** → Pipeline stops, team gets notified
- **Tests pass** → Code moves to staging/production
- **Zero manual work** → Consistent, reliable quality gates

This simple app demonstrates exactly that workflow. The tests you see here would run the same way in a massive enterprise pipeline.

---

## <img src="https://cdn.simpleicons.org/visualstudiocode" width="24" height="24" /> Project Structure

```
surefiretestdemo/
├── src/main/java/com/demo/surefiretest/
│   ├── SurefiretestdemoApplication.java    # Main class
│   ├── controller/
│   │   └── ResultController.java           # Handles web + API
│   └── service/
│       └── ResultService.java              # Business logic
│
├── src/main/resources/
│   ├── application.properties              # Config
│   └── templates/                          # HTML pages
│       ├── index.html                      # Home
│       ├── api-docs.html                   # API docs
│       └── about.html                      # About Surefire
│
├── src/test/java/com/demo/surefiretest/
│   ├── SurefiretestdemoApplicationTests.java
│   └── service/
│       └── ResultServiceTest.java          # 15 tests here
│
└── pom.xml                                 # Maven config
```

---

## <img src="https://cdn.simpleicons.org/postman" width="24" height="24" /> Key Endpoints

| URL | What It Does |
|-----|-------------|
| `/` | Main result checker page |
| `/check?marks=75` | Check result via web |
| `/api/result/75` | Get JSON response |
| `/api-docs` | Interactive API documentation |
| `/about` | Learn about Maven Surefire |

---

## <img src="https://cdn.simpleicons.org/github" width="24" height="24" /> Adding Your Own Screenshots

I've added placeholder paths for screenshots in this README. Here's how to add yours:

1. Create a `screenshots` folder in the project root:
   ```bash
   mkdir screenshots
   ```

2. Take screenshots and save them as:
   - `home-page.png` - The main page
   - `api-docs.png` - API documentation page
   - `about-surefire.png` - About page
   - `test-results.png` - Terminal output from `mvn test`

3. The images will automatically show up in this README.

**Pro tip**: On Windows, you can use `Win + Shift + S` to take screenshots quickly.

---

## <img src="https://cdn.simpleicons.org/personio" width="24" height="24" /> Built By

**Anurag Maurya** - Otabhyarthi Batch 24

---

## <img src="https://cdn.simpleicons.org/opensourceinitiative" width="24" height="24" /> License

Free to use for learning and demonstration purposes.

---

## <img src="https://cdn.simpleicons.org/discord" width="24" height="24" /> Got Questions?

If you're learning about Maven Surefire or setting up automated testing for the first time, this project is a great starting point. The code is well-commented, and the `/about` page explains the concepts in plain English.

Happy testing!
