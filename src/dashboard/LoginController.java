package dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.client.MongoIterable;
import database.dbutil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.bson.Document;
import util.UtilityFunctions;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
        @FXML
        private AnchorPane rootPane;

        @FXML
        private JFXTextField loginID;

        @FXML
        private JFXPasswordField paasword;

        @FXML
        private JFXButton submitButton;

        @FXML
        private JFXTextField studentName;

        @FXML
        private JFXComboBox<Label> semester;

        @FXML
        private JFXButton viewResult;

        @FXML
        private JFXTextField regNo;

        public void initialize(URL url, ResourceBundle rb){
            semester.getItems().add(new Label("Semester 1"));
            semester.getItems().add(new Label("Semester 2"));
            semester.getItems().add(new Label("Semester 3"));
            semester.getItems().add(new Label("Semester 4"));
            semester.getItems().add(new Label("Semester 5"));
            semester.getItems().add(new Label("Semester 6"));
            semester.getItems().add(new Label("Semester 7"));
            semester.getItems().add(new Label("Semester 8"));
        }

        @FXML
        void login(ActionEvent event) throws IOException {
            dbutil db = new dbutil();
            UtilityFunctions util = new UtilityFunctions();
            if(db.canLogin(loginID.getText(), paasword.getText())) {
                FXMLLoader fx = new FXMLLoader(getClass().getResource("FacultyControlPanel.fxml"));
                Stage st = util.getStage(fx, event);
                FacultyControlPanelController ctlr = fx.getController();
                ctlr.updatename(db.canLoginStr(loginID.getText(), paasword.getText()));
                st.show();
            }

        }

        @FXML
        void viewResult(ActionEvent event) throws IOException{
            UtilityFunctions util = new UtilityFunctions();
            FXMLLoader fx = new FXMLLoader(getClass().getResource("StudentDetails.fxml"));
            Stage st = util.getStage(fx, event);
            StudentDetailsController ctlr = fx.getController();
            dbutil db = new dbutil();
            MongoIterable<Document> student = db.getStudentDetails(Integer.parseInt(regNo.getText()));
            int i = 0;
            for (Document d: student) {
                i++;
                ctlr.populate(d.getString("name"), d.getInteger("regno"), semester.getValue().getText(), d.getString("rollno"), d.getString("batchno"));
            }
                st.show();
        }
    }