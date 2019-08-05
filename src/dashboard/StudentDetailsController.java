package dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.mongodb.client.MongoIterable;
import database.dbutil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.bson.Document;
import util.UtilityFunctions;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentDetailsController implements Initializable {

    @FXML
    private JFXButton genrep;

    @FXML
    private Label studentName;

    @FXML
    private Label rollNo;

    @FXML
    private Label regNo;

    @FXML
    private JFXButton back;

    @FXML
    private Text gpa;

    @FXML
    private Label batch;

    @FXML
    private Text sub1;

    @FXML
    private Text mid1;

    @FXML
    private Text end1;

    @FXML
    private Text att1;

    @FXML
    private Text cl1;

    @FXML
    private Text tot1;

    @FXML
    private Text sub2;

    @FXML
    private Text mid2;

    @FXML
    private Text end2;

    @FXML
    private Text att2;

    @FXML
    private Text cl2;

    @FXML
    private Text tot2;

    @FXML
    private Text sub3;

    @FXML
    private Text mid3;

    @FXML
    private Text end3;

    @FXML
    private Text att3;

    @FXML
    private Text cl3;

    @FXML
    private Text tot3;

    @FXML
    private Text sub4;

    @FXML
    private Text mid4;

    @FXML
    private Text end4;

    @FXML
    private Text att4;

    @FXML
    private Text cl4;

    @FXML
    private Text tot4;

    @FXML
    private Text sub5;

    @FXML
    private Text mid5;

    @FXML
    private Text end5;

    @FXML
    private Text att5;

    @FXML
    private Text cl5;

    @FXML
    private Text tot5;

    @FXML
    private Text sub6;

    @FXML
    private Text mid6;

    @FXML
    private Text end6;

    @FXML
    private Text att6;

    @FXML
    private Text cl6;

    @FXML
    private Text tot6;

    @FXML
    private Text sub7;

    @FXML
    private Text mid7;

    @FXML
    private Text end7;

    @FXML
    private Text att7;

    @FXML
    private Text cl7;

    @FXML
    private Text tot7;

    @FXML
    private Text sub8;

    @FXML
    private Text mid8;

    @FXML
    private Text end8;

    @FXML
    private Text att8;

    @FXML
    private Text cl8;

    @FXML
    private Text tot8;

    @FXML
    private Text cr1;

    @FXML
    private Text cr2;

    @FXML
    private Text cr3;

    @FXML
    private Text cr4;

    @FXML
    private Text cr5;

    @FXML
    private Text cr6;

    @FXML
    private Text cr7;

    @FXML
    private Text cr8;

    @FXML
    private Text gpashow;


    @FXML
    private JFXComboBox<Label> selectedsem;


    public void initialize(URL url, ResourceBundle rb){
        selectedsem.getItems().add(new Label("Semester 1"));
        selectedsem.getItems().add(new Label("Semester 2"));
        selectedsem.getItems().add(new Label("Semester 3"));
        selectedsem.getItems().add(new Label("Semester 4"));
        selectedsem.getItems().add(new Label("Semester 5"));
        selectedsem.getItems().add(new Label("Semester 6"));
        selectedsem.getItems().add(new Label("Semester 7"));
        selectedsem.getItems().add(new Label("Semester 8"));
    }

    public void populate(String studentName, int regNo, String semester, String rollNo, String batch) {
        this.studentName.setText(studentName);
        this.rollNo.setText(rollNo);
        this.regNo.setText(Integer.toString(regNo));
        this.batch.setText(batch);
        addEntries(regNo,semester);
    }

    private void clearData(){
        sub1.setText("");
        mid1.setText("");
        end1.setText("");
        cl1.setText("");
        att1.setText("");
        cr1.setText("");
        sub2.setText("");
        mid2.setText("");
        end2.setText("");
        cl2.setText("");
        att2.setText("");
        cr2.setText("");
        sub3.setText("");
        mid3.setText("");
        end3.setText("");
        cl3.setText("");
        att3.setText("");
        cr3.setText("");
        sub4.setText("");
        mid4.setText("");
        end4.setText("");
        cl4.setText("");
        att4.setText("");
        cr4.setText("");
        sub5.setText("");
        mid5.setText("");
        end5.setText("");
        cl5.setText("");
        att5.setText("");
        cr5.setText("");
        sub6.setText("");
        mid6.setText("");
        end6.setText("");
        cl6.setText("");
        att6.setText("");
        cr6.setText("");
        sub7.setText("");
        mid7.setText("");
        end7.setText("");
        cl7.setText("");
        att7.setText("");
        cr7.setText("");
        sub8.setText("");
        mid8.setText("");
        end8.setText("");
        cl8.setText("");
        att8.setText("");
        cr8.setText("");
        gpashow.setText("");
        tot1.setText("");
        tot2.setText("");
        tot3.setText("");
        tot4.setText("");
        tot5.setText("");
        tot6.setText("");
        tot7.setText("");
        tot8.setText("");
    }

    public void addEntries(int regNo, String semester) {
        UtilityFunctions u = new UtilityFunctions();
        clearData();
        dbutil db = new dbutil();
        MongoIterable<Document> marks = db.getMarks(semester, regNo);
        int i = 1;
        int TotalCredits = 0;
        int TotalMul = 0;
        for (Document d:marks
             ) {
            System.out.println(d);
            if(i == 1){
                sub1.setText(d.getString("subjectcode"));
                mid1.setText(Integer.toString(d.getInteger("midsem")));
                end1.setText(Integer.toString(d.getInteger("endsem")));
                att1.setText(Integer.toString(d.getInteger("attendance")));
                cl1.setText(Integer.toString(d.getInteger("classtest")));
                tot1.setText(Integer.toString(d.getInteger("midsem")+d.getInteger("endsem")+d.getInteger("attendance")+d.getInteger("classtest")));
                cr1.setText(Integer.toString(d.getInteger("credits")));
                TotalCredits = TotalCredits+d.getInteger("credits");
                TotalMul = TotalMul + u.points(Integer.parseInt(tot1.getText()))*d.getInteger("credits");
                System.out.println(TotalMul);
            }

            if(i == 2){
                sub2.setText(d.getString("subjectcode"));
                mid2.setText(Integer.toString(d.getInteger("midsem")));
                end2.setText(Integer.toString(d.getInteger("endsem")));
                att2.setText(Integer.toString(d.getInteger("attendance")));
                cl2.setText(Integer.toString(d.getInteger("classtest")));
                tot2.setText(Integer.toString(d.getInteger("midsem")+d.getInteger("endsem")+d.getInteger("attendance")+d.getInteger("classtest")));
                cr2.setText(Integer.toString(d.getInteger("credits")));
                TotalCredits = TotalCredits+d.getInteger("credits");
                TotalMul = TotalMul + u.points(Integer.parseInt(tot2.getText()))*d.getInteger("credits");
            }

            if(i == 3){
                sub3.setText(d.getString("subjectcode"));
                mid3.setText(Integer.toString(d.getInteger("midsem")));
                end3.setText(Integer.toString(d.getInteger("endsem")));
                att3.setText(Integer.toString(d.getInteger("attendance")));
                cl3.setText(Integer.toString(d.getInteger("classtest")));
                tot3.setText(Integer.toString(d.getInteger("midsem")+d.getInteger("endsem")+d.getInteger("attendance")+d.getInteger("classtest")));
                cr3.setText(Integer.toString(d.getInteger("credits")));
                TotalCredits = TotalCredits+d.getInteger("credits");
                TotalMul = TotalMul + u.points(Integer.parseInt(tot3.getText()))*d.getInteger("credits");
            }

            if(i == 4){
                sub4.setText(d.getString("subjectcode"));
                mid4.setText(Integer.toString(d.getInteger("midsem")));
                end4.setText(Integer.toString(d.getInteger("endsem")));
                att4.setText(Integer.toString(d.getInteger("attendance")));
                cl4.setText(Integer.toString(d.getInteger("classtest")));
                tot4.setText(Integer.toString(d.getInteger("midsem")+d.getInteger("endsem")+d.getInteger("attendance")+d.getInteger("classtest")));
                cr4.setText(Integer.toString(d.getInteger("credits")));
                TotalCredits = TotalCredits+d.getInteger("credits");
                TotalMul = TotalMul + u.points(Integer.parseInt(tot4.getText()))*d.getInteger("credits");
            }

            if(i == 5){
                sub5.setText(d.getString("subjectcode"));
                mid5.setText(Integer.toString(d.getInteger("midsem")));
                end5.setText(Integer.toString(d.getInteger("endsem")));
                att5.setText(Integer.toString(d.getInteger("attendance")));
                cl5.setText(Integer.toString(d.getInteger("classtest")));
                tot5.setText(Integer.toString(d.getInteger("midsem")+d.getInteger("endsem")+d.getInteger("attendance")+d.getInteger("classtest")));
                cr5.setText(Integer.toString(d.getInteger("credits")));
                TotalCredits = TotalCredits+d.getInteger("credits");
                TotalMul = TotalMul + u.points(Integer.parseInt(tot5.getText()))*d.getInteger("credits");
            }

            if(i == 6){
                sub6.setText(d.getString("subjectcode"));
                mid6.setText(Integer.toString(d.getInteger("midsem")));
                end6.setText(Integer.toString(d.getInteger("endsem")));
                att6.setText(Integer.toString(d.getInteger("attendance")));
                cl6.setText(Integer.toString(d.getInteger("classtest")));
                tot6.setText(Integer.toString(d.getInteger("midsem")+d.getInteger("endsem")+d.getInteger("attendance")+d.getInteger("classtest")));
                cr6.setText(Integer.toString(d.getInteger("credits")));
                TotalCredits = TotalCredits+d.getInteger("credits");
                TotalMul = TotalMul + u.points(Integer.parseInt(tot6.getText()))*d.getInteger("credits");
            }

            if(i == 7){
                sub7.setText(d.getString("subjectcode"));
                mid7.setText(Integer.toString(d.getInteger("midsem")));
                end7.setText(Integer.toString(d.getInteger("endsem")));
                att7.setText(Integer.toString(d.getInteger("attendance")));
                cl7.setText(Integer.toString(d.getInteger("classtest")));
                tot7.setText(Integer.toString(d.getInteger("midsem")+d.getInteger("endsem")+d.getInteger("attendance")+d.getInteger("classtest")));
                cr7.setText(Integer.toString(d.getInteger("credits")));
                TotalCredits = TotalCredits+d.getInteger("credits");
                TotalMul = TotalMul + u.points(Integer.parseInt(tot7.getText()))*d.getInteger("credits");
            }

            if(i == 8){
                sub8.setText(d.getString("subjectcode"));
                mid8.setText(Integer.toString(d.getInteger("midsem")));
                end8.setText(Integer.toString(d.getInteger("endsem")));
                att8.setText(Integer.toString(d.getInteger("attendance")));
                cl8.setText(Integer.toString(d.getInteger("classtest")));
                tot8.setText(Integer.toString(d.getInteger("midsem")+d.getInteger("endsem")+d.getInteger("attendance")+d.getInteger("classtest")));
                cr8.setText(Integer.toString(d.getInteger("credits")));
                TotalCredits = TotalCredits+d.getInteger("credits");
                TotalMul = TotalMul + u.points(Integer.parseInt(tot8.getText()))*d.getInteger("credits");
            }
           i++;
        }

        float cgpa = (float) TotalMul/(float)TotalCredits;
        gpashow.setText(Float.toString(cgpa));
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        UtilityFunctions util = new UtilityFunctions();
        FXMLLoader fx = new FXMLLoader(getClass().getResource("Login.fxml"));
        Stage st = util.getStage(fx, event);
        st.show();
    }

    @FXML
    void genrep(ActionEvent event) throws IOException {
        addEntries(Integer.parseInt(regNo.getText()), selectedsem.getValue().getText());
    }


}
