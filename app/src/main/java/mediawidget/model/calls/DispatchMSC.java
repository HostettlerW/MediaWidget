/*
 * Copyright (c) 2026 William Hostettler.
 * Licensed under MIT
 */
package mediawidget.model.calls;

public class DispatchMSC {
    
    public static MediaSystemCaller create() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return new Win32MSC();
        } else {
            throw new UnsupportedOperationException("Unsupported operating system: " + osName);
        }
    }
}
