package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    public TableColumn tbAuthor;
    public TableColumn tbTitle;
    public TableColumn tbCategories;
    public TableColumn tbIsbn;
    private ObservableList

    public void initialize(){
        tbAuthor.setCellValueFactory(new PropertyValueFactory("author"));
        tbTitle.setCellValueFactory(new PropertyValueFactory("title"));
        tbCategories.setCellValueFactory(new PropertyValueFactory("category"));
        tbIsbn.setCellValueFactory(new PropertyValueFactory("isbn"));


    }

    public void actAdd(ActionEvent actionEvent) {
    }

    public void actEdit(ActionEvent actionEvent) {
    }

    public void actDelete(ActionEvent actionEvent) {
    }
}
