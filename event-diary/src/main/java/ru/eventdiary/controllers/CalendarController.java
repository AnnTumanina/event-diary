package ru.eventdiary.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ru.eventdiary.utils.StageFactory;

import java.io.File;
import java.io.IOException;

/**
 * контроллер сцены календаря
 */
public class CalendarController {

    /**
     * главное окно
     */
    private final Stage primaryStage;

    public CalendarController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * вызывается при нажатии на день
     */
    @FXML
    public void onDayClick(ActionEvent event) {
        int index = Integer.parseInt(((Button) event.getSource()).getText()); // получаем текст из кнопки и превращаем его в число
        try {
            Scene sc = StageFactory.createDayScene(primaryStage, index); // создаем сцену с текстовым полем ввода результата дня
            primaryStage.setScene(sc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * вызывается при нажатии на кнопку Очистить все
     */
    @FXML
    public void onClearAllClick() {
        for (int i = 1; i < 31; i++) {
            File file = new File(DayController.DAYS_TEXT_PATH + File.separator + "day" + i + ".txt");
            if (file.exists()) file.delete();
        }
    }
}
