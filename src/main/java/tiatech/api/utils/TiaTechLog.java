package tiatech.api.utils;

import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class TiaTechLog {

    public static Logger logger;

    private TiaTechLog() {}

    public static void init(@NotNull Logger modLogger) {
        logger = modLogger;
    }
}