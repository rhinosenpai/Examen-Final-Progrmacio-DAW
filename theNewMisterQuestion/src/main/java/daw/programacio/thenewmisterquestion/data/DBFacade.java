package daw.programacio.thenewmisterquestion.data;
import daw.programacio.thenewmisterquestion.CategoryModel;
import daw.programacio.thenewmisterquestion.QuestionModel;

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
                    System.out.println(rs.getInt(1));
                    System.out.print("," + rs.getString(2));
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static ResultSet select(String tabla) {
        String sql = "SELECT * FROM " + tabla +";";
        if(conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                return rs;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    public static ResultSet select(String tabla,String condition) {
        String sql = "SELECT * FROM " + tabla + " WHERE " + condition +";";
        if(conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                return rs;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    public static void insertQuestion(QuestionModel q){
        String[] answers = q.getAnswers();
        String sql = "INSERT INTO question (question,answer_A,answer_B,answer_C,correct_answer,value,category) VALUES (\""
                + q.getQuestion() + "\",\"" + answers[0] + "\",\"" + answers[1] +
                "\",\"" + answers[2] + "\"," + q.getCorrectAnswer() + "," + q.getValue() + "," + q.getCategory() + ");";
        if(conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                System.out.println("Se ha añadido correctamente");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void actualizarQuestion(QuestionModel q,String condition){
        String[] answers = q.getAnswers();
        String sql = "UPDATE question  SET question = \"" + q.getQuestion() + "\", answer_A = \"" + answers[0] + "\"" +
                ", answer_B = \"" + answers[1] + "\", answer_C = \"" + answers[2] + "\", correct_answer = " +
                q.getCorrectAnswer() + ", value = " + q.getValue() + ", category = " + q.getCategory() + " WHERE " + condition + ";";
        if(conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                System.out.println("Se ha añadido correctamente");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void deleteInsert(String table, int id){
        String sql = "DELETE FROM " + table + " WHERE id = " + id;
        if(conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                System.out.println("Se ha borrado correctamente");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void insertCategoria(String cm){
        String sql = "INSERT INTO category (name) VALUES (\""
                + cm +"\");";
        if(conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                System.out.println("Se ha añadido correctamente");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void actualizarCategoria(CategoryModel cm){
        String sql = "UPDATE catgory SET name =\""
                + cm.getName() +"\" WHERE id = " + cm.getId() + ";";
        if(conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                System.out.println("Se ha modificado correctamente");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
