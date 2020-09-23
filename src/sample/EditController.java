package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;

public class EditController {

    public TextField fdAuthor;
    public TextField fdTitle;
    public TextField fdIsbn;
    public ChoiceBox<String> choiceLibrary;
    public ChoiceBox<String> choicePublisher;
    public ChoiceBox<String> choiceCategory;
    private Book book;
    private static ClassDaoBase model;

    public EditController(Book book){
        this.book = book;
        model = ClassDaoBase.getInstance();
    }

    public Book book()  {
        return book;
    }

    public void initialize() throws SQLException {
        choiceLibrary.setItems(model.libraries());
        choiceCategory.setItems(model.categories());
        choicePublisher.setItems(model.publishers());

        if(book != null){
            fdAuthor.setText(book.getAuthor());
            fdTitle.setText(book.getTitle());
            choiceLibrary.setValue(book.getLibraryId().getBuildingName());
            choiceCategory.setValue(book.getCategoryId().getName());
            choicePublisher.setValue(book.getPublisherId().getName());
            fdIsbn.setText(String.valueOf(book.getIsbn()));
        }
    }

    public void actOk(ActionEvent actionEvent) {
            if (book == null) book = new Book();
            if(fdAuthor.getText().length() != 0 && fdTitle.getText().length() != 0 && choicePublisher.getValue() != null && choiceCategory.getValue() != null && choiceLibrary.getValue() != null) {
                try {
                    book = new Book(Integer.parseInt(fdIsbn.getText()),fdAuthor.getText(),fdTitle.getText(),model.getLibrariesName(choiceLibrary.getValue().toString()),model.getPublisherName(choicePublisher.getValue().toString()),model.getCategorieName(choiceCategory.getValue().toString()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Stage stage = (Stage) fdTitle.getScene().getWindow();
                stage.close();
            }
    }

    public void actCancel(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }


}
