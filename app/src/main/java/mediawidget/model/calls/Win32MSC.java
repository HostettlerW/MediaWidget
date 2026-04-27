package mediawidget.model.calls;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.WORD;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.INPUT;
import com.sun.jna.platform.win32.WinUser.KEYBDINPUT;

public class Win32MSC implements MediaSystemCaller{

    private static final int KEYEVENTF_EXTENDEDKEY = 0x0001;
    private static final int KEYEVENTF_KEYUP = 0x0002;

    private static final int VK_MEDIA_NEXT_TRACK = 0xB0;
    private static final int VK_MEDIA_PREV_TRACK = 0xB1;
    private static final int VK_MEDIA_PLAY_PAUSE = 0xB3;
    private static final int VK_VOLUME_DOWN = 0xAE;
    private static final int VK_VOLUME_UP = 0xAF;

    @Override
    public void togglePlayback() {
        sendVirtualKey(VK_MEDIA_PLAY_PAUSE);
    }

    @Override
    public void next() {
        sendVirtualKey(VK_MEDIA_NEXT_TRACK);
    }

    @Override
    public void previous() {
        sendVirtualKey(VK_MEDIA_PREV_TRACK);
    }

    @Override
    public void volumeUp() {
        sendVirtualKey(VK_VOLUME_UP);
    }

    @Override
    public void volumeDown() {
        sendVirtualKey(VK_VOLUME_DOWN);
    }

    private void sendVirtualKey(int virtualKey) {
        INPUT[] events = (INPUT[]) new INPUT().toArray(2);
        fillKeyboardInput(events[0], virtualKey, KEYEVENTF_EXTENDEDKEY);
        fillKeyboardInput(events[1], virtualKey, KEYEVENTF_EXTENDEDKEY | KEYEVENTF_KEYUP);

        DWORD sent = User32.INSTANCE.SendInput(new DWORD(events.length), events, events[0].size());
        if (sent.intValue() != events.length) {
            int errorCode = Native.getLastError();
            throw new IllegalStateException("SendInput failed with Win32 error code: " + errorCode);
        }
    }

    private void fillKeyboardInput(INPUT input, int virtualKey, int flags) {
        input.type = new DWORD(WinUser.INPUT.INPUT_KEYBOARD);
        input.input.setType("ki");

        KEYBDINPUT keyboard = new KEYBDINPUT();
        keyboard.wVk = new WORD(virtualKey);
        keyboard.wScan = new WORD(0);
        keyboard.dwFlags = new DWORD(flags);
        keyboard.time = new DWORD(0);

        input.input.ki = keyboard;
        input.write();
    }
    
}
