package daw.programacio.thenewmisterquestion.controllers;

import daw.programacio.thenewmisterquestion.CategoryModel;
import daw.programacio.thenewmisterquestion.MisterQuestionApplication;
import daw.programacio.thenewmisterquestion.data.DBFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CategoriesController implements Initializable {
    @FXML
    private ListView lvCategoria;

    @FXML
    private TextField txtCategoria;

    private ObservableList<String> categorias;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categorias = FXCollections.observableArrayList();
        rellenarTabla();
    }

    private void rellenarTabla() {
        categorias.removeAll();
        for(CategoryModel categoria: MisterQuestionApplication.categories){
            this.categorias.add(categoria.getName());
        }
        this.lvCategoria.setItems(this.categorias);
    }

    @FXML
    public void tornar(){
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


    public void save(){
        if(this.lvCategoria.getSelectionModel().getSelectedItem() == null) {
            DBFacade.insertCategoria(this.txtCategoria.getText());
        } else {
            DBFacade.actualizarCategoria(buscar());
        }


        CategoryModel cm = new CategoryModel();
        MisterQuestionApplication.categories = cm.getCategoryModel();
        this.lvCategoria.getItems().clear();
        rellenarTabla();
    }


    public void delete(){
        if(this.lvCategoria.getSelectionModel().getSelectedItem() != null) {
            CategoryModel categoria = buscar();
            DBFacade.deleteInsert("category", categoria.getId());
            CategoryModel cm = new CategoryModel();
            MisterQuestionApplication.categories = cm.getCategoryModel();
            this.lvCategoria.getItems().clear();
            rellenarTabla();
        }
    }

    private CategoryModel buscar(){
        for(CategoryModel categoria: MisterQuestionApplication.categories){
            if(categoria.getName().equals(this.lvCategoria.getSelectionModel().getSelectedItem())){
                return categoria;
            }
        }
        return null;
    }
}
