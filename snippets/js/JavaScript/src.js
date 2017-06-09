"use strict";
exports.__esModule = true;
/// <reference path="typings/index.d.ts"/>
var ThingIF = require("thing-if");
var owner = new ThingIF.TypedID(ThingIF.Types.User, "dummyID");

// CodeTagStart: thingIFAPI_js
var app = new ThingIF.App("___APPID___", "___APPKEY___", ThingIF.Site.JP);
var api = new ThingIF.ThingIFAPI(owner, "dummyToken", app);
// CodeTagEnd: thingIFAPI_js

// CodeTagStart: onboard_js
var vendorThingID = "nbvadgjhcbn";
var thingPassword = "123456";
var onboardRequest = new ThingIF.OnboardWithVendorThingIDRequest(
    vendorThingID,
    thingPassword,
    owner,
    "AirConditioner",
    "v1",
    null,
    ThingIF.LayoutPosition.STANDALONE);
api.onboardWithVendorThingID(onboardRequest)
    .then(function (result) {
        var thingID = result.thingID;
        var thingAccessToken = result.accessToken;
    }).catch(function (error) {
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
// CodeTagEnd: onboard_js

// CodeTagStart: updateThingType_js
api.updateThingType("AirConditioner2").catch(function (error) {
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
// CodeTagEnd: updateThingType_js

// CodeTagStart: updateFirmwareVersion_js
api.updateFirmwareVersion("v2").catch(function (error) {
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
// CodeTagEnd: updateFirmwareVersion_js

// CodeTagStart: postNewCommand_js
var commandRequest = new ThingIF.PostCommandRequest([
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
    .then(function (command) {
    // Do something.
}).catch(function (error) {
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
// CodeTagEnd: postNewCommand_js

// CodeTagStart: getCommand_js
api.getCommand("commandID")
    .then(function (command) {
    var aliasActionResults = command.getAliasActionResults("AirConditionerAlias");
    for (var _i = 0, aliasActionResults_1 = aliasActionResults; _i < aliasActionResults_1.length; _i++) {
        var aliasActionResult = aliasActionResults_1[_i];
        for (var _a = 0, _b = aliasActionResult.getActionResults("turnPower"); _a < _b.length; _a++) {
            var actionResult = _b[_a];
            var actionName = actionResult.actionName;
            var succeeded = actionResult.succeeded;
            var data = actionResult.data;
            var errorMessage = actionResult.errorMessage;
        }
    }
}).catch(function (error) {
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
// CodeTagEnd: getCommand_js

// CodeTagStart: postNewTrigger_js
var triggerCommand = new ThingIF.TriggerCommandObject(
    [
        new ThingIF.AliasAction(
            "AirConditionerAlias",
            [
                new ThingIF.Action("tornPower", true),
                new ThingIF.Action("setPresetTemperature", 25),
                new ThingIF.Action("setFanSpeed", 5)
            ]
        )
    ],
    new ThingIF.TypedID(ThingIF.Types.Thing, "th.01234-abcde"),
    owner);
var postPredicate = new ThingIF.StatePredicate(
    new ThingIF.Condition(
        ThingIF.RangeClauseInTrigger.greaterThanEquals(
            "AirConditionerAlias", "currentTemperature", 30)),
    ThingIF.TriggersWhen.CONDITION_FALSE_TO_TRUE);
var postTriggerRequest = new ThingIF.PostCommandTriggerRequest(triggerCommand, postPredicate);
api.postCommandTrigger(postTriggerRequest)
    .then(function (trigger) {
        // check trigger.
    }).catch(function (error) {
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
// CodeTagEnd: postNewTrigger_js

// CodeTagStart: patchTrigger_js
var triggerID = "{get trigger id from Trigger instance.}";
var patchPredicate = new ThingIF.StatePredicate(
    new ThingIF.Condition(
        ThingIF.RangeClauseInTrigger.greaterThanEquals(
            "AirConditionerAlias", "currentTemperature", 28)),
    ThingIF.TriggersWhen.CONDITION_FALSE_TO_TRUE);
var patchTriggerRequest = new ThingIF.PostCommandTriggerRequest(null, patchPredicate);
api.patchCommandTrigger(triggerID, patchTriggerRequest)
    .then(function (trigger) {
    // check trigger.
}).catch(function (error) {
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
// CodeTagEnd: patchTrigger_js

// CodeTagStart: ungroupedQuery_js
var ungroupedQueryRequest = new ThingIF.QueryHistoryStatesRequest("AirConditionerAlias", new ThingIF.AllClause());
api.query(ungroupedQueryRequest)
    .then(function (queryResult) {
        // check result.
    }).catch(function (error) {
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
// CodeTagEnd: ungroupedQuery_js

// CodeTagStart: groupedQuery_js
var groupedQueryRequest = new ThingIF.QueryGroupedHistoryStatesRequest(
    "AirConditionerAlias",
    new ThingIF.TimeRange(new Date(), new Date()),
    new ThingIF.AllClause());
api.groupedQuery(groupedQueryRequest)
    .then(function (queryResults) {
        // check results.
    }).catch(function (error) {
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
// CodeTagEnd: groupedQuery_js

// CodeTagStart: aggregate_js
var aggregateRequest = new ThingIF.AggregateGroupedHistoryStatesRequest(
    new ThingIF.QueryGroupedHistoryStatesRequest(
        "AirConditionerAlias",
        new ThingIF.TimeRange(new Date(), new Date()),
        new ThingIF.AllClause()),
    new ThingIF.Aggregation(
        ThingIF.FunctionType.COUNT,
        "power",
        ThingIF.FieldType.BOOLEAN));
api.aggregate(aggregateRequest)
    .then(function (aggregateResults) {
        // check results.
    }).catch(function (error) {
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
// CodeTagEnd: aggregate_js
