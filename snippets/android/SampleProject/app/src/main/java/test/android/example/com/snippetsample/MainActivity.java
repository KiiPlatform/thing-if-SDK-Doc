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
        // CodeTagStart: variables
        ThingIFAPI api;
        // CodeTagEnd: variables

        // 1. Create ThingIFAPI
        {
            // CodeTagStart: thingIFAPI
            KiiApp app = new KiiApp("___APPID___", "___APPKEY___", Site.JP);
            ThingIFAPI.Builder builder = ThingIFAPI.Builder.newBuilder(getApplicationContext(), app, owner);
            builder.registerAction("AirConditionerAlias", "turnPower", TurnPower.class);
            builder.registerAction("AirConditionerAlias", "setPresetTemperature", SetPresetTemperature.class);
            builder.registerAction("AirConditionerAlias", "setFanSpeed", SetFanSpeed.class);
            api = builder.build();
            // CodeTagEnd: thingIFAPI
        }

        // 2. Execute Onboard
        {
            // CodeTagStart: onboard
            String vendorThingID = "nbvadgjhcbn";
            String thingPassword = "123456";
            OnboardWithVendorThingIDOptions.Builder builder = new OnboardWithVendorThingIDOptions.Builder();
            builder.setThingType("AirConditioner");
            builder.setFirmwareVersion("v1");
            builder.setThingProperties(new JSONObject());
            builder.setLayoutPosition(LayoutPosition.STANDALONE);
            OnboardWithVendorThingIDOptions options = builder.build();
            try {
                api.onboardWithVendorThingID(vendorThingID, thingPassword, options);
            } catch (ForbiddenException e) {
                // Handle the error.
            } catch (NotFoundException e) {
                // Handle the error.
            } catch (InternalServerErrorException e) {
                // Handle the error.
            } catch (Exception e) {
                // Handle the error.
            }
            // CodeTagEnd: onboard
        }

        // 3. Update thing type
        // CodeTagStart: updateThingType
        try {
            api.updateThingType("AirConditioner2");
        } catch (BadRequestException e) {
            // Handle the error.
        } catch (ForbiddenException e) {
            // Handle the error.
        } catch (NotFoundException e) {
            // Handle the error.
        } catch (ConflictException e) {
            // Handle the error.
        } catch (ServiceUnavailableException e) {
            // Handle the error.
        } catch (Exception e) {
            // Handle the error.
        }
        // CodeTagEnd: updateThingType

        // 4. Update firmware version
        // CodeTagStart: updateFirmwareVersion
        try {
            api.updateFirmwareVersion("v2");
        } catch (ForbiddenException e) {
            // Handle the error.
        } catch (NotFoundException e) {
            // Handle the error.
        } catch (ServiceUnavailableException e) {
            // Handle the error.
        } catch (Exception e) {
            // Handle the error.
        }
        // CodeTagEnd: updateFirmwareVersion

        // 5. Command Operations - Post new Command
        {
            // CodeTagStart: postNewCommand
            List<Action> actions = new ArrayList<>();
            actions.add(new TurnPower(true));
            actions.add(new SetPresetTemperature(25));
            actions.add(new SetFanSpeed(5));
            List<AliasAction> aliases = new ArrayList<>();
            aliases.add(new AliasAction("AirConditionerAlias", actions));
            CommandForm.Builder builder = CommandForm.Builder.newBuilder(aliases);
            try {
                api.postNewCommand(builder.build());
            } catch (BadRequestException e) {
                // Handle the error.
            } catch (ForbiddenException e) {
                // Handle the error.
            } catch (ServiceUnavailableException e) {
                // Handle the error.
            } catch (Exception e) {
                // Handle the error.
            }
            // CodeTagEnd: postNewCommand
        }

        // 5. Command Operations - get Command
        // CodeTagStart: getCommand
        try {
            Command command = api.getCommand("commandID");
            List<ActionResult> results = command.getActionResult("AirConditionerAlias", "turnPower");
            for (ActionResult result : results) {
                String actionName = result.getActionName();
                JSONObject data = result.getData();
                boolean succeeded = result.isSucceeded();
                String errorMessage = result.getErrorMessage();
            }
        } catch (ForbiddenException e) {
            // Handle the error.
        } catch (NotFoundException e) {
            // Handle the error.
        } catch (ServiceUnavailableException e) {
            // Handle the error.
        } catch (Exception e) {
            // Handle the error.
        }
        // CodeTagEnd: getCommand

        // 6. Trigger Operations - post command trigger
        {
            // CodeTagStart: postNewTrigger
            List<Action> actions = new ArrayList<>();
            actions.add(new TurnPower(true));
            actions.add(new SetPresetTemperature(25));
            actions.add(new SetFanSpeed(5));
            TriggeredCommandForm form = TriggeredCommandForm.Builder.newBuilder()
                    .addAliasAction(new AliasAction("AirConditionerAlias", actions))
                    .setTitle("Power on")
                    .setDescription("Power on and set to 25 deg C")
                    .build();
            Condition condition = new Condition(
                    RangeClauseInTrigger.greaterThanOrEqualTo("AirConditionerAlias", "currentTemperature", 30));
            StatePredicate predicate = new StatePredicate(condition, TriggersWhen.CONDITION_FALSE_TO_TRUE);
            TriggerOptions options = TriggerOptions.Builder.newBuilder()
                    .setTitle("Power on")
                    .setDescription("Power on when the temperature goes over 30 deg C")
                    .build();
            try {
                Trigger trigger = api.postNewTrigger(form, predicate, options);
            } catch (ForbiddenException e) {
                // Handle the error.
            } catch (NotFoundException e) {
                // Handle the error.
            } catch (ServiceUnavailableException e) {
                // Handle the error.
            } catch (Exception e) {
                // Handle the error.
            }
            // CodeTagEnd: postNewTrigger
        }

        // 6. Trigger Operations - patch command trigger
        {
            // CodeTagStart: patchTrigger
            String triggerID = "{get trigger id from Trigger instance}";
            Condition condition = new Condition(
                    RangeClauseInTrigger.greaterThanOrEqualTo("AirConditionerAlias", "currentTemperature", 28));
            StatePredicate predicate = new StatePredicate(condition, TriggersWhen.CONDITION_FALSE_TO_TRUE);
            TriggerOptions options = TriggerOptions.Builder.newBuilder()
                    .setTitle("Power on")
                    .setDescription("Power on when the temperature goes over 28 deg C")
                    .build();
            try {
                api.patchCommandTrigger(triggerID, null, predicate, options);
            } catch (ForbiddenException e) {
                // Handle the error.
            } catch (NotFoundException e) {
                // Handle the error.
            } catch (ServiceUnavailableException e) {
                // Handle the error.
            } catch (Exception e) {
                // Handle the error.
            }
            // CodeTagEnd: patchTrigger
        }

        // 7. Query thing states - ungrouped query
        {
            // CodeTagStart: ungroupedQuery
            HistoryStatesQuery query = HistoryStatesQuery.Builder
                    .newBuilder("AirConditionerAlias", new AllClause())
                    .build();
            try {
                Pair<List<HistoryState<AirConditionerState>>, String> result =
                        api.query(query, AirConditionerState.class);
                for (HistoryState<AirConditionerState> state : result.first) {
                    // check state.
                }
                String nextPaginationKey = result.second;
            } catch (UnregisteredAliasException e) {
                // Handle the error.
            } catch (BadRequestException e) {
                // Handle the error.
            } catch (ForbiddenException e) {
                // Handle the error.
            } catch (NotFoundException e) {
                // Handle the error.
            } catch (ConflictException e) {
                // Handle the error.
            } catch (ServiceUnavailableException e) {
                // Handle the error.
            } catch (Exception e) {
                // Handle the error.
            }
            // CodeTagEnd: ungroupedQuery
        }

        // 7. Query thing states - grouped query
        {
            // CodeTagStart: groupedQuery
            TimeRange timeRange = new TimeRange(new Date(), new Date());
            GroupedHistoryStatesQuery query = GroupedHistoryStatesQuery.Builder
                    .newBuilder("AirConditionerAlias", timeRange)
                    .setClause(new AllClause())
                    .build();
            try {
                List<GroupedHistoryStates<AirConditionerState>> results =
                        api.query(query, AirConditionerState.class);
                for (GroupedHistoryStates<AirConditionerState> result : results) {
                    TimeRange range = result.getTimeRange();
                    for (HistoryState<AirConditionerState> state : result.getObjects()) {
                        // check state.
                    }
                }
            } catch (UnregisteredAliasException e) {
                // Handle the error.
            } catch (BadRequestException e) {
                // Handle the error.
            } catch (ForbiddenException e) {
                // Handle the error.
            } catch (NotFoundException e) {
                // Handle the error.
            } catch (ConflictException e) {
                // Handle the error.
            } catch (ServiceUnavailableException e) {
                // Handle the error.
            } catch (Exception e) {
                // Handle the error.
            }
            // CodeTagEnd: groupedQuery
        }

        // 7. Query thing states - aggregate
        {
            // CodeTagStart: aggregate
            TimeRange timeRange = new TimeRange(new Date(), new Date());
            GroupedHistoryStatesQuery query = GroupedHistoryStatesQuery.Builder
                    .newBuilder("AirConditionerAlias", timeRange)
                    .setClause(new AllClause())
                    .build();
            Aggregation aggregate = Aggregation.newCountAggregation("power",
                    Aggregation.FieldType.BOOLEAN);
            try {
                List<AggregatedResult<Integer, AirConditionerState>> results =
                        api.aggregate(query, aggregate, AirConditionerState.class, Integer.class);
                for (AggregatedResult<Integer, AirConditionerState> result : results) {
                    TimeRange range = result.getTimeRange();
                    if (result.getValue() != null) {
                        int count = result.getValue();
                        // check count
                    }
                    for (HistoryState<AirConditionerState> state : result.getAggregatedObjects()) {
                        // check state
                    }
                }
            } catch (UnregisteredAliasException e) {
                // Handle the error.
            } catch (BadRequestException e) {
                // Handle the error.
            } catch (ForbiddenException e) {
                // Handle the error.
            } catch (NotFoundException e) {
                // Handle the error.
            } catch (ConflictException e) {
                // Handle the error.
            } catch (ServiceUnavailableException e) {
                // Handle the error.
            } catch (Exception e) {
                // Handle the error.
            }
            // CodeTagEnd: aggregate
        }
    }
}
