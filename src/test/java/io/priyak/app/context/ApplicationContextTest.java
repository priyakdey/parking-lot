package io.priyak.app.context;

import io.priyak.app.controller.ParkingController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Application Context Test")
class ApplicationContextTest {
    ApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new ApplicationContext();
    }

    @Test
    @DisplayName("should wire all components")
    void getController() {
        final ParkingController controller = context.getController(3);
        assertNotNull(controller);
    }
}