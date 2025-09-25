module lk.ijse.driving_school_orm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;

    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.desktop;
    requires jbcrypt;

    opens lk.ijse.driving_school_orm.controller to javafx.fxml;
    opens lk.ijse.driving_school_orm.view.tdm to javafx.base;
    opens lk.ijse.driving_school_orm.config to jakarta.persistence;
    opens lk.ijse.driving_school_orm.entity to org.hibernate.orm.core;

    exports lk.ijse.driving_school_orm;
}