module com.example.myticket {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires org.apache.pdfbox;

    opens com.example.myticket to javafx.fxml;
    exports com.example.myticket;
    exports com.example.myticket.Controller;
    exports com.example.myticket.Model;
    exports com.example.myticket.Connection;
    opens com.example.myticket.Controller to javafx.fxml;
}
