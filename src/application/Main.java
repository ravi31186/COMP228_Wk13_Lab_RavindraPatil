package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        TextField txtMin = new TextField();
        txtMin.setPromptText("Enter Minimum Number");

        TextField txtMax = new TextField();
        txtMax.setPromptText("Enter Maximum Number");

        TextField txtQty = new TextField();
        txtQty.setPromptText("Enter Quantity of Balls");

        TextArea area = new TextArea();
        Button btn = new Button("Generate Lotto");

        btn.setOnAction(event -> {

            area.clear();

            int min = Integer.parseInt(txtMin.getText());
            int max = Integer.parseInt(txtMax.getText());
            int qty = Integer.parseInt(txtQty.getText());

            StringBuilder sb = new StringBuilder();
            LottoGenerator gen = new LottoGenerator(min, max);

            // create threads
            Thread[] list = new Thread[qty];

            for (int i = 0; i < qty; i++) {
                list[i] = new LottoThread(i + 1, gen, sb);
                list[i].start();
            }

            // wait for all threads to finish
            Thread waitThread = new Thread(() -> {
                try {
                    for (int i = 0; i < qty; i++) {
                        list[i].join();
                    }

                    Platform.runLater(() -> {
                        area.setText(sb.toString());
                        area.appendText("Numbers saved to database.");
                    });

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            waitThread.start();
        });

        VBox root = new VBox(10,
                new Label("Minimum:"), txtMin,
                new Label("Maximum:"), txtMax,
                new Label("Quantity:"), txtQty,
                btn, area);

        stage.setScene(new Scene(root, 350, 400));
        stage.setTitle("Simple Lotto App");
        stage.show();
    }

    public static void main(String[] args) { launch(); }
}
