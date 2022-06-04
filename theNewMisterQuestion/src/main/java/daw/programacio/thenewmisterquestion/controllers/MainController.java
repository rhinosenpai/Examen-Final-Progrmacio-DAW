package daw.programacio.thenewmisterquestion.controllers;

import daw.programacio.thenewmisterquestion.MisterQuestionApplication;
import daw.programacio.thenewmisterquestion.PlayerModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {

    private ArrayList<PlayerModel> records;

    @FXML
    protected void goToQuestions() {
        FXMLLoader loader = new FXMLLoader(MisterQuestionApplication.class.getResource("views/questions-view.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        MisterQuestionApplication.window.setScene(scene);
        MisterQuestionApplication.window.show();
    }


    @FXML
    protected void goToPlay() {
        FXMLLoader loader = new FXMLLoader(MisterQuestionApplication.class.getResource("views/questions-view.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        MisterQuestionApplication.window.setScene(scene);
        MisterQuestionApplication.window.show();
    }
}