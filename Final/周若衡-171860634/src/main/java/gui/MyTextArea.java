package gui;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class MyTextArea extends TextArea {
    public MyTextArea(){
        super();
    }
    public  void appendTextArea(final String str){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                appendText(str);
            }
        });
    }
}
