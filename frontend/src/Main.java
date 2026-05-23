import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import ui.Dashboard;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Dashboard dashboard =
                new Dashboard();

        Scene scene =
                new Scene(
                        dashboard,
                        1600,
                        900
                );

        // LOAD CSS

        scene.getStylesheets().add(
                getClass()
                        .getResource(
                                "/style/style.css"
                        )
                        .toExternalForm()
        );

        stage.setTitle(
                "Hospital Management System"
        );

        stage.setScene(scene);

        // FULLSCREEN

        stage.setMaximized(true);

        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}