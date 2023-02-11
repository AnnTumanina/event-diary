package ru.eventdiary;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.eventdiary.utils.StageFactory;

import java.io.IOException;

public class EventDiaryApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = StageFactory.createCalendarScene(stage);
        stage.setTitle("Календарь заметок");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
