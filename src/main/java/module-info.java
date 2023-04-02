module Models {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens Models to javafx.fxml;
    exports Models;
    exports Controllers;
    opens Controllers to javafx.fxml;
    exports Models.ClassHierarchy;
    opens Models.ClassHierarchy to javafx.fxml;
}