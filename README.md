# 🔐 Java Vulnerable App – Security Pipeline with GitHub Actions

This project is a deliberately vulnerable Java application designed to demonstrate secure DevOps practices using GitHub Actions.

It includes a fully automated CI pipeline for **Static Code Analysis**, **Dependency Scanning**, **Secret Scanning**, and **Code Linting**, all using **open-source tools**.

---

## ✅ Implemented Security Tools

| Tool                       | Type                                       | Description                                      |
| -------------------------- | ------------------------------------------ | ------------------------------------------------ |
| **Semgrep**                | SAST (Static Application Security Testing) | Detects insecure code patterns & vulnerabilities |
| **OWASP Dependency-Check** | Dependency Scanning                        | Identifies known vulnerable libraries via CVEs   |
| **Gitleaks**               | Secret Scanning                            | Finds hardcoded secrets in codebase              |
| **Checkstyle**             | Code Linting with Security Rules           | Flags style issues and poor coding practices     |

---

## ⚙️ Tool Configuration Details

### 1. **Semgrep**

- **File**: `.github/workflows/sast-template.yml`
- **Command**: `semgrep --config auto .`
- **Runs on**:
  - Pull Requests
  - Push to `main`
  - Weekly schedule
- **Purpose**: Detect SQL injection, hardcoded credentials, unsafe APIs

### 2. **OWASP Dependency-Check**

- **File**: `.github/workflows/dependency-scan-template.yml`
- **Command**: Scans using official CLI via GitHub Actions
- **Output**: HTML report as artifact
- **Purpose**: Identify third-party dependencies with known CVEs

### 3. **Gitleaks**

- **File**: `.github/workflows/secret-scan-template.yml`
- **Command**: Runs `gitleaks` as GitHub Action
- **Purpose**: Find exposed secrets like API keys, DB credentials, tokens

### 4. **Checkstyle**

- **File**: `.github/workflows/linting-template.yml`
- **Command**: `java -jar checkstyle.jar -c /google_checks.xml src/`
- **Purpose**: Enforce code quality and catch insecure patterns

---

## 🤔 Why These Tools Were Chosen

| Check Type      | Reason for Inclusion                                                |
| --------------- | ------------------------------------------------------------------- |
| SAST (Semgrep)  | Quickly detects vulnerable code paths (e.g., SQLi)                  |
| Dependency Scan | Prevents usage of known-vulnerable libraries                        |
| Secret Scan     | Prevents accidental leakage of credentials                          |
| Linting         | Ensures consistent and readable code; detects suspicious constructs |

All tools are open source and can run entirely within CI/CD pipelines.

---

## 📊 How to Interpret the Results

### 🔎 Semgrep

- Output shown in GitHub Actions logs
- Highlights file, line, rule, and vulnerability type
- Fix insecure logic (e.g., string SQL concatenation)

### 🧱 Dependency-Check

- Generates `dependency-check-report/index.html`
- Download from **Actions → Job → Artifacts**
- Look for **Critical** or **High** severity CVEs

### 🔐 Gitleaks

- Outputs secret matches in the logs
- Fix by removing secrets and rotating them

### 🧹 Checkstyle

- Lists formatting violations and best-practice issues
- Helps ensure clean, safe code before merging

---

## 🛠️ Custom Rules or Configurations

- **Checkstyle** uses [Google Java Style Guide](https://checkstyle.sourceforge.io/google_style.html) via `/google_checks.xml`
- **Semgrep** uses `--config auto` to auto-detect Java rules
- You can extend with a `.semgrep.yml` for custom SAST rules if needed

---

## 🚀 How to Run Locally

```bash
# Compile the project
mvn clean install

# Run OWASP Dependency-Check locally (optional)
# Download tool from https://github.com/jeremylong/DependencyCheck

# Run Checkstyle manually (optional)
java -jar checkstyle.jar -c /google_checks.xml src/
```

| Trigger Type          | Enabled |
| --------------------- | ------- |
| Push to `main` branch | ✅      |
| Pull Request creation | ✅      |
| Weekly Scheduled Run  | ✅      |
