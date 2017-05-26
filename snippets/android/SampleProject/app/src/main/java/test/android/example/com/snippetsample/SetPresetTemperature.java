package test.android.example.com.snippetsample;

import com.kii.thing_if.command.Action;

public class SetPresetTemperature implements Action {
    public int presetTemperature;

    public SetPresetTemperature(int temperature) {
        this.presetTemperature = temperature;
    }
}
