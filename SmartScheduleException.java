/**
 * Created by krejcir on 29.4.14.
 */
public class SmartScheduleException extends Exception {

    public SmartScheduleException() {
        super();
    }

    public SmartScheduleException(String message) {
        super(message);
    }

    public SmartScheduleException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmartScheduleException(Throwable cause) {
        super(cause);
    }

    protected SmartScheduleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
