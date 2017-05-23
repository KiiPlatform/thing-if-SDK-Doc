1. Create ThingIFAPI(especially for Android) 

```java
KiiApp app = new KiiApp("___APPID___", "___APPKEY___", Site.JP);
ThingIFAPI.Builder builder = ThingIFAPI.Builder.newBuilder(getApplicationContext(), app, owner);
builder.registerAction("___ALIAS1___", "___ALIAS1_ACTION1___", Alias1Action1.class);
builder.registerAction("___ALIAS1___", "___ALIAS1_ACTION2___", Alias1Action2.class);
ThingIFAPI api = builder.build();
```

2. Execute Onboard

```java
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
```

3. Update thing type 
```java
try {
  api.updateThingType("dummyThingType");
} catch (ThingIFException e) {
  // Handle the error.
}
```

4. Update firmware version 
```java
try {
  api.updateFirmwareVersion("v2");
} catch (ThingIFException e) {
  // Handle the error.
}
```

5. Command Operations
  - Post new Command (create alias action)
```java
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
```

  - get Command (handle action result) 
```java
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
```

6. Trigger Operations
  - post command trigger(state predicate)
```java
```

  - patch command trigger(state predicate)
```java
```

7. Query thing states
  - ungrouped query
```java
```

  - grouped query
```java
```

  - aggregate
```java
```

