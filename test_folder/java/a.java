package test.android.example.com.snippetsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.kii.cloud.storage.Kii;
import com.kii.thing_if.KiiApp;
import com.kii.thing_if.LayoutPosition;
import com.kii.thing_if.OnboardWithVendorThingIDOptions;
import com.kii.thing_if.Owner;
import com.kii.thing_if.Site;
import com.kii.thing_if.ThingIFAPI;
import com.kii.thing_if.TypedID;
import com.kii.thing_if.clause.query.AllClause;
import com.kii.thing_if.clause.trigger.RangeClauseInTrigger;
import com.kii.thing_if.command.Action;
import com.kii.thing_if.command.ActionResult;
import com.kii.thing_if.command.AliasAction;
import com.kii.thing_if.command.Command;
import com.kii.thing_if.command.CommandForm;
import com.kii.thing_if.exception.BadRequestException;
import com.kii.thing_if.exception.ConflictException;
import com.kii.thing_if.exception.ForbiddenException;
import com.kii.thing_if.exception.InternalServerErrorException;
import com.kii.thing_if.exception.NotFoundException;
import com.kii.thing_if.exception.ServiceUnavailableException;
import com.kii.thing_if.exception.ThingIFException;
import com.kii.thing_if.exception.UnregisteredAliasException;
import com.kii.thing_if.query.AggregatedResult;
import com.kii.thing_if.query.Aggregation;
import com.kii.thing_if.query.GroupedHistoryStates;
import com.kii.thing_if.query.GroupedHistoryStatesQuery;
import com.kii.thing_if.query.HistoryState;
import com.kii.thing_if.query.HistoryStatesQuery;
import com.kii.thing_if.query.TimeRange;
import com.kii.thing_if.trigger.Condition;
import com.kii.thing_if.trigger.StatePredicate;
import com.kii.thing_if.trigger.Trigger;
import com.kii.thing_if.trigger.TriggerOptions;
import com.kii.thing_if.trigger.TriggeredCommandForm;
import com.kii.thing_if.trigger.TriggersWhen;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
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
        // CodeTagStart: variables_test
        ThingIFAPI api;
        // CodeTagEnd: variables_test

        // 1. Create ThingIFAPI_test
        {
            // CodeTagStart: thingIFAPI_test
            KiiApp app = new KiiApp("___APPID___", "___APPKEY___", Site.JP);
            ThingIFAPI.Builder builder = ThingIFAPI.Builder.newBuilder(getApplicationContext(), app, owner);
            builder.registerAction("AirConditionerAlias", "turnPower", TurnPower.class);
            builder.registerAction("AirConditionerAlias", "setPresetTemperature", SetPresetTemperature.class);
            builder.registerAction("AirConditionerAlias", "setFanSpeed", SetFanSpeed.class);
            api = builder.build();
            // CodeTagEnd: thingIFAPI_test
        }