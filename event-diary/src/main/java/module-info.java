module ru.eventdiary {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.eventdiary to javafx.fxml;
    opens ru.eventdiary.controllers to javafx.fxml;
    exports ru.eventdiary;
    exports ru.eventdiary.controllers;
}