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

public class TicTacToe extends Application {

    static ArrayList<Button> buttons;
    private static Text text;
    private Game game;

    @Override
    public void start(Stage stage) {
        buttons = new ArrayList<Button>(Arrays.asList(
            new Button(), new Button(), new Button(),
            new Button(), new Button(), new Button(),
            new Button(), new Button(), new Button()));
        buttons.forEach(b -> {
                b.setMinWidth(80);
                b.setMinHeight(80);
                b.setOnAction(this::btnClick);
            });
        FlowPane flow = new FlowPane();
        flow.getChildren().addAll(buttons);
        flow.setVgap(10);
        flow.setHgap(10);
        flow.setAlignment(javafx.geometry.Pos.CENTER);

        text = new Text("Player X's turn");
        text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(flow, text);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        vbox.setSpacing(20);

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.setWidth(340);
        stage.setHeight(380);
        stage.setResizable(false);
        stage.show();

        game = new Game("X");
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
