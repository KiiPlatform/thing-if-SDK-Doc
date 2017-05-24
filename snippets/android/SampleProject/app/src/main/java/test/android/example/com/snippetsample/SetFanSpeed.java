package test.android.example.com.snippetsample;

import com.kii.thing_if.command.Action;

public class SetFanSpeed implements Action {
    public int fanSpeed;

    public SetFanSpeed(int speed) {
        this.fanSpeed = speed;
    }
}
