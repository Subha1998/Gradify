package dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.dbutil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import util.UtilityFunctions;

import java.io.IOException;

public class AddFacultyController {

    @FXML
    private JFXTextField facultyName;

    @FXML
    private JFXTextField facultyLoginID;

    @FXML
    private JFXPasswordField facultyPassword;

    @FXML
    private JFXButton addFaculty;

    @FXML
    private JFXButton dashboard;

    @FXML
    void addFaculty(ActionEvent event) {
        dbutil db = new dbutil();
        db.addFaculty(facultyName.getText(), facultyLoginID.getText(), facultyPassword.getText());
    }

    @FXML
    public void Dashboard(ActionEvent event) throws IOException {
        UtilityFunctions util = new UtilityFunctions();
        FXMLLoader fx = new FXMLLoader(getClass().getResource("FacultyControlPanel.fxml"));
        Stage st = util.getStage(fx, event);
        st.show();
    }

}
