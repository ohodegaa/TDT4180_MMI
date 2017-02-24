package Oving_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Wordfeud extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Wordfeud");
        StackPane root = new StackPane();
        primaryStage.setScene(new Scene(root, 350, 350));
        int columns = 7;
        int rows = 7;


        GridPane grid = new GridPane();
        for (int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints(50);
            grid.getColumnConstraints().add(column);
        }

        for (int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(50);
            grid.getRowConstraints().add(row);
        }
        String picPath = "";

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                boolean addPic = false;


                if ((x == 2 && y == 0) || (x == 3 && y == 3) || (x == 6 && y == 4)) {
                    addPic = true;
                    picPath = "/Oving_1/img/02.jpeg";
                }

                else if (x == 6 && y == 0){
                    addPic = true;
                    picPath = "/Oving_1/img/06.jpeg";
                }
                else if ((x == 1 && y == 1) || (x == 3 && y == 1) || (x == 5 && y == 3) || (x == 0 && y == 6)) {
                    addPic = true;
                    picPath = "/Oving_1/img/11.jpeg";
                }
                else if ((x == 0 && y == 2) || (x == 4 && y == 2) || (x == 2 && y == 4) || (x == 4 && y == 6)) {
                    addPic = true;
                    picPath = "/Oving_1/img/20.jpeg";
                }
                else if ((x == 1 && y == 5) || (x == 5 && y == 5)){
                    addPic = true;
                    picPath = "/Oving_1/img/R.jpeg";
                }
                else if (x == 2 && y == 5){
                    addPic = true;
                    picPath = "/Oving_1/img/I.jpeg";
                }
                else if (x == 3 && y == 5){
                    addPic = true;
                    picPath = "/Oving_1/img/V.jpeg";
                }
                else if (x == 4 && y == 5){
                    addPic = true;
                    picPath = "/Oving_1/img/E.jpeg";
                }
                if (addPic) {
                    Image image = new Image(picPath);
                    ImageView pic = new ImageView();
                    pic.setFitWidth(50);
                    pic.setFitHeight(50);
                    pic.setImage(image);
                    HBox hb = new HBox();
                    hb.getChildren().add(pic);
                    grid.add(pic, x, y);
                }
                else {
                    Pane pane = new Pane();
                    pane.setStyle("-fx-background-color: #222222;" +
                            "-fx-progress-color: black;" +
                            "-fx-border-radius: 5%;" +
                            "-fx-border-color: black;" +
                            "-fx-border-width: 2");
                    grid.add(pane, x, y);
                }

            }
        }
        grid.setStyle("-fx-background-color: black");

        root.getChildren().add(grid);


        primaryStage.show();
    }


}
