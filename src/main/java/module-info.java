module com.example.bookstoreapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires okhttp3;
    requires json;
    requires org.apache.pdfbox;
    requires java.sql;
    requires java.mail;
    requires activation;


    opens com.example.bookstoreapp to javafx.fxml;
    exports com.example.bookstoreapp;
}