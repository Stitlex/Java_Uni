module order.processing {
    requires static lombok;
    requires javafaker;
    requires java.sql; // потрібен SnakeYAML усередині JavaFaker

    exports com.example.order.processing;
    exports com.example.order.processing.model;
    exports com.example.order.processing.exception;
    // пакет internal не експортується
}
