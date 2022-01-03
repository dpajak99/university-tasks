module pl.pwsztar {
    requires javafx.controls;
    requires javafx.web;
    requires javafx.fxml;
    requires java.sql;
    requires json;
    requires java.mail;

    opens pl.pwsztar to javafx.fxml;
    exports pl.pwsztar;
    exports pl.pwsztar.model;
    exports pl.pwsztar.services;
    exports pl.pwsztar.utils;
}