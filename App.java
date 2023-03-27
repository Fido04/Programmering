import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    private static final int BOARD_SIZE = 3;
    private static final int BUTTON_SIZE = 100;

    private Button[][] buttons = new Button[BOARD_SIZE][BOARD_SIZE];
    private boolean playerX = true;

    @Override
    public void start(Stage primaryStage) {
        GridPane board = new GridPane();
        board.setAlignment(Pos.CENTER);
        board.setVgap(10);
        board.setHgap(10);

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Button button = new Button("");
                button.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
                button.setFont(Font.font("Arial", FontWeight.BOLD, 50));
                button.setOnAction(event -> {
                    if (button.getText().isEmpty()) {
                        if (playerX) {
                            button.setText("X");
                        } else {
                            button.setText("O");
                        }
                        playerX = !playerX;
                        if (checkForWin()) {
                            announceWinner(primaryStage);
                        } else if (checkForDraw()) {
                            announceDraw(primaryStage);
                        }
                    }
                });
                buttons[row][col] = button;
                board.add(button, col, row);
            }
        }

        Scene scene = new Scene(board, 330, 330);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean checkForWin() {
        //rader
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (!buttons[row][0].getText().isEmpty() &&
                    buttons[row][0].getText().equals(buttons[row][1].getText()) &&
                    buttons[row][0].getText().equals(buttons[row][2].getText())) {
                return true;
            }
        }

        //kolumner
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (!buttons[0][col].getText().isEmpty() &&
                    buttons[0][col].getText().equals(buttons[1][col].getText()) &&
                    buttons[0][col].getText().equals(buttons[2][col].getText())) {
                return true;
            }
        }

       //diagonala
        if (!buttons[0][0].getText().isEmpty() &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText())) {
            return true;
        }
        if (!buttons[0][2].getText().isEmpty() &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText())) {
            return true;
        }

        return false;
    }

    private boolean checkForDraw() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void announceWinner(Stage primaryStage) {
        String winner = playerX ? "O" : "X";
        Text message = new Text("Player " + winner + " wins!");
        message.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        StackPane root = new StackPane(message);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 200, 100);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void announceDraw(Stage primaryStage) {
        Text message = new Text("It's a draw!");
        message.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        StackPane root = new StackPane(message);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 200, 100);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}