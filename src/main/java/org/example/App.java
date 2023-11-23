package org.example;

import java.sql.*;

public class App
{
    public static void main( String[] args )
    {
        selectAllDemo();
        System.out.printf("%n");
        insertStudentDemo();
        System.out.printf("%n");
        selectAllDemo();
        System.out.printf("%n");
        updateStudentDemo();
        System.out.printf("%n");
        selectAllDemo();
        System.out.printf("%n");
        deleteStudentDemo(6);
        System.out.printf("%n");
        selectAllDemo();
        System.out.printf("%n");
        findAllByNameLike("Kel");
        System.out.printf("%n");

    }

    public static void findAllByNameLike(String pattern) {

        // Hier sind die ganzen Attribute zum Verbinden
        System.out.println("Find Demo mit JDBC");
        String connectionURL = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pswrd = "";

        // Hier wird die Verbindung zur MySQL Datenbank hergestellt
        // Die Verbindung muss in einem try-catch block erfolgen, falls ein fehler vorliegt
        try(Connection conn = DriverManager.getConnection(connectionURL, user, pswrd)) {
            System.out.println("Verbindung zur Datenbank hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `student` WHERE `student`.`name` LIKE ?;");
            preparedStatement.setString(1,"%"+pattern+"%");
            ResultSet rs = preparedStatement.executeQuery(); // Hier holt er es sich

            // Funktioniert wie die Foreach schleife.
            // Ganz unten werden dann die ganzen Studenten ausgegeben mit ID, NAME und EMAIL
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println("Student | ID: "+id+" NAME: "+name+" EMAIL: "+email);
            }

        } catch (SQLException e) {
            System.out.println("Fehler beim verbinden der Datenbank: "+e.getMessage());
        }
    }

    public static void deleteStudentDemo(int studentID) {
        // Hier sind die ganzen Attribute zum Verbinden
        System.out.println("Delete Demo mit JDBC");
        String connectionURL = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pswrd = "";

        // Hier wird die Verbindung zur MySQL Datenbank hergestellt
        // Die Verbindung muss in einem try-catch block erfolgen, falls ein fehler vorliegt
        try(Connection conn = DriverManager.getConnection(connectionURL, user, pswrd)) {
            System.out.println("Verbindung zur Datenbank hergestellt!");
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM student WHERE `student`.`id` = ?" // Diese Fragezeichen müssen sein
            );

            // Den Insert muss man ebenfalls in einen try-catch block machen da hier auch fehler entstehen können.
            try {

                preparedStatement.setInt(1, studentID);
                int rowAffected = preparedStatement.executeUpdate();
                System.out.println("Anzahl der gelöschten Datensätze: "+rowAffected);

            } catch (SQLException ex) {
                System.out.println("Fehler im SQL-DELETE Statement: "+ ex.getMessage());
            }


        } catch (SQLException e) {
            System.out.println("Fehler beim verbinden der Datenbank: "+e.getMessage());
        }
    }

    public static void updateStudentDemo() {

        // Hier sind die ganzen Attribute zum Verbinden
        System.out.println("Update Demo mit JDBC");
        String connectionURL = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pswrd = "";

        // Hier wird die Verbindung zur MySQL Datenbank hergestellt
        // Die Verbindung muss in einem try-catch block erfolgen, falls ein fehler vorliegt
        try(Connection conn = DriverManager.getConnection(connectionURL, user, pswrd)) {
            System.out.println("Verbindung zur Datenbank hergestellt!");
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE `student` SET `name` = ?, `email` = ? WHERE `student`.`id` = 4" // Diese Fragezeichen müssen sein
            );

            // Den Update-Befehl muss man ebenfalls in einen try-catch block machen da hier auch fehler entstehen können.
            try {

                preparedStatement.setString(1, "Hans Zimmer");
                preparedStatement.setString(2,"hans.zimmer@hotmail.com");
                int rowAffected = preparedStatement.executeUpdate(); // Hier findet er heraus wieviele Datensätze geupdatet wurden
                System.out.println("Anzahl der aktuallisierten Datensätze: "+rowAffected);

            } catch (SQLException ex) {
                System.out.println("Fehler im SQL-UPDATE Statement: "+ ex.getMessage());
            }


        } catch (SQLException e) {
            System.out.println("Fehler beim verbinden der Datenbank: "+e.getMessage());
        }
    }

    public static void insertStudentDemo() {

        // Hier sind die ganzen Attribute zum Verbinden
        System.out.println("Insert Demo mit JDBC");
        String connectionURL = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";
        String pswrd = "";

        // Hier wird die Verbindung zur MySQL Datenbank hergestellt
        // Die Verbindung muss in einem try-catch block erfolgen, falls ein fehler vorliegt
        try(Connection conn = DriverManager.getConnection(connectionURL, user, pswrd)) {
            System.out.println("Verbindung zur Datenbank hergestellt!");
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO `student` (`id`, `name`, `email`) VALUES (NULL, ?, ?)" // Diese Fragezeichen müssen sein
            );

            // Den Insert muss man ebenfalls in einen try-catch block machen da hier auch fehler entstehen können.
            try {

                // Hier Programmiert man welche neuen Daten eingefügt werden
                preparedStatement.setString(1, "Peter Zeck");
                preparedStatement.setString(2, "peter.zeck@aon.at");
                int rowAffected = preparedStatement.executeUpdate(); // Hier findet er heraus wieviele Datensätze inserted wurden
                System.out.println(rowAffected + " Datensätze eingefügt!");

            } catch (SQLException ex) {
                System.out.println("Fehler im SQL-INSERT Statement: "+ ex.getMessage());
            }


        } catch (SQLException e) {
            System.out.println("Fehler beim verbinden der Datenbank: "+e.getMessage());
        }
    }

    public static void selectAllDemo() {

        // Hier sind die ganzen Attribute zum Verbinden
        System.out.println("Select Demo mit JDBC");
        String connectionURL = "jdbc:mysql://localhost:3306/jdbcdemo";
        String user = "root";        String pswrd = "";

        // Hier wird die Verbindung zur MySQL Datenbank hergestellt
        // Die Verbindung muss in einem try-catch block erfolgen, falls ein fehler vorliegt
        try(Connection conn = DriverManager.getConnection(connectionURL, user, pswrd)) {
            System.out.println("Verbindung zur Datenbank hergestellt!");

            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM `student`");
            ResultSet rs = preparedStatement.executeQuery(); // Hier holt er es sich

            // Funktioniert wie die Foreach schleife.
            // Ganz unten werden dann die ganzen Studenten ausgegeben mit ID, NAME und EMAIL
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println("Student | ID: "+id+" NAME: "+name+" EMAIL: "+email);
            }

        } catch (SQLException e) {
            System.out.println("Fehler beim verbinden der Datenbank: "+e.getMessage());
        }
    }
}
