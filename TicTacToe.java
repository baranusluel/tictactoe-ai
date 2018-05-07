import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Button;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;

public class TicTacToe extends Application {

    static ArrayList<Button> buttons;
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

        Scene scene = new Scene(flow);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.setWidth(300);
        stage.setHeight(320);
        stage.setResizable(false);
        stage.show();

        game = new Game("X");
    }

    private void btnClick(ActionEvent e) {
        if (game.getIsOn()) {
            int pos = buttons.indexOf(e.getSource());
            game.makeMove(pos);
            game.checkForWinner();
            game.switchPlayer();
            if (!game.getIsOn()) {
                // Game over message
            }
        }
    }
}
