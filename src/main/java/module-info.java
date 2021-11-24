module com.example.demo {
    requires javafx.fxml;
    requires javafx.controls;
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