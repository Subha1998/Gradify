package util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class UtilityFunctions {
    public Stage getStage(FXMLLoader fxmlLoader, ActionEvent event) throws IOException {
        Parent newPane = fxmlLoader.load();
        Scene newScene = new Scene(newPane);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        return window;
    }

    public int points(int x){
        if(x>90){
            return 10;
        }
        else if(x>80){
            return 9;
        }
        else if(x>70){
            return 8;
        }
        else if(x>60){
            return 7;
        }
        else if(x>50){
            return 6;
        }
        else if(x>40){
            return 5;
        }
        else if(x>30){
            return 4;
        }
        else if(x>20){
            return 3;
        }
        else if(x>10){
            return 2;
        }
        else if(x>0){
            return 1;
        }
        else return 0;
    }
}
