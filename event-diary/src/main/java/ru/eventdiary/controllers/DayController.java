package ru.eventdiary.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import ru.eventdiary.utils.StageFactory;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class DayController implements Initializable {
    
    public static final String DAYS_TEXT_PATH = System.getProperty("user.home") + File.separator + "EventDiary";

    private final Stage primaryStage;

    private final Scene calendarScene;
 
    private final int index;

    @FXML
    private Label title;

    @FXML
    private TextArea textarea;

    public DayController(Stage primaryStage, Scene calendarScene, int index) {
        this.primaryStage = primaryStage;
        this.calendarScene = calendarScene;
        this.index = index;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { //вызывается при открытии окна для того чтобы сразу считать значение текстАреа из файла
        title.setText(String.format(title.getText(), index));
        File file = new File(DAYS_TEXT_PATH + File.separator + "day" + index + ".txt");
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) { 
            textarea.setText(reader.lines().collect(Collectors.joining("\n"))); //установка текста, предусмотрение переноса строк
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onClearClick() { 
        textarea.setText("");
    }

    @FXML
    public void onKaomojiClick() { //открытие окна каомоджи
        try {
            Stage stage = StageFactory.createKaomojiStage(primaryStage); // создаем окно
            stage.show(); // открываем его
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onBackClick() { //метод возврат в главное окно
        saveTextToFile();
        primaryStage.setScene(calendarScene);
    }

    private void saveTextToFile() { //сохранение текста при выходе
        if (textarea.getText().isBlank()) return; // если текстовое поле пустое
        File file = new File(DAYS_TEXT_PATH + File.separator + "day" + index + ".txt");
        File parent = file.getParentFile(); // получаем папку, в которой лежит файл
        if (!parent.exists() && !parent.mkdirs()) return; 
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(textarea.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
