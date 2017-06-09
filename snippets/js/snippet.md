1. Create ThingIFAPI(especially for Android)

{% tabcontrol %}

{% tabpage TypeScript %}
{% highlight typescript %}
const app = new ThingIF.App("___APPID___", "___APPKEY___", ThingIF.Site.JP);
const api = new ThingIF.ThingIFAPI(owner, "dummyToken", app);
{% endhighlight %}
{% endtabpage %}

{% tabpage JavaScript %}
{% highlight javascript %}
var app = new ThingIF.App("___APPID___", "___APPKEY___", ThingIF.Site.JP);
var api = new ThingIF.ThingIFAPI(owner, "dummyToken", app);
{% endhighlight %}
{% endtabpage %}

{% endtabcontrol %}

2. Execute Onboard

{% tabcontrol %}

{% tabpage TypeScript %}
{% highlight typescript %}
const vendorThingID = "nbvadgjhcbn";
const thingPassword = "123456";
const onboardRequest = new ThingIF.OnboardWithVendorThingIDRequest(
  vendorThingID,
  thingPassword,
  owner,
  "AirConditioner",
  "v1",
  null,
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
{% endhighlight %}
{% endtabpage %}

{% tabpage JavaScript %}
{% highlight javascript %}
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
{% endhighlight %}
{% endtabpage %}

{% endtabcontrol %}

3. Update thing type

{% tabcontrol %}

{% tabpage TypeScript %}
{% highlight typescript %}
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
{% endhighlight %}
{% endtabpage %}

{% tabpage JavaScript %}
{% highlight javascript %}
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
{% endhighlight %}
{% endtabpage %}

{% endtabcontrol %}

4. Update firmware version

{% tabcontrol %}

{% tabpage TypeScript %}
{% highlight typescript %}
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
{% endhighlight %}
{% endtabpage %}

{% tabpage JavaScript %}
{% highlight javascript %}
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
{% endhighlight %}
{% endtabpage %}

{% endtabcontrol %}

5. Command Operations
  - Post new Command (create alias action)

{% tabcontrol %}

{% tabpage TypeScript %}
{% highlight typescript %}
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
{% endhighlight %}
{% endtabpage %}

{% tabpage JavaScript %}
{% highlight javascript %}
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
{% endhighlight %}
{% endtabpage %}

{% endtabcontrol %}

  - get Command (handle action result)

{% tabcontrol %}

{% tabpage TypeScript %}
{% highlight typescript %}
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
{% endhighlight %}
{% endtabpage %}

{% tabpage JavaScript %}
{% highlight javascript %}
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
{% endhighlight %}
{% endtabpage %}

{% endtabcontrol %}

6. Trigger Operations
  - post command trigger(state predicate)

{% tabcontrol %}

{% tabpage TypeScript %}
{% highlight typescript %}
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
{% endhighlight %}
{% endtabpage %}

{% tabpage JavaScript %}
{% highlight javascript %}
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
{% endhighlight %}
{% endtabpage %}

{% endtabcontrol %}

  - patch command trigger(state predicate)

{% tabcontrol %}

{% tabpage TypeScript %}
{% highlight typescript %}
const triggerID = "{get trigger id from Trigger instance.}";
const patchPredicate = new ThingIF.StatePredicate(
  new ThingIF.Condition(
    ThingIF.RangeClauseInTrigger.greaterThanEquals(
      "AirConditionerAlias", "currentTemperature", 28)),
  ThingIF.TriggersWhen.CONDITION_FALSE_TO_TRUE
);
const patchTriggerRequest = new ThingIF.PostCommandTriggerRequest(null, patchPredicate);
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
{% endhighlight %}
{% endtabpage %}

{% tabpage JavaScript %}
{% highlight javascript %}
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
{% endhighlight %}
{% endtabpage %}

{% endtabcontrol %}

7. Query thing states
  - ungrouped query

{% tabcontrol %}

{% tabpage TypeScript %}
{% highlight typescript %}
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
{% endhighlight %}
{% endtabpage %}

{% tabpage JavaScript %}
{% highlight javascript %}
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
{% endhighlight %}
{% endtabpage %}

{% endtabcontrol %}

  - grouped query

{% tabcontrol %}

{% tabpage TypeScript %}
{% highlight typescript %}
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
{% endhighlight %}
{% endtabpage %}

{% tabpage JavaScript %}
{% highlight javascript %}
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
{% endhighlight %}
{% endtabpage %}

{% endtabcontrol %}

  - aggregate

{% tabcontrol %}

{% tabpage TypeScript %}
{% highlight typescript %}
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
{% endhighlight %}
{% endtabpage %}

{% tabpage JavaScript %}
{% highlight javascript %}
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
{% endhighlight %}
{% endtabpage %}

{% endtabcontrol %}

