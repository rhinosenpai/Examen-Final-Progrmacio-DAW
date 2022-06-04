package daw.programacio.thenewmisterquestion.controllers;

import daw.programacio.thenewmisterquestion.CategoryModel;
import daw.programacio.thenewmisterquestion.MisterQuestionApplication;
import daw.programacio.thenewmisterquestion.QuestionModel;
import daw.programacio.thenewmisterquestion.data.DBFacade;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.constant.Constable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuestionController implements Initializable {
    QuestionModel qm;
    ObservableList<MenuItem> categorias;
    ArrayList<CategoryModel> category;

    @FXML
    private TextField txtA,txtB,txtC,txtPreg,txtValor;

    @FXML
    private RadioButton rdbA,rdbB, rdbC;

    @FXML
    private SplitMenuButton smenuCategoria;


    @FXML
    private ToggleGroup ans = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        añadirCategoriasMenu();
        this.rdbA.setToggleGroup(ans);
        this.rdbB.setToggleGroup(ans);
        this.rdbC.setToggleGroup(ans);
    }

    @FXML
    public void initTxtFields(QuestionModel q){
        this.qm = q;
        String[] ans = q.getAnswers();
        this.txtA.setText(ans[0]);
        this.txtB.setText(ans[1]);
        this.txtC.setText(ans[2]);
        this.txtPreg.setText(q.getQuestion());
        this.txtValor.setText(q.getValue() + "");
    }

    @FXML
    private void añadirCategoriasMenu(){
        for(CategoryModel categoria: MisterQuestionApplication.categories){
            MenuItem mi = new MenuItem(categoria.getName());
            this.smenuCategoria.getItems().add(mi);
            mi.setOnAction((e) -> {
                smenuCategoria.setText(categoria.getName());
            });
        }

    }

    public QuestionModel save(){
        int id = 0;
        if(this.qm != null){
            id = this.qm.getId();
        }

        int correct_answer = 0;
        String[] respuestas = {this.txtA.getText(),this.txtB.getText(),this.txtC.getText()};
        if(rdbA.isSelected()){ correct_answer = 1; }
        if(rdbB.isSelected()){ correct_answer = 2; }
        if(rdbC.isSelected()){ correct_answer = 3; }

        CategoryModel categoria = categoriaElegida();

        QuestionModel question = new QuestionModel(id,this.txtPreg.getText(),respuestas,Integer.parseInt(this.txtValor.getText()),categoria.getId(),correct_answer);

        return question;
    }

    private CategoryModel categoriaElegida() {
        String busqueda = smenuCategoria.getText();


        for(CategoryModel categoria: MisterQuestionApplication.categories){
            if (categoria.getName().equals(busqueda) ){
                return categoria;
            }
        }

        return null;
    }

    public void volver(){
        this.qm = null;
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

    public void Guardar(){
        if(this.qm == null) {
            DBFacade.insertQuestion(save());
            MisterQuestionApplication.questions = qm.getQuestionsModels();
        } else {
            String sql = "id = " + qm.getId();
            DBFacade.actualizarQuestion(save(),sql);
            MisterQuestionApplication.questions = qm.getQuestionsModels();
        }
    }
}
