package test.android.example.com.snippetsample;

import com.kii.thing_if.command.Action;

public class TurnPower implements Action {
    public boolean power;

    public TurnPower(boolean power) {
        this.power = power;
    }
}
