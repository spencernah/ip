package duke.gui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;




/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private String dir;
    private Duke duke;


    @Override
    public void start(Stage stage) {
        dir = System.getProperty("user.dir");
        dir = dir + "\\data\\data.txt";
        duke = new Duke(dir);
        //duke = new Duke("C:\\Users\\User\\Documents\\ip\\data\\data.txt");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
