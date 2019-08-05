package dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableRow;
import com.mongodb.client.MongoIterable;
import database.dbutil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.bson.Document;
import util.UtilityFunctions;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FacultyControlPanelController implements Initializable {
    @FXML
    private JFXTextField textFeild4;

    @FXML
    private JFXTextField textFeild5;

    @FXML
    private JFXTextField textFeild6;

    @FXML
    private JFXTextField textFeild7;

    @FXML
    private JFXButton update;

    @FXML
    private JFXButton addTeacher;

    @FXML
    private JFXButton addSubject;

    @FXML
    private JFXButton findStudent;

    @FXML
    private JFXButton logOut;

    @FXML
    private JFXTextField regnoToSearch;

    @FXML
    private JFXTextField subcode;

    @FXML
    private Text stnm;

    @FXML
    private Text reg;

    @FXML
    private Text sm;

    @FXML
    private Text sc;

    @FXML
    private Text wel;

    public void initialize(URL url, ResourceBundle rb){
        wel.setText("");
    }

    @FXML
    public void AddTeacher(ActionEvent event) throws IOException {
        UtilityFunctions util = new UtilityFunctions();
        FXMLLoader fx = new FXMLLoader(getClass().getResource("AddFaculty.fxml"));
        Stage st = util.getStage(fx, event);
        st.show();
    }

    @FXML
    void LogOut(ActionEvent event) throws IOException{
        UtilityFunctions util = new UtilityFunctions();
        FXMLLoader fx = new FXMLLoader(getClass().getResource("Login.fxml"));
        Stage st = util.getStage(fx, event);
        st.show();
    }

    @FXML
    void AddSubject(ActionEvent event) throws IOException{
        UtilityFunctions util = new UtilityFunctions();
        FXMLLoader fx = new FXMLLoader(getClass().getResource("AddSubject.fxml"));
        Stage st = util.getStage(fx, event);
        st.show();
    }

    @FXML
    void AddStudent(ActionEvent event) throws IOException{
        UtilityFunctions util = new UtilityFunctions();
        FXMLLoader fx = new FXMLLoader(getClass().getResource("AddStudent.fxml"));
        Stage st = util.getStage(fx, event);
        st.show();
    }

    @FXML
    void populateStudent(ActionEvent event) throws IOException{
        dbutil db = new dbutil();
        MongoIterable<Document> studdet = db.getStudentDetails(Integer.parseInt(regnoToSearch.getText()));
        for (Document d:studdet
             ) {
            stnm.setText(d.getString("name"));
        }
        MongoIterable<Document> marks = db.getMarkssub(subcode.getText(),Integer.parseInt(regnoToSearch.getText()));
        for (Document d:marks
        ) {
            sm.setText(d.getString("semester"));
            sc.setText(d.getString("subjectcode"));
            reg.setText(Integer.toString(d.getInteger("regno")));
            textFeild4.setText(Integer.toString(d.getInteger("midsem")));
            textFeild5.setText(Integer.toString(d.getInteger("endsem")));
            textFeild6.setText(Integer.toString(d.getInteger("classtest")));
            textFeild7.setText(Integer.toString(d.getInteger("attendance")));
        }
    }

    @FXML
    void updateMarks(ActionEvent event) throws IOException{
        dbutil db = new dbutil();
        db.addMarks(Integer.parseInt(reg.getText()), sc.getText(), Integer.parseInt(textFeild4.getText()), Integer.parseInt(textFeild5.getText()), Integer.parseInt(textFeild6.getText()), Integer.parseInt(textFeild7.getText()));

        }

    void updatename(String s) {
        wel.setText(s);
        }
    }
