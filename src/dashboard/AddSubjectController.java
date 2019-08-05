package dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import database.dbutil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.UtilityFunctions;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddSubjectController implements Initializable {

    @FXML
    private JFXTextField subjectName;

    @FXML
    private JFXTextField sujectCode;

    @FXML
    private JFXTextField credits;

    @FXML
    private JFXComboBox<Label> batch;

    @FXML
    private JFXComboBox<Label> semester;

    @FXML
    private JFXButton addSubject;

    @FXML
    private JFXButton dashboard;

    public void initialize(URL url, ResourceBundle rb){
        semester.getItems().add(new Label("Semester 1"));
        semester.getItems().add(new Label("Semester 2"));
        semester.getItems().add(new Label("Semester 3"));
        semester.getItems().add(new Label("Semester 4"));
        semester.getItems().add(new Label("Semester 5"));
        semester.getItems().add(new Label("Semester 6"));
        semester.getItems().add(new Label("Semester 7"));
        semester.getItems().add(new Label("Semester 8"));

        batch.getItems().add(new Label("2015"));
        batch.getItems().add(new Label("2016"));
        batch.getItems().add(new Label("2017"));
        batch.getItems().add(new Label("2018"));
    }

    @FXML
    void Dashboard(ActionEvent event) throws IOException {
        UtilityFunctions util = new UtilityFunctions();
        FXMLLoader fx = new FXMLLoader(getClass().getResource("FacultyControlPanel.fxml"));
        Stage st = util.getStage(fx, event);
        st.show();
    }

    @FXML
    void addSubjectToBatch(ActionEvent event) throws IOException {
        dbutil db = new dbutil();
        db.addSubject(sujectCode.getText(), batch.getValue().getText(), subjectName.getText(), semester.getValue().getText(), Integer.parseInt(credits.getText()));
    }

}
