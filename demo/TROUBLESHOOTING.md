# Quick Start & Troubleshooting

## ✅ Quick Start (2 minutes)

### Step 1: Start the Server
```bash
cd c:\Users\ASUS\vetri23suca28\demo
.\mvnw spring-boot:run
```

### Step 2: Open in Browser
```
http://localhost:8080
```

### Step 3: Add a Test Case
Fill in the form with:
- **Case Number**: CASE-2026-001
- **Case Type**: Criminal
- **Status**: Pending
- **Defendant Name**: John Doe
- **Affendant Name**: Jane Smith
- **Description**: Test case (optional)

Click "Add Case" button.

---

## 🔧 Troubleshooting

### Problem 1: "Failed to save court case" Error

**Cause**: Server is not properly validating or saving data.

**Solutions**:

1. **Check Server Logs**:
   - Look at the terminal where `.\mvnw spring-boot:run` is running
   - Look for error messages

2. **Verify All Fields are Filled**:
   ```
   ✓ Case Number: Not empty
   ✓ Case Type: Selected (Criminal or Civil)
   ✓ Status: Selected (Pending, In Progress, or Closed)
   ✓ Defendant Name: Not empty
   ✓ Affendant Name: Not empty
   ```

3. **Check Browser Console** (Press F12):
   - Open "Console" tab
   - Look for detailed error messages
   - Click "Add Case" again to capture the error

4. **Restart the Server**:
   ```bash
   # Stop: Press Ctrl+C in the terminal
   # Rebuild: .\mvnw clean package -DskipTests
   # Start: .\mvnw spring-boot:run
   ```

5. **Check API Response**:
   - Open "Network" tab in browser (F12)
   - Submit the form
   - Click on the POST request to `/api/court-cases`
   - Check "Response" tab for error details

---

### Problem 2: Cannot Connect to Server (Connection Refused)

**Cause**: Server is not running.

**Solution**:
```bash
# In project directory
cd c:\Users\ASUS\vetri23suca28\demo

# Start the server
.\mvnw spring-boot:run

# Wait for message: "Tomcat started on port 8080"
```

---

### Problem 3: Port 8080 Already in Use

**Cause**: Another application is using port 8080.

**Solutions**:

**Option A**: Change the port in `application.properties`:
```properties
server.port=8081
```
Then access: http://localhost:8081

**Option B**: Kill the process using port 8080:
```bash
netstat -ano | findstr :8080
taskkill /F /PID <PID>
```

---

### Problem 4: Build Fails with Errors

**Cause**: Code compilation errors or Maven issues.

**Solution**:
```bash
# Clean build
.\mvnw clean package -DskipTests

# If still failing, delete target directory
rmdir /s /q target

# Then rebuild
.\mvnw clean package -DskipTests
```

---

### Problem 5: Cases Are Not Displaying

**Cause**: Cases exist in database but not showing in UI.

**Solution**:
1. Click "REFRESH" button
2. Check H2 Console:
   - Go to http://localhost:8080/h2-console
   - Login with username: `sa`, password: (empty)
   - Run SQL: `SELECT * FROM court_cases;`
   - Verify data exists

---

## 📊 Check Database

### H2 Console Access
```
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (leave empty)
```

### View All Cases
```sql
SELECT * FROM court_cases;
```

### Count Cases by Status
```sql
SELECT status, COUNT(*) FROM court_cases GROUP BY status;
```

### Delete All Cases
```sql
DELETE FROM court_cases;
```

---

## 🔄 Restart Procedure

**Complete Restart**:
```bash
# 1. Stop the server (Ctrl+C in terminal)

# 2. Kill any remaining Java processes
taskkill /F /IM java.exe

# 3. Clean rebuild
.\mvnw clean package -DskipTests

# 4. Start the server
.\mvnw spring-boot:run

# 5. Access application
# http://localhost:8080
```

---

## 📝 Log Important Information

When reporting an issue, include:

1. **Error Message**: Exact error text from browser or console
2. **Browser Console** (F12 > Console): All JavaScript errors
3. **Server Logs**: Terminal output where server is running
4. **Network Tab** (F12 > Network): Response from failed API call
5. **Steps to Reproduce**: What you did before the error

---

## ✨ Verify Installation

Run this to verify everything is working:

```bash
# 1. Check Java version
java -version

# 2. Check Maven
.\mvnw --version

# 3. Build test
.\mvnw clean compile

# 4. Run tests
.\mvnw test

# 5. Package
.\mvnw package -DskipTests

# 6. Run application
.\mvnw spring-boot:run
```

---

## 🎯 API Testing with cURL

Test the API without using the frontend:

```bash
# Add a case
curl -X POST http://localhost:8080/api/court-cases \
  -H "Content-Type: application/json" \
  -d "{\"caseNumber\":\"TEST-001\",\"caseType\":\"CRIMINAL\",\"status\":\"PENDING\",\"defendantName\":\"John\",\"affendantName\":\"Jane\",\"description\":\"Test\"}"

# Get all cases
curl http://localhost:8080/api/court-cases

# Get case by ID
curl http://localhost:8080/api/court-cases/1

# Update case
curl -X PUT http://localhost:8080/api/court-cases/1 \
  -H "Content-Type: application/json" \
  -d "{\"caseNumber\":\"TEST-001\",\"caseType\":\"CIVIL\",\"status\":\"IN_PROGRESS\",\"defendantName\":\"John\",\"affendantName\":\"Jane\"}"

# Delete case
curl -X DELETE http://localhost:8080/api/court-cases/1
```

---

## ✅ Success Checklist

- [ ] Server is running (see "Tomcat started on port 8080")
- [ ] Can access http://localhost:8080
- [ ] Form appears on page
- [ ] Can fill in all fields without errors
- [ ] Can submit form
- [ ] Success message appears
- [ ] Case appears in the list
- [ ] Statistics update automatically
- [ ] Can filter by status and type
- [ ] Can edit cases
- [ ] Can delete cases

---

## 📞 Need More Help?

1. Check the `SETUP_GUIDE.md` file for detailed information
2. Review the code comments in the Java files
3. Check H2 Console to verify database state
4. Monitor browser console (F12) for JavaScript errors
5. Monitor server terminal for Java/Spring errors
