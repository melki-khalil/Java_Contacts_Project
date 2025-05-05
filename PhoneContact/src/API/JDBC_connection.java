package API;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class JDBC_connection {
    private String url = "jdbc:mysql://localhost/phone_contact";
    private String user = "root";
    private String password = "";

    Connection db = null;
    Statement st = null;

    public JDBC_connection() {
        try {
            db = DriverManager.getConnection(this.url, this.user, this.password);
            st = db.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Adding an account
    public boolean add_Account(String name, String surname, String username, String email, String password, String codeC) {
        if (accountExists(username, email)) {
            JOptionPane.showMessageDialog(null, "An account with this username or email already exists.", "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        String sql = "INSERT INTO account (name, surname, username, email, password, id_country) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = db.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, surname);
            pst.setString(3, username);
            pst.setString(4, email);
            pst.setString(5, password);
            pst.setString(6, codeC);

            int rowsAffected = pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Your account has been added successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to add the account.", "Insert Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean accountExists(String username, String email) {
        String sql = "SELECT 1 FROM account WHERE username = ? OR email = ? LIMIT 1";

        try (PreparedStatement pst = db.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, email);

            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while checking account existence.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public Account getAccount(String username) {
        String sql = "SELECT * FROM account WHERE username = ? OR email = ? LIMIT 1";

        try (PreparedStatement pst = db.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, username);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Account account = new Account();
                    account.setIdA(rs.getInt("idA"));
                    account.setName(rs.getString("name"));
                    account.setSurname(rs.getString("surname"));
                    account.setUsername(rs.getString("username"));
                    account.setEmail(rs.getString("email"));
                    account.setPassword(rs.getString("password"));
                    account.setIdCountry(rs.getString("id_country"));
                    return account;
                } else {
                    JOptionPane.showMessageDialog(null, "No account found with the provided username.", "Account Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while fetching the account.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    // Insert or update a contact
    public PhoneNumber insertContact(PhoneNumber contact) {
        String checkSql = "SELECT idC FROM phone_number WHERE idC = ?";
        String updateSql = "UPDATE phone_number SET isFav = ?, country_code = ?, NAME = ?, NUMBER = ?, image = ? WHERE idC = ?";
        String insertSql = "INSERT INTO phone_number (idA, NAME, NUMBER, isFav, country_code, image) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement checkStmt = db.prepareStatement(checkSql)) {
            checkStmt.setInt(1, contact.getIdC());

            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    // Contact exists: update
                    try (PreparedStatement updateStmt = db.prepareStatement(updateSql)) {
                        updateStmt.setBoolean(1, contact.getIsFav());
                        updateStmt.setString(2, contact.getCountryCode());
                        updateStmt.setString(3, contact.getName());
                        updateStmt.setString(4, contact.getNumber());
                        updateStmt.setBytes(5, contact.getImage());
                        updateStmt.setInt(6, contact.getIdC());
                        updateStmt.executeUpdate();
                    }
                } else {
                    // Contact doesn't exist: insert
                    try (PreparedStatement insertStmt = db.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                        insertStmt.setInt(1, contact.getIdA());
                        insertStmt.setString(2, contact.getName());
                        insertStmt.setString(3, contact.getNumber());
                        insertStmt.setBoolean(4, contact.getIsFav());
                        insertStmt.setString(5, contact.getCountryCode());
                        insertStmt.setBytes(6, contact.getImage());
                        insertStmt.executeUpdate();

                        try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                contact.setIdC(generatedKeys.getInt(1));
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error inserting/updating contact in the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        return contact;
    }
    
    
    // updating isfav value whenever it change in the GUI
    public void updateFav(PhoneNumber contact) {
        String updateSql = "UPDATE phone_number SET isFav = ? WHERE idC = ?";

        try (PreparedStatement updateStmt = db.prepareStatement(updateSql)) {
            updateStmt.setBoolean(1, contact.getIsFav());
            updateStmt.setInt(2, contact.getIdC());
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating favorite status in the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public List<PhoneNumber> getContactsByIdA(int idA) {
        List<PhoneNumber> contacts = new ArrayList<>();
        String query = "SELECT * FROM phone_number WHERE idA = ?";

        try (PreparedStatement stmt = db.prepareStatement(query)) {
            stmt.setInt(1, idA);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PhoneNumber contact = new PhoneNumber();
                    
                    contact.setIdC(rs.getInt("IdC"));
                    contact.setIdA(rs.getInt("idA"));
                    contact.setName(rs.getString("NAME"));
                    contact.setNumber(rs.getString("NUMBER"));
                    contact.setIsFav(rs.getBoolean("isFav"));
                    contact.setCountryCode(rs.getString("country_code"));
                    contact.setImage(rs.getBytes("image"));
                    contacts.add(contact);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving contacts from the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        return contacts;
    }

    public boolean deleteContact(PhoneNumber contact) {
        String deleteSql = "DELETE FROM phone_number WHERE idA = ? AND NAME = ? AND NUMBER = ?";

        try (PreparedStatement deleteStmt = db.prepareStatement(deleteSql)) {
            deleteStmt.setInt(1, contact.getIdA());
            deleteStmt.setString(2, contact.getName());
            deleteStmt.setString(3, contact.getNumber());

            int affectedRows = deleteStmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Contact deleted successfully.", "Deleting status", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No contact matched the provided criteria.", "Deleting status", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting contact from the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

   
}
