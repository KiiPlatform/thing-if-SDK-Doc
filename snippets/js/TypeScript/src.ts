/// <reference path="typings/index.d.ts"/>
import * as ThingIF from 'thing-if'

const owner = new ThingIF.TypedID(ThingIF.Types.User, "dummyID");

// CodeTagStart: thingIFAPI_ts
const app = new ThingIF.App("___APPID___", "___APPKEY___", ThingIF.Site.JP);
const api = new ThingIF.ThingIFAPI(owner, "dummyToken", app);
// CodeTagEnd: thingIFAPI_ts

// CodeTagStart: onboard_ts
const vendorThingID = "nbvadgjhcbn";
const thingPassword = "123456";
const onboardRequest = new ThingIF.OnboardWithVendorThingIDRequest(
  vendorThingID,
  thingPassword,
  owner,
  "AirConditioner",
  "v1",
  undefined,
  ThingIF.LayoutPosition.STANDALONE);
api.onboardWithVendorThingID(onboardRequest)
  .then((result: ThingIF.OnboardingResult) => {
    const thingID = result.thingID;
    const thingAccessToken = result.accessToken;
  })
  .catch((error: ThingIF.ThingIFError) => {
    if (error instanceof ThingIF.HttpRequestError) {
      switch (error.status) {
        case 403:
          // Handle the error.
          break;
        case 404:
          // Handle the error.
          break;
        case 500:
          // Handle the error.
          break;
        default:
          // Handle the error.
          break;
      }
    }
  });
// CodeTagEnd: onboard_ts

// CodeTagStart: updateThingType_ts
api.updateThingType("AirConditioner2")
  .catch((error: ThingIF.ThingIFError) => {
    if (error instanceof ThingIF.HttpRequestError) {
      switch (error.status) {
        case 400:
          // Handle the error.
          break;
        case 403:
          // Handle the error.
          break;
        case 404:
          // Handle the error.
          break;
        case 409:
          // Handle the error.
          break;
        case 503:
          // Handle the error.
          break;
        default:
          // Handle the error.
          break;
      }
    }
  });
// CodeTagEnd: updateThingType_ts

// CodeTagStart: updateFirmwareVersion_ts
api.updateFirmwareVersion("v2")
  .catch((error: ThingIF.ThingIFError) => {
    if (error instanceof ThingIF.HttpRequestError) {
      switch (error.status) {
        case 403:
          // Handle the error.
          break;
        case 404:
          // Handle the error.
          break;
        case 503:
          // Handle the error.
          break;
        default:
          // Handle the error.
          break;
      }
    }
  });
// CodeTagEnd: updateFirmwareVersion_ts

// CodeTagStart: postNewCommand_ts
const commandRequest = new ThingIF.PostCommandRequest([
  new ThingIF.AliasAction(
    "AirConditionerAlias",
    [
      new ThingIF.Action("turnPower", true),
      new ThingIF.Action("setPresetTemperature", 25),
      new ThingIF.Action("setFanSpeed", 5)
    ]
  )
]);
api.postNewCommand(commandRequest)
  .then((command: ThingIF.Command) => {
    // Do something.
  })
  .catch((error: ThingIF.ThingIFError) => {
    if (error instanceof ThingIF.HttpRequestError) {
      switch (error.status) {
        case 400:
          // Handle the error.
          break;
        case 403:
          // Handle the error.
          break;
        case 503:
          // Handle the error.
          break;
        default:
          // Handle the error.
          break;
      }
    }
  });
// CodeTagEnd: postNewCommand_ts

// CodeTagStart: getCommand_ts
api.getCommand("commandID")
  .then((command: ThingIF.Command) => {
    const aliasActionResults = command.getAliasActionResults("AirConditionerAlias");
    for (const aliasActionResult of aliasActionResults) {
      for (const actionResult of aliasActionResult.getActionResults("turnPower")) {
        const actionName : string = actionResult.actionName;
        const succeeded : boolean = actionResult.succeeded;
        const data : Object = actionResult.data;
        const errorMessage : string = actionResult.errorMessage;
      }
    }
  })
  .catch((error: ThingIF.ThingIFError) => {
    if (error instanceof ThingIF.HttpRequestError) {
      switch (error.status) {
        case 403:
          // Handle the error.
          break;
        case 404:
          // Handle the error.
          break;
        case 503:
          // Handle the error.
          break;
        default:
          // Handle the error.
          break;
      }
    }
  });
// CodeTagEnd: getCommand_ts

// CodeTagStart: postNewTrigger_ts
const triggerCommand = new ThingIF.TriggerCommandObject(
  [
    new ThingIF.AliasAction("AirConditionerAlias",
      [
        new ThingIF.Action("tornPower", true),
        new ThingIF.Action("setPresetTemperature", 25),
        new ThingIF.Action("setFanSpeed", 5)
      ])
  ],
  new ThingIF.TypedID(ThingIF.Types.Thing, "th.01234-abcde"),
  owner);
const postPredicate = new ThingIF.StatePredicate(
  new ThingIF.Condition(
    ThingIF.RangeClauseInTrigger.greaterThanEquals(
      "AirConditionerAlias", "currentTemperature", 30)),
  ThingIF.TriggersWhen.CONDITION_FALSE_TO_TRUE
);
const postTriggerRequest = new ThingIF.PostCommandTriggerRequest(triggerCommand, postPredicate);
api.postCommandTrigger(postTriggerRequest)
  .then((trigger: ThingIF.Trigger) => {
    // check trigger.
  })
  .catch((error: ThingIF.ThingIFError) => {
    if (error instanceof ThingIF.HttpRequestError) {
      switch (error.status) {
        case 403:
          // Handle the error.
          break;
        case 404:
          // Handle the error.
          break;
        case 503:
          // Handle the error.
          break;
        default:
          // Handle the error.
          break;
      }
    }
  });
// CodeTagEnd: postNewTrigger_ts

// CodeTagStart: patchTrigger_ts
const triggerID = "{get trigger id from Trigger instance.}";
const patchPredicate = new ThingIF.StatePredicate(
  new ThingIF.Condition(
    ThingIF.RangeClauseInTrigger.greaterThanEquals(
      "AirConditionerAlias", "currentTemperature", 28)),
  ThingIF.TriggersWhen.CONDITION_FALSE_TO_TRUE
);
const patchTriggerRequest = new ThingIF.PostCommandTriggerRequest(triggerCommand, patchPredicate);
api.patchCommandTrigger(triggerID, patchTriggerRequest)
  .then((trigger: ThingIF.Trigger) => {
    // check trigger.
  })
  .catch((error: ThingIF.ThingIFError) => {
    if (error instanceof ThingIF.HttpRequestError) {
      switch (error.status) {
        case 403:
          // Handle the error.
          break;
        case 404:
          // Handle the error.
          break;
        case 503:
          // Handle the error.
          break;
        default:
          // Handle the error.
          break;
      }
    }
  });
// CodeTagEnd: patchTrigger_ts

// CodeTagStart: ungroupedQuery_ts
const ungroupedQueryRequest = new ThingIF.QueryHistoryStatesRequest(
  "AirConditionerAlias",
  new ThingIF.AllClause());
api.query(ungroupedQueryRequest)
  .then((queryResult: ThingIF.QueryResult<ThingIF.HistoryState>) => {
    // check result.
  })
  .catch((error: ThingIF.ThingIFError) => {
    if (error instanceof ThingIF.HttpRequestError) {
      switch (error.status) {
        case 400:
          // Handle the error.
          break;
        case 403:
          // Handle the error.
          break;
        case 404:
          // Handle the error.
          break;
        case 409:
          // Handle the error.
          break;
        case 503:
          // Handle the error.
          break;
        default:
          // Handle the error.
          break;
      }
    }
  });
// CodeTagEnd: ungroupedQuery_ts

// CodeTagStart: groupedQuery_ts
const groupedQueryRequest = new ThingIF.QueryGroupedHistoryStatesRequest(
  "AirConditionerAlias",
  new ThingIF.TimeRange(new Date(), new Date()),
  new ThingIF.AllClause());
api.groupedQuery(groupedQueryRequest)
  .then((queryResults: Array<ThingIF.GroupedHistoryStates>) => {
    // check results.
  })
  .catch((error: ThingIF.ThingIFError) => {
    if (error instanceof ThingIF.HttpRequestError) {
      switch (error.status) {
        case 400:
          // Handle the error.
          break;
        case 403:
          // Handle the error.
          break;
        case 404:
          // Handle the error.
          break;
        case 409:
          // Handle the error.
          break;
        case 503:
          // Handle the error.
          break;
        default:
          // Handle the error.
          break;
      }
    }
  });
// CodeTagEnd: groupedQuery_ts

// CodeTagStart: aggregate_ts
const aggregateRequest = new ThingIF.AggregateGroupedHistoryStatesRequest(
  new ThingIF.QueryGroupedHistoryStatesRequest(
    "AirConditionerAlias",
    new ThingIF.TimeRange(new Date(), new Date()),
    new ThingIF.AllClause()),
  new ThingIF.Aggregation(ThingIF.FunctionType.COUNT, "power", ThingIF.FieldType.BOOLEAN)
);
api.aggregate(aggregateRequest)
  .then((aggregateResults: Array<ThingIF.AggregatedResults>) => {
    // check results.
  })
  .catch((error: ThingIF.ThingIFError) => {
    if (error instanceof ThingIF.HttpRequestError) {
      switch (error.status) {
        case 400:
          // Handle the error.
          break;
        case 403:
          // Handle the error.
          break;
        case 404:
          // Handle the error.
          break;
        case 409:
          // Handle the error.
          break;
        case 503:
          // Handle the error.
          break;
        default:
          // Handle the error.
          break;
      }
    }
  });
// CodeTagEnd: aggregate_ts
