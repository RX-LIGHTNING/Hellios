module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires org.apache.pdfbox;
    requires org.apache.commons.compress;
    requires java.desktop;
    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}