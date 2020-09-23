package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EditController {

    public TextField fdAuthor;
    public TextField fdTitle;
    public ChoiceBox choiceLibrary;
    public ChoiceBox choicePublisher;
    public ChoiceBox choiceCategory;
    private Book book;
    private static ClassDaoBase model;

    public EditController(Book book){
        this.book = book;
        model = ClassDaoBase.getInstance();
    }

    public void initialize() throws SQLException {
        choiceLibrary.setItems(model.libraries());
        choiceCategory.setItems(model.categories());
        choicePublisher.setItems(model.publishers());

        if(book != null){
            fdAuthor.setText(book.getAuthor());
            fdTitle.setText(book.getTitle());
        }
    }

    public void actOk(ActionEvent actionEvent) {
    }

    public void actCancel(ActionEvent actionEvent) {
    }
}
