import java.sql.*;
import java.util.Scanner;
import java.sql.SQLException;

public class Main {
    static Connection conn;
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            Class.forName(Constants.DB_DRIVER);
            dbConnection();

            dbProccess(scanner);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void dbProccess(Scanner scanner) throws SQLException {
        try (Statement statement = createStatement()){
            boolean running = true;
            while(running){
                System.out.println("""                
                Create users table -> 1
                Insert data to users table -> 2
                Get all data from users table -> 3
                Close connections -> 4
                Exit -> 5
                """);
                switch (scanner.nextInt()){
                    case 1 -> statement.executeUpdate(Queries.createUsersTable);

                    case 2 -> Queries.usersData.forEach(insert ->{
                        try{
                            statement.executeUpdate(insert);
                        }catch (SQLException e){
                            throw new RuntimeException(e);
                        }
                    });

                    case 3 -> {
                        ResultSet resultSet = statement.executeQuery(Queries.selectAllFromUsers);
                        while (resultSet.next()){
                            System.out.println("ID :" + resultSet.getInt("id") + " USERNAME : " + resultSet.getString("username")
                            + " EMAIL : " + resultSet.getString("email"));
                        }
                    }

                    case 4 -> closeDbConnection();
                    case 5 -> running = false;
                    default -> System.out.println("Gecersiz secim, tekrar deneyin.");
                }
            }
        }
    }

    public static void dbConnection(){
        try{
            conn = DriverManager.getConnection(Constants.DB_URL, Constants.BD_USER, Constants.DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Statement createStatement(){
        try {
            return conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeDbConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}