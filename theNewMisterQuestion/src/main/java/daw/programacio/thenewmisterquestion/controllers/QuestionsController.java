package daw.programacio.thenewmisterquestion.controllers;

import daw.programacio.thenewmisterquestion.MisterQuestionApplication;
import daw.programacio.thenewmisterquestion.QuestionModel;
import daw.programacio.thenewmisterquestion.data.DBFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuestionsController implements Initializable {
    @FXML
    private TableView<QuestionModel> tblPreguntas;
    @FXML
    private TableColumn colPregunta,colCategoria,colUtilizada;

    private ObservableList<QuestionModel> questions;


    public QuestionsController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        questions = FXCollections.observableArrayList();

        this.colPregunta.setCellValueFactory(new PropertyValueFactory("question"));
        this.colCategoria.setCellValueFactory(new PropertyValueFactory("category"));
        this.colUtilizada.setCellValueFactory(new PropertyValueFactory("value"));
        rellenarQuestions();
    }

    @FXML
    public void addQuestionView() {
        FXMLLoader loader = new FXMLLoader(MisterQuestionApplication.class.getResource("views/question-view.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        QuestionController controller = loader.getController();
        if(seleccionar()){
            controller.initTxtFields(this.tblPreguntas.getSelectionModel().getSelectedItem());
        }
        Scene scene = new Scene(root);
        MisterQuestionApplication.window.setScene(scene);
        MisterQuestionApplication.window.show();
    }

    public void deleteQuestion(){
        QuestionModel r = this.tblPreguntas.getSelectionModel().getSelectedItem();
        DBFacade.deleteInsert("question",this.tblPreguntas.getSelectionModel().getSelectedItem().getId());
        if(r != null){
            MisterQuestionApplication.questions.remove(r);
            this.questions.remove(r);
            this.tblPreguntas.refresh();
        }
    }

    @FXML
    public void volver(){
        FXMLLoader loader = new FXMLLoader(MisterQuestionApplication.class.getResource("views/main-view.fxml"));

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
    public void goToCategories(){
        FXMLLoader loader = new FXMLLoader(MisterQuestionApplication.class.getResource("views/categories-view.fxml"));

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
    public void rellenarQuestions(){
        for(QuestionModel pregunta: MisterQuestionApplication.questions){
            this.questions.add(pregunta);
        }
        this.tblPreguntas.setItems(this.questions);
    }

    @FXML
    private boolean seleccionar(){
        QuestionModel r = this.tblPreguntas.getSelectionModel().getSelectedItem();

        if(r != null) {
            return true;
        }
        return false;
    }
}
