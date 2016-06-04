package gui;/**
 * Created by KVN on 28/05/2016.
 */

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ProcessLauncherApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage)  throws Exception {

        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui.fxml"));
        final Parent root = (Parent) loader.load();
        final ApplicationViewController controller = loader.<ApplicationViewController>getController();

        Scene scene = new Scene(root);

        stage.setTitle("Genio Remote Launcher");
        stage.setScene(scene);
        stage.show();
    }

    /** small helper class for handling tree loading events. */
    private class TreeLoadingEventHandler implements EventHandler<ActionEvent> {
        private ApplicationViewController controller;
        private int idx = 0;

        TreeLoadingEventHandler(ApplicationViewController controller) {
            this.controller = controller;
        }

        public void handle(ActionEvent t) {
            controller.loadTreeItems("Loaded " + idx, "Loaded " + (idx + 1), "Loaded " + (idx + 2));
            idx += 3;
        }
    }
}
