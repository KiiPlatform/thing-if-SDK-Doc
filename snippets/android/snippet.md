1. Create ThingIFAPI(especially for Android)

```java
ThingIFAPI api;

KiiApp app = new KiiApp("___APPID___", "___APPKEY___", Site.JP);
ThingIFAPI.Builder builder = ThingIFAPI.Builder.newBuilder(getApplicationContext(), app, owner);
builder.registerAction("AirConditionerAlias", "turnPower", TurnPower.class);
builder.registerAction("AirConditionerAlias", "setPresetTemperature", SetPresetTemperature.class);
builder.registerAction("AirConditionerAlias", "setFanSpeed", SetFanSpeed.class);
api = builder.build();
```

2. Execute Onboard

```java
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
```

3. Update thing type

```java
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
```

4. Update firmware version

```java
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
```

5. Command Operations
  - Post new Command (create alias action)

```java
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
```

  - get Command (handle action result)

```java
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
```

6. Trigger Operations
  - post command trigger(state predicate)

```java
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
```

  - patch command trigger(state predicate)

```java
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
```

7. Query thing states
  - ungrouped query

```java
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
```

  - grouped query

```java
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
```

  - aggregate

```java
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
```


