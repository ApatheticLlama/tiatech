package tiatech.api.utils;

import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class TTLog {

    public static Logger logger;

    private TTLog() {}

    public static void init(@NotNull Logger modLogger) {
        logger = modLogger;
    }
}