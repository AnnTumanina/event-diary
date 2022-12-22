package ru.eventdiary.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.eventdiary.EventDiaryApplication;
import ru.eventdiary.controllers.CalendarController;
import ru.eventdiary.controllers.DayController;

import java.io.IOException;

public class StageFactory {

    /**
     * создает сцену с календарем
     * @param primaryStage окно на котором мы отобразим сцену
     * @return сцену с календарем
     */
    public static Scene createCalendarScene(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(EventDiaryApplication.class.getResource("calendar.fxml"));
        loader.setController(new CalendarController(primaryStage));
        return new Scene(loader.load(), 600, 400);
    }

    /**
     * создает сцену с полем результата
     * @param primaryStage окно на котором мы отобразим сцену
     * @return сцену с текстовым полем
     */
    public static Scene createDayScene(Stage primaryStage, int index) throws IOException {
        FXMLLoader loader = new FXMLLoader(EventDiaryApplication.class.getResource("day.fxml"));
        loader.setController(new DayController(primaryStage, primaryStage.getScene(), index));
        return new Scene(loader.load(), 600, 400);
    }

    /**
     * создает отдельное окно с эмодзи
     * @param primaryStage главное окно
     * @return окно с эмодзи
     */
    public static Stage createKaomojiStage(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(EventDiaryApplication.class.getResource("kaomoji.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // это будет модальное окно
        stage.initOwner(primaryStage); // владельцем этого окна задаем наше главное окно
        stage.setTitle("Kaomoji");
        stage.setScene(new Scene(loader.load(), 500, 300));
        return stage;
    }

}
