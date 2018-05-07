import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

public class TicTacToe extends Application {

    @Override
    public void start(Stage stage) {
        FlowPane flow = new FlowPane();
        flow.setVgap(10);
        flow.setHgap(10);
        flow.setPrefWrapLength(300);

        Scene scene = new Scene(flow);
        stage.setScene(scene);
        stage.setTitle("Tic-Tac-Toe");
        stage.setWidth(300);
        stage.setHeight(300);
        stage.show();
    }

    //human vs human
    private static void multiplayer() {
        Scanner scan = new Scanner(System.in);
        Game game = new Game("X");

        while (game.getIsOn()) {
            System.out.print("\033[H\033[2J");
            game.getBoard().print();
            boolean validMove = false;
            while (!validMove) {
                game.getBoard().printDemo();
                System.out.println("Your move(0-8): ");
                String pos = scan.nextLine();
                try {
                    game.makeMove(pos);
                    validMove = true;
                } catch (Exception e) {
                    System.out.print("\033[H\033[2J");
                    game.getBoard().print();
                    System.out.println("Invalid Input. Enter a number 0-8");
                }
            }
            game.checkForWinner();
            game.switchPlayer();
        }
    }

    //human vs computer
    private static void singleplayer() {

    }


    /*public static void main(String[] args) {
        multiplayer();
    }*/
}
