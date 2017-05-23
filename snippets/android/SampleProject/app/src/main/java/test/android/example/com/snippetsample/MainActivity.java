package test.android.example.com.snippetsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kii.cloud.storage.Kii;
import com.kii.thing_if.KiiApp;
import com.kii.thing_if.LayoutPosition;
import com.kii.thing_if.OnboardWithVendorThingIDOptions;
import com.kii.thing_if.Owner;
import com.kii.thing_if.Site;
import com.kii.thing_if.ThingIFAPI;
import com.kii.thing_if.TypedID;
import com.kii.thing_if.command.Action;
import com.kii.thing_if.command.ActionResult;
import com.kii.thing_if.command.AliasAction;
import com.kii.thing_if.command.Command;
import com.kii.thing_if.command.CommandForm;
import com.kii.thing_if.exception.ThingIFException;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Kii.initialize(getApplicationContext(), "___APPID___", "___APPKEY___", Kii.Site.JP, false);
    }

    /* Dummy handler
     */
    public void onClick(View view) {
        Owner owner = new Owner(new TypedID(TypedID.Types.THING, "dummyID"), "dummyToken");
        ThingIFAPI api;

        // 1. Create ThingIFAPI
        {
            KiiApp app = new KiiApp("___APPID___", "___APPKEY___", Site.JP);
            ThingIFAPI.Builder builder = ThingIFAPI.Builder.newBuilder(getApplicationContext(), app, owner);
            builder.registerAction("___ALIAS1___", "___ALIAS1_ACTION1___", Alias1Action1.class);
            builder.registerAction("___ALIAS1___", "___ALIAS1_ACTION2___", Alias1Action2.class);
            api = builder.build();
        }

        // 2. Execute Onboard
        try {
            String vendorThingID = "nbvadgjhcbn";
            String thingPassword = "123456";
            OnboardWithVendorThingIDOptions.Builder builder = new OnboardWithVendorThingIDOptions.Builder();
            builder.setThingType("AirConditioner");
            builder.setFirmwareVersion("v1");
            builder.setThingProperties(new JSONObject());
            builder.setLayoutPosition(LayoutPosition.STANDALONE);
            OnboardWithVendorThingIDOptions options = builder.build();
            api.onboardWithVendorThingID(vendorThingID, thingPassword, options);
        } catch (ThingIFException e) {
            // Handle the error.
        }

        // 3. Update thing type
        try {
            api.updateThingType("AirConditioner2");
        } catch (ThingIFException e) {
            // Handle the error.
        }

        // 4. Update firmware version
        try {
            api.updateFirmwareVersion("v2");
        } catch (ThingIFException e) {
            // Handle the error.
        }

        // 5. Command Operations - Post new Command (create alias action)
        try {
            List<Action> actions = new ArrayList<>();
            actions.add(new Alias1Action1());
            List<AliasAction> aliases = new ArrayList<>();
            aliases.add(new AliasAction("___ALIAS1___", actions));
            CommandForm.Builder builder = CommandForm.Builder.newBuilder(aliases);
            api.postNewCommand(builder.build());
        } catch (ThingIFException e) {
            // Handle the error.
        }

        // 5. Command Operations - get Command (handle action result)
        try {
            Command command = api.getCommand("commandID");
            List<ActionResult> results = command.getActionResult("___ALIAS1___", "___ALIAS1_ACTION1___");
            for (ActionResult result : results) {
                if (result.isSucceeded()) {
                    // check result
                } else {
                    // check error message
                }
            }
        } catch (ThingIFException e) {
            // Handle the error.
        }
    }
}
