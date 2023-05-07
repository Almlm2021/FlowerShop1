module com.example.flowershop {
    requires javafx.controls;
    requires javafx.fxml;
            
                        
    opens com.example.flowershop to javafx.fxml;
    exports com.example.flowershop;
}