package dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.dbutil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import util.UtilityFunctions;

import java.io.IOException;

public class AddStudentController {

    @FXML
    private JFXTextField studentName;

    @FXML
    private JFXTextField studentRollNo;

    @FXML
    private JFXTextField studentBatchNo ;

    @FXML
    private JFXTextField studentRegNo;

    @FXML
    private JFXButton addStudent;

    @FXML
    void addStudent(ActionEvent event) {
        dbutil db = new dbutil();
        db.addStudent(studentName.getText(),studentBatchNo.getText(), studentRollNo.getText(),Integer.parseInt(studentRegNo.getText()));
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        UtilityFunctions util = new UtilityFunctions();
        FXMLLoader fx = new FXMLLoader(getClass().getResource("FacultyControlPanel.fxml"));
        Stage st = util.getStage(fx, event);
        st.show();
    }

}
