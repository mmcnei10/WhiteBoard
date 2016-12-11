package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Controller {

    static Color markerInk = Color.BLACK;
    static int size_of_marker = 12;



    @FXML
    Button saveAs = new Button();

    @FXML
    Label markerL = new Label();
    @FXML
    ColorPicker markerP = new ColorPicker();
    @FXML
    HBox markerTool = new HBox();
    @FXML
    Button eraser = new Button();
    @FXML
    Pane pane = new Pane();



    public void handleButtonAction() {

        File file = new File("C:\\users\\c1mt4\\documents\\White Board Creations");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }}


            File screenshot = new File("C:/users/c1mt4/documents/White Board Creations/sample.png");

            WritableImage snapshot = saveAs.getScene().snapshot(null);



            try {
                ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png",screenshot);
                Stage dialog = new Stage();
                dialog.setResizable(false);
                dialog.initStyle(StageStyle.UTILITY);
                Label text = new Label("Saved in documents under White Board Creations!");
                text.setStyle("  -fx-font-size: 20pt;\n" +
                        "    -fx-font-family: \"Segoe UI Light\";\n" +
                        "    -fx-text-fill: black;\n" +
                        "    -fx-opacity: 1;");
                Button close = new Button("Got it!");
                close.setStyle("  -fx-background-color: \n" +
                       "linear-gradient(#f2f2f2, #d6d6d6),\n" +
                                "        linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%),\n" +
                                "        linear-gradient(#dddddd 0%, #f6f6f6 50%);\n"+
                        "    -fx-background-insets: 0,1,4,5;\n" +
                        "    -fx-background-radius: 9,8,5,4;\n" +
                        "    -fx-padding: 15 30 15 30;\n" +
                        "    -fx-font-family: \"Helvetica\";\n" +
                        "    -fx-font-size: 18px;\n" +
                        "    -fx-text-fill: #333333;\n" +
                        "   -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
                close.setOnMouseClicked(event ->
                        {
                           dialog.close();
                            markerP.setVisible(true);
                            saveAs.setVisible(true);
                            eraser.setVisible(true);
                            markerL.setVisible(true);

                        }
                );



                markerP.setVisible(false);
                saveAs.setVisible(false);
                eraser.setVisible(false);
                markerL.setVisible(false);

                StackPane dialogPane = new StackPane();


                VBox h = new VBox(text);
                VBox z = new VBox(close);
                z.setAlignment(Pos.BOTTOM_CENTER);
                dialogPane.getChildren().addAll(h,z);


                Scene scene = new Scene(dialogPane,600,120);
                dialog.setScene(scene);
                dialog.show();
            } catch (IOException e) {
                System.out.println("Unable to take screenshot");

                // TODO: handle exception here
            }












    }

    public void changemarkerInk()
    {
                    markerInk = markerP.getValue();



    }


    public void lambdaEvent()
    {

        pane.setOnKeyTyped(event ->
                {
                    if(event.getCharacter().equals("h")||event.getCharacter().equals("H"))
                    {

                        markerP.setVisible(false);
                        saveAs.setVisible(false);
                        eraser.setVisible(false);
                        markerL.setVisible(false);



                    }
                    else if(event.getCharacter().equals("s")||event.getCharacter().equals("S"))
                    {

                        markerP.setVisible(true);
                        saveAs.setVisible(true);
                        eraser.setVisible(true);
                        markerL.setVisible(true);

                    }

                }

        );
    }
    public void erase()
    {
        size_of_marker= 67;
        markerInk = Color.WHITE;


    }






}
