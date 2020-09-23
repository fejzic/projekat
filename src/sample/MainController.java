package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class MainController {

    public TableColumn tbAuthor;
    public TableColumn tbTitle;
    public TableColumn tbCategories;
    public TableColumn tbIsbn;
    public TableView table;
    private static ClassDaoBase base;
    private Book book;

    public void initialize(){
        book = new Book();
        base = ClassDaoBase.getInstance();
        tbAuthor.setCellValueFactory(new PropertyValueFactory("author"));
        tbTitle.setCellValueFactory(new PropertyValueFactory("title"));
        tbCategories.setCellValueFactory(new PropertyValueFactory("category_id"));
        tbIsbn.setCellValueFactory(new PropertyValueFactory("isbn"));

        table.setItems(base.books());

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {
            @Override
            public void changed(ObservableValue<? extends Book> observableValue, Book oldBook, Book newBook) {
                if (oldBook != null) {
                }
                if (newBook == null) {

                } else {
                     book = (Book) table.getSelectionModel().getSelectedItem();
                }
                table.refresh();
            }
        });


    }

    public void actAdd(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editBook.fxml"));
        EditController ctrl = new EditController(null);
        loader.setController(ctrl);
        stage.setTitle("Main");

        try {
            root = loader.load();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
            stage.setOnHiding(event -> {
                Book book = ctrl.book();
                if (book != null) {
                    base.addBook(book);
                    initialize();
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actEdit(ActionEvent actionEvent) {
        Stage stage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editBook.fxml"));
        EditController ctrl = new EditController(book);
        loader.setController(ctrl);
        stage.setTitle("Main");

        try {
            root = loader.load();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actDelete(ActionEvent actionEvent) {
        table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
    }
}
