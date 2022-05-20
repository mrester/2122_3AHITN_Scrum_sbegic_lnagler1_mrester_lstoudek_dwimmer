package com.example.scrum;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller_Shot_Keyboard_Input {
    @FXML
    private TextField shotInputFieldX;

    @FXML
    private TextField shotInputFieldY;

    @FXML
    private Button shotConfirmBTN;

    @FXML
    private Button fireShotBTN;

    @FXML
    private Button resetPosBTN;

    ShotKeyBoardInput kbi = null;

    @FXML
    protected void confirmShotPosition() {
        try {
            do {
                kbi = new ShotKeyBoardInput(Integer.parseInt(shotInputFieldX.getText()), Integer.parseInt(shotInputFieldY.getText()));
            } while (kbi.getShotPos()[0] == Integer.parseInt(null) || kbi.getShotPos()[1] == Integer.parseInt(null));
        } catch (RuntimeException e) {
        }

        System.out.println(kbi.getShotPos()[0]);
        System.out.println(kbi.getShotPos()[1]);

        shotConfirmBTN.setDisable(true);
        fireShotBTN.setDisable(false);
        resetPosBTN.setDisable(false);
    }

    @FXML
    protected void fireShot() {
        fireShotBTN.setDisable(true);
        resetPosBTN.setDisable(true);
        System.out.println("Shot at -> " + kbi.getShotPos()[0] + " : " + kbi.getShotPos()[1]);
        System.out.println("Boomm!!!");
    }

    @FXML
    protected void resetPosInput(){
        fireShotBTN.setDisable(true);
        resetPosBTN.setDisable(true);
        shotConfirmBTN.setDisable(false);
        shotInputFieldX.clear();
        shotInputFieldY.clear();
    }
}