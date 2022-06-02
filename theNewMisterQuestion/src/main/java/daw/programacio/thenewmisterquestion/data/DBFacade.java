package daw.programacio.thenewmisterquestion.data;
import java.sql.*;

public final class DBFacade {
    private static final String pathToDb = "jdbc:sqlite:theNewMisterQuestion/src/main/java/daw/programacio/thenewmisterquestion/data/";
    private static final String db = "sqlite.db";
    private static Connection conn;


    public static boolean logOn() {
        String url = pathToDb + db;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        if(conn != null) {
            return true;
        }
        return false;
    }

    public static void logOff() throws SQLException {
        if(conn != null) {
            conn.close();
        }
    }

    public static void select() {
        String sql = "SELECT * FROM question;";
        if(conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    System.out.println(rs.getString(2));
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
