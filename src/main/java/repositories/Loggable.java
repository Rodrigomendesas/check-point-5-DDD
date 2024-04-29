package repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public interface Loggable {
    Logger LOGGER = (Logger) LogManager.getLogger(Loggable.class);
    default void logInfo(String message){
        LOGGER.info(message); //messages that are informational with a lower severity level
    }
    default void logError(String message){
        LOGGER.error(message); //log error messages of  higher severity
    }
}
