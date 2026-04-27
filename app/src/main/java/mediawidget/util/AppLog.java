/*
 * Copyright (c) 2026 William Hostettler.
 * Licensed under MIT
 */
package mediawidget.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class AppLog {

    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Path LOG_PATH = Path.of(
        System.getenv("LOCALAPPDATA") != null ? System.getenv("LOCALAPPDATA") : ".",
        "MediaWidget",
        "mediawidget.log"
    );

    private AppLog() {
    }

    public static void info(String message) {
        write("INFO", message, null);
    }

    public static void error(String message, Throwable error) {
        write("ERROR", message, error);
    }

    private static synchronized void write(String level, String message, Throwable error) {
        StringBuilder line = new StringBuilder()
            .append('[').append(LocalDateTime.now().format(TS)).append("] ")
            .append(level)
            .append(" - ")
            .append(message)
            .append(System.lineSeparator());

        if (error != null) {
            line.append(error.getClass().getName())
                .append(": ")
                .append(error.getMessage())
                .append(System.lineSeparator());
            for (StackTraceElement ste : error.getStackTrace()) {
                line.append("    at ")
                    .append(ste)
                    .append(System.lineSeparator());
            }
        }

        try {
            Files.createDirectories(LOG_PATH.getParent());
            Files.writeString(
                LOG_PATH,
                line.toString(),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );
        } catch (IOException ignored) {
            // Avoid failing the app due to logging errors.
        }
    }
}
