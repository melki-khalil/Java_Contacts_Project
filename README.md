# Java Contacts Project

A Java Swing-based contact management desktop application that simulates a smartphone contact interface. Users can register, sign in, manage contacts, simulate calls, and view recent call history.

## 🧩 Features

- 📱 **Contact Management**: Add, edit, delete, and favorite contacts.
- 🔐 **User Authentication**: Sign up and sign in functionality with account handling.
- 🌐 **Country Code Support**: Automatically loads country codes with flags.
- ☎️ **Calling Simulation**: Mimics a dialer keypad and recent call tracking.
- 💾 **Data Persistence**: Stores data using JSON and connects to a MySQL database via JDBC.
- 🖼️ **Custom UI**: Clean and interactive Swing interface with icons and custom panels.

## 🛠️ Technologies Used

- Java (Swing for GUI)
- MySQL (JDBC for database)
- Maven (Project management)
- Gson (JSON parsing)
- JSON (For country codes and contacts data)
- Eclipse IDE (project structure based on Eclipse)

## 📦 Prerequisites

- Java JDK 8 or above
- Maven installed
- MySQL server running
- Eclipse IDE (optional, for development)

## 📁 Project Structure
PhoneContact/
├── src/
│ ├── API/ # Database models and JDBC handling
│ └── connect/com/ # GUI components and main application logic
├── gson-2.13.0.jar # Gson library
├── contacts_data.json # Sample contacts
├── country_codes_data.json # Country codes with metadata
├── pom.xml # Maven configuration

## 🧪 Running the Application

1. **Clone the repository**:
   ```bash
   git clone https://github.com/melki-khalil/Java_Contacts_Project.git
   cd Java_Contacts_Project/PhoneContact
2. **Set up the database**  
   Use the provided SQL file located in the `Java_Contacts_Project/PhoneContact` folder to recreate the database schema and tables.

3. **Build and run**:
 ```bash
mvn clean install
mvn exec:java -Dexec.mainClass="connect.com.Contact"


