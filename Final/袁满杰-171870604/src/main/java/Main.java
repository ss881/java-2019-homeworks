import control.Cardinal;
import exception.CharacterErrorException;
import exception.HuluwaOutOfNumException;
import exception.ZhenfaIDOutOfNumException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {

        primaryStage.setTitle("葫芦娃大战蝎子精");

        final Cardinal game = new Cardinal();
        Scene scene = new Scene(game.getGroup(), 1800, 800);
        scene.getStylesheets().add("MainStyle.css");

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        final Button btn = new Button();
        btn.setText("Begin![B]");
        btn.setPrefWidth(200);
        final Button btn1 = new Button();
        btn1.setText("Save![S]");
        btn1.setPrefWidth(200);

        final Button btn2 = new Button();
        btn2.setText("Load![L]");
        btn2.setPrefWidth(200);

        final Button btn3 = new Button();
        btn3.setText("Exit![E]");
        btn3.setPrefWidth(200);
        final FileChooser fileChooser = new FileChooser();

        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("保存成功");
        alert.setHeaderText(null);
        alert.setContentText("对战记录保存成功！");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    game.init();
                } catch (CharacterErrorException e) {
                    e.printStackTrace();
                } catch (ZhenfaIDOutOfNumException e) {
                    e.printStackTrace();
                } catch (HuluwaOutOfNumException e) {
                    e.printStackTrace();
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        btn.setDisable(true);
                        btn1.setDisable(true);
                        btn2.setDisable(true);

                        Thread temp = new Thread(game);
                        temp.start();
                        try {
                            temp.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            btn1.setDisable(false);
                            btn2.setDisable(false);
                            btn.setDisable(false);
                        }
                    }
                }).start();
            }
        });

        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = fileChooser.showSaveDialog(primaryStage);
                if (file == null)
                    return;
                try {
                    game.save(file);
                    alert.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final File file = fileChooser.showOpenDialog(primaryStage);
                if (file == null)
                    return;
                try {
                    game.init();
                    game.preload(file);
                } catch (CharacterErrorException e) {
                    e.printStackTrace();
                } catch (ZhenfaIDOutOfNumException e) {
                    e.printStackTrace();
                } catch (HuluwaOutOfNumException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        btn.setDisable(true);
                        btn1.setDisable(true);
                        btn2.setDisable(true);

                        try {
                            Thread temp = game.load(file);
                            temp.start();
                            temp.join();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (HuluwaOutOfNumException e) {
                            e.printStackTrace();
                        } catch (CharacterErrorException e) {
                            e.printStackTrace();
                        } catch (ZhenfaIDOutOfNumException e) {
                            e.printStackTrace();
                        }
                        btn.setDisable(false);
                        btn1.setDisable(false);
                        btn2.setDisable(false);
                    }
                }).start();

            }
        });

        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.B) {
                    try {
                        game.init();
                    } catch (CharacterErrorException e) {
                        e.printStackTrace();
                    } catch (ZhenfaIDOutOfNumException e) {
                        e.printStackTrace();
                    } catch (HuluwaOutOfNumException e) {
                        e.printStackTrace();
                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            btn.setDisable(true);
                            btn1.setDisable(true);
                            btn2.setDisable(true);

                            Thread temp = new Thread(game);
                            temp.start();
                            try {
                                temp.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                btn1.setDisable(false);
                                btn2.setDisable(false);
                                btn.setDisable(false);
                            }
                        }
                    }).start();
                }else if (event.getCode() == KeyCode.S){
                    File file = fileChooser.showSaveDialog(primaryStage);
                    if (file == null)
                        return;
                    try {
                        game.save(file);
                        alert.showAndWait();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (event.getCode() == KeyCode.L){
                    final File file = fileChooser.showOpenDialog(primaryStage);
                    if (file == null)
                        return;
                    try {
                        game.init();
                        game.preload(file);
                    } catch (CharacterErrorException e) {
                        e.printStackTrace();
                    } catch (ZhenfaIDOutOfNumException e) {
                        e.printStackTrace();
                    } catch (HuluwaOutOfNumException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            btn.setDisable(true);
                            btn1.setDisable(true);
                            btn2.setDisable(true);

                            try {
                                Thread temp = game.load(file);
                                temp.start();
                                temp.join();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (HuluwaOutOfNumException e) {
                                e.printStackTrace();
                            } catch (CharacterErrorException e) {
                                e.printStackTrace();
                            } catch (ZhenfaIDOutOfNumException e) {
                                e.printStackTrace();
                            }
                            btn.setDisable(false);
                            btn1.setDisable(false);
                            btn2.setDisable(false);
                        }
                    }).start();
                }else if (event.getCode() == KeyCode.E){
                    System.exit(0);

                }
            }
        });
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.getChildren().addAll(btn, btn1, btn2, btn3);
        game.getGroup().getChildren().add(vbox);
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(Main.class, args);
    }
}
