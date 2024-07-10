# TechPanda Cucumber Automation Framework

## Description

This project is a comprehensive automation testing framework for the TechPanda e-commerce website. It implements Behavior-Driven Development (BDD) using Cucumber, providing extensive test coverage for both admin and user functionalities.

The framework is designed to test a wide range of e-commerce features including Register, Login, Search, CRUD, and Admin Manage...

## Tech Stack

- **Programming Language**: Java
- **Automation Tool**: Selenium WebDriver
- **BDD Framework**: Cucumber
- **Testing Framework**: TestNG
- **Build Tool**: Maven
- **Reporting**: Allure Cucumber
- **Additional libraries**: javafaker, java owner, Apache POI ...

## Key Features

1. **Cucumber BDD**
   - Feature files written in Gherkin syntax for clear test case documentation
   - Step definitions mapping features to code
   - Encourages collaboration between technical and non-technical team members
  
2.  **Page Object Model (POM)**
    - implements the POM design pattern for improved maintainability and reusability
    - Separates page elements and actions for cleaner code structure

4. **GitHub Actions CI**
   - Continuous Integration setup using GitHub Actions
   - Automated test execution on push or pull requests in branch master

5. **Cross-Browser and Multi-Environment Testing**
   - Supports tests across multiple browsers (Chrome, Firefox, Safari, Edge)
   - Configurable for different test environments (Production ....)

6. **Data Generation**
   - Utilizes libraries like JavaFaker for dynamic test data creation
   - Generates realistic user information, product details, and order data
  
7. **Parallel Execution**
   - Supports concurrent execution of test scenarios
   - Configurable thread count for optimal performance

8. **Allure Reporting**
   - Comprehensive test reports with Allure Cucumber integration
   - Detailed step-by-step test execution logs
   - Visual representations of test results and trends
     
9. **Screenshot Capture**
    - Automatic capture of screenshots on test failure
    - Embedded screenshots in Allure reports for easy debugging

   
