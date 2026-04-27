/*
 * Copyright (c) 2026 William Hostettler.
 * Licensed under MIT
 */
package mediawidget.model.calls;

public interface MediaSystemCaller {
    void togglePlayback();
    void next();
    void previous();
    void volumeUp();
    void volumeDown();
}