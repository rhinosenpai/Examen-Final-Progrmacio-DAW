package daw.programacio.thenewmisterquestion;

import daw.programacio.thenewmisterquestion.data.DBFacade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MisterQuestionApplication extends Application {
    public static Stage window;

    @Override
    public void start(Stage stage) throws IOException {
        if(DBFacade.logOn()) {
            System.out.println("Connexion established");
            DBFacade.select();
        }
        window = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MisterQuestionApplication.class.getResource("views/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}