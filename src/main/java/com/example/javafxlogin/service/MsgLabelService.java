package com.example.javafxlogin.service;

import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

public class MsgLabelService {
    public static void succesLabel(Label label, String message) {
        label.setTextFill(Paint.valueOf("#09b531"));
        label.setText(message);
    }

    public static void errorLabel(Label label, String message) {
        label.setTextFill(Paint.valueOf("#ff0400"));
        label.setText(message);
    }
}
