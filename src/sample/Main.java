package sample;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/**
 *
 * Author: Mariel McNeil
 * White Board is a small drawing application, that clears, captures, and saves user's drawings
 */
public class Main extends Application {
     Stage stage = new Stage();
    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Pane parentPane = new Pane();

        Canvas board = new Canvas(primaryScreenBounds.getWidth(),primaryScreenBounds.getHeight());
        final GraphicsContext boardGraphics = board.getGraphicsContext2D();

        Pane childPane = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("White Board");


        Image cursor = new Image("/sample/marker1.png");
        //parentPane.setCursor(Cursor.cursor("C:\\Users\\c1mt4\\Documents\\White Board\\src\\sample\\cursor.jpg"));
       board.setCursor(new ImageCursor(cursor,
                cursor.getWidth() / 2,
                cursor.getHeight() /2));

       board.setOnMousePressed(event ->
               {
                   boardGraphics.beginPath();
                   boardGraphics.moveTo(event.getX(), event.getY());
                   boardGraphics.stroke();
               }
       );
        board.setOnMouseDragged(event ->
                {
                    //Controller.size_of_marker = 12;
                    boardGraphics.lineTo(event.getX(), event.getY());
                    boardGraphics.stroke();
                    boardGraphics.setStroke(Controller.markerInk);
                    boardGraphics.setLineWidth(Controller.size_of_marker);
                }

        );
        board.setOnMouseClicked(event ->
                {
                    if(event.getClickCount()==2)
                    {
                        boardGraphics.clearRect(primaryScreenBounds.getMinX(),primaryScreenBounds.getMinY(),primaryScreenBounds.getWidth(),primaryScreenBounds.getHeight());
                    }

                }
        );

        Light.Distant light = new Light.Distant();
        light.setElevation(100f);

        Lighting lightning = new Lighting();
        lightning.setLight(light);
        lightning.setSurfaceScale(500.0f);



        parentPane.getChildren().addAll(board,childPane);
        Scene scene = new Scene(parentPane, 1000, 800);
        stage.setScene(scene);
        parentPane.setStyle("-fx-background-color: white;");
        parentPane.setEffect(lightning);
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());

        stage.show();

    }




    public static void main(String[] args) {
        launch(args);
    }
}
