# 🏦 ParaBank Automation Framework

## 📌 Overview
**ParaBank**
is an online demo banking application developed by Parasoft to simulate real-world banking operations, including:
 - 👤 Customer onboarding & authentication

 - 💳 Account creation & management

 - 🔄 Fund transfers & transactions

 - 📝 Updating customer information

 - 🔐 Security features such as password reset & logout

This project is a **robust Test Automation Framework** designed to validate the **end-to-end functionality of ParaBank.**
Built with scalability and reusability in mind, it ensures that all core banking features work seamlessly while reducing manual testing effort.
---

## Performance - ☕ **Java Language** – Strongly typed, object-oriented language for scalable test automation.
- 🌐 **Selenium WebDriver** – Browser automation for functional testing.
- 🧪 **TestNG** – Enables structured test execution with grouping, parallel runs, and reporting.
- 🎨 **Fluent Design Pattern** – Improves readability with chainable, human-like test steps.
- 📊 **Chain Test Report** – Generates detailed structured test reports.
- ⚙️ **CI/CD (GitHub Actions)** – Automates test execution with .yml workflows.mance reviews
**ParaBank**  
## ![ParaBank_Logo](Logo.png)


## ✨ Key Highlights
- 🆕 **New User Registration** – Validates smooth creation of customer accounts.
- 🔑 **Login & Authentication** – Tests valid/invalid login scenarios.
- 🏦 **Open New Account** – Automates account type creation and validation.
- 💸 **Transactions** – Covers deposits, withdrawals, and fund transfers.
- 📝 **Update Contact Information** – Ensures user profile details can be updated successfully.
- 🚪 **Logout** – Validates secure logout functionality.
- 🔐 **Password Reset** – Verifies end-to-end reset workflow.

## ![Usage Example](GIF_ParaBank.gif)

---

## ⚙️ Tech Stack & Tools 🔑 Features
🛠 Framework Features


## 🧾 Test Coverage

 - 1️⃣ validateCreationNewUser_P – User account creation
 - 2️⃣ validateLogin_P – Login functionality
 - 3️⃣ validateOpenNewAccount_P – Open new bank account
 - 4️⃣ validateTransaction_P – Transaction flow (deposit/transfer)
 - 5️⃣ validateUpdateContactInfo_P – Update profile information
 - 6️⃣ validateLogoutFeature_P – Logout validation
 - 7️⃣ validateResetPassword_P – Password reset

## 📂 Project Structure
**ParaBank-Automation**
 - │── src
 - │   ├── main
 - │   │   └── java (Page Objects, Base Classes, Utilities)
 - │   ├── test
 - │   │   └── java (Test Scripts)
 - │── reports (Execution Reports)
 - │── testng.xml
 - │── .github/workflows/ci.yml (GitHub Actions config)
 - │── pom.xml (Dependencies & Build Management)
 - │── README.md

## 📌 Project Status
✅ Stable – Core ParaBank test modules implemented and running with CI/CD.
**🚀 Future Enhancements:**

 - Data-driven testing (JSON/Excel)

 - Cross-browser execution (Chrome, Firefox, Edge)

⚠️ **Under Development** – New features and modules are being continuously enhanced. 