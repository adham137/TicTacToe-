package ticTacToe;


import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TicTacToeApplication extends Application {

    private String whoseTurn= "X";
    private ArrayList<Button> buttons= new ArrayList<>();



    public void start(Stage window){


        BorderPane mainPain = new BorderPane();
        Label turn = new Label("Turn: "+whoseTurn);
        turn.setFont(Font.font("Monospaced", 50));
        turn.setPadding(new Insets(20, 20, 20, 75));

        GridPane secondaryPane = new GridPane();

        //creation of buttons and adding them to the pane and to arrayList buttons
        for(int r=0; r<3 ; r++){
            for(int c=0 ; c<3 ; c++){
                Button newButton = new Button(" ");
                newButton.setFont(Font.font("Monospaced", 100));
                secondaryPane.add(newButton,c,r);
                buttons.add(newButton);

            }
        }

        secondaryPane.setPrefSize(300,600);
        secondaryPane.setAlignment(Pos.CENTER);

        mainPain.setTop(turn);
        mainPain.setCenter(secondaryPane);

        mainPain.setPrefSize(800,800);
        mainPain.setPadding(new Insets(20, 20, 20, 20));


        for(Button pressedButton : buttons){

            pressedButton.setOnAction((event)->{
                //if the button pressed is not assigned a value (either an X or an O) , set the value of the button
                //and check for a winner , if there is none , change the turns and continue

                if(pressedButton.getText().equals(" ")){
                    pressedButton.setText(whoseTurn);

                    if(thereIsAWinner(buttonsToStrings())){
                        buttons.stream().forEach((b)->{
                            b.setDisable(true);
                        });
                        turn.setText("The end!");
                    }else {
                        changeTurn();

                        turn.setText("Turn: " + whoseTurn);
                    }

                }


            });
        }

        Scene scene = new Scene(mainPain);
        window.setTitle("X-O Game");
        window.setScene(scene);
        window.show();

    }
   //changes turn from X to O and vice versa
    private void changeTurn(){
        if(whoseTurn.equals("X")){
            whoseTurn = "O";
        }else{
            whoseTurn = "X";
        }
    }

    //turns the values of the buttons into an array of strings
    private String[][] buttonsToStrings(){
        String[][] arrOfButtons = new String[3][3];
        int i = 0;
        for(int r=0 ; r<3 ; r++){
            for(int c=0 ; c<3 ; c++ ){
                if(buttons.get(i).getText().equals("X")||buttons.get(i).getText().equals("O")){
                    arrOfButtons[r][c] = buttons.get(i).getText();
                }

                else{
                    arrOfButtons[r][c] = "w"+i;
                }

                i++;
            }
        }
        return arrOfButtons;
    }

    //checks the columns , rows and diagonals for a match and thus a winner
    private boolean thereIsAWinner(String[][] buttons){
        if(checkRows(buttons)==true||checkColumns(buttons)==true||checkDiagonals(buttons)==true){
            return true;
        }
        return false;
    }

    private boolean checkRows(String[][] buttons){
        int c = 0;
        for(int r=0 ; r<3 ; r++){
            if(buttons[r][c]==buttons[r][c+1]&&buttons[r][c+1]==buttons[r][c+2]){
                return true;
            }
        }
        return false;
    }
    private boolean checkColumns(String[][] buttons){
        int r = 0;
        for(int c=0 ; c<3 ; c++){
            if(buttons[r][c]==buttons[r+1][c]&&buttons[r+1][c]==buttons[r+2][c]){
                return true;
            }
        }
        return false;
    }
    private boolean checkDiagonals(String[][] buttons){
        if(buttons[0][0]==buttons[1][1]&&buttons[1][1]==buttons[2][2]){
            return true;
        }
        if(buttons[0][2]==buttons[1][1]&&buttons[1][1]==buttons[2][0]){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {

        launch(TicTacToeApplication.class);

    }

}
