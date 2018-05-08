import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import javafx.application.Platform;

public class TicTacToe extends Application {

    static ArrayList<Button> buttons;
    private static Text text;
    private Game game;

    @Override
    public void start(Stage stage) {
        boolean multiplayer = false;

        Alert menu = new Alert(Alert.AlertType.CONFIRMATION);
        menu.setGraphic(null);
        menu.setTitle("Tic Tac Toe");
        menu.setHeaderText("What would you like to play?");
        menu.setContentText("Select one of the following modes");
        ButtonType btnSingle = new ButtonType("Player vs AI");
        ButtonType btnMulti = new ButtonType("Player vs Player");
        ButtonType btnQuit = new ButtonType("Quit", ButtonData.CANCEL_CLOSE);
        menu.getButtonTypes().setAll(btnSingle, btnMulti, btnQuit);

        Optional<ButtonType> result = menu.showAndWait();
        if (!result.isPresent()) {
            return;
        }
        if (result.get() == btnMulti) {
            multiplayer = true;
        }

        buttons = new ArrayList<Button>(Arrays.asList(
            new Button(), new Button(), new Button(),
            new Button(), new Button(), new Button(),
            new Button(), new Button(), new Button()));
        buttons.forEach(b -> {
                b.setMinWidth(80);
                b.setMinHeight(80);
                b.setStyle("-fx-background-color: #b7b7b7;" +
                        "-fx-text-fill: #3a5368;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font: 28px 'Sans-serif';");
                b.setOnAction(this::btnClick);
            });
        FlowPane flow = new FlowPane();
        flow.getChildren().addAll(buttons);
        flow.setVgap(10);
        flow.setHgap(10);
        flow.setAlignment(javafx.geometry.Pos.CENTER);

        text = new Text("Player X's turn");
        text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text.setStyle("-fx-fill: white;" +
                "-fx-font: 16px 'Monospace';");

        VBox vbox = new VBox();
        vbox.getChildren().addAll(flow, text);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        vbox.setSpacing(20);
        vbox.setStyle("-fx-background-color: #474747;");

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.setWidth(340);
        stage.setHeight(380);
        stage.setResizable(false);
        stage.show();

        if (multiplayer) {
            game = new Game("X");
        } else {
            // single player mode
            Platform.exit();
        }
    }

    private void btnClick(ActionEvent evt) {
        if (game.getIsOn()) {
            int pos = buttons.indexOf(evt.getSource());
            try {
                game.makeMove(pos);
            } catch (CellAlreadyTakenException e) {
                showText("That cell was already taken!");
                return;
            }
            game.checkForWinner();
            if (game.getIsOn())
                game.switchPlayer();
        }
    }

    public static void showText(String s) {
        text.setText(s);
    }
}
