// Simple test runner (plain Java, no JUnit) to exercise core flows.
import com.smartoffice.config.OfficeConfig;
import com.smartoffice.model.Room;
import com.smartoffice.service.BookingManager;
import com.smartoffice.util.LoggerUtil;

import java.time.LocalDateTime;

public class TestRunner {
    public static void main(String[] args) throws Exception {
        LoggerUtil.getLogger().info("Running basic tests...");
        OfficeConfig cfg = OfficeConfig.getInstance();
        cfg.createRooms(3);
        cfg.setRoomCapacity(1, 10);

        BookingManager bm = BookingManager.getInstanceWithWindow(0); // immediate auto-release for test
        // book room 1 for 60 minutes starting now
        bm.bookRoom(1, LocalDateTime.now(), 60, "tester");
        assert bm.getBooking(1).isPresent() : "Booking should exist";
        bm.cancelBooking(1);
        assert !bm.getBooking(1).isPresent() : "Booking should be cancelled";
        LoggerUtil.getLogger().info("All tests passed (basic).");
        bm.shutdown();
    }
}
