module COMP228_Wk13_Lab_RavindraPatil {
	requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;

    opens application to javafx.graphics, javafx.fxml;
    exports application;
}
