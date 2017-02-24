package Oving_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by olehakon95 on 22/02/2017.
 */
public class AppointmentView extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("New Appointment");
        Parent root = FXMLLoader.load(getClass().getResource("appointment.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args ) {
        Application.launch(args);
    }
}
