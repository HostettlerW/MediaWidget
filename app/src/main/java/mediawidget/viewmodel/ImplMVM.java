/*
 * Copyright (c) 2026 William Hostettler.
 * Licensed under MIT
 */
package mediawidget.viewmodel;

import mediawidget.model.calls.MediaSystemCaller;

public class ImplMVM implements MediaViewModel {

    private final MediaSystemCaller msc;

    public ImplMVM(MediaSystemCaller msc) {
        this.msc = msc;
    }

    @Override
    public void togglePlayback() {
        msc.togglePlayback();
    }

    @Override
    public void next() {
        msc.next();
    }

    @Override
    public void previous() {
        msc.previous();
    }

    @Override
    public void volumeUp() {
        msc.volumeUp();
    }

    @Override
    public void volumeDown() {
        msc.volumeDown();
    }

}
