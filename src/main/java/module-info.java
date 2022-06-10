module com.example.scrum {
    requires javafx.controls;
    requires javafx.fxml;
    requires soundPlay;
    requires jdk.compiler;


    opens com.example.scrum to javafx.fxml;
    exports com.example.scrum;
    //opens Controller to javafx.fxml;
}