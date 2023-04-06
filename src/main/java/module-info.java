module cz.vse.adventura_cipm02 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens cz.vse.adventura_cipm02.main to javafx.fxml;
    exports cz.vse.adventura_cipm02.main;
}