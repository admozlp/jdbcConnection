import java.util.ArrayList;
import java.util.List;

public class Queries {

    public static final String createUsersTable = """
            CREATE TABLE IF NOT EXISTS users (
             id SERIAL PRIMARY KEY,
             username VARCHAR(255),
             email VARCHAR(255)
            );
            """;

    public static final String user1 = "INSERT INTO users (username, email) values ('adem', 'ademozalp57@gmail.com')";

    public static final String user2 = "INSERT INTO users (username, email) values ('test', 'test40@gmail.com')";

    public static final List<String> usersData = List.of(user1,user2);

    public static final String selectAllFromUsers = "SELECT * FROM users";


}
