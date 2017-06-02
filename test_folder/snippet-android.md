
```java
ThingIFAPI api;

KiiApp app = new KiiApp("___APPID___", "___APPKEY___", Site.JP);
ThingIFAPI.Builder builder = ThingIFAPI.Builder.newBuilder(getApplicationContext(), app, owner);
builder.registerAction("AirConditionerAlias", "turnPower", TurnPower.class);
builder.registerAction("AirConditionerAlias", "setPresetTemperature", SetPresetTemperature.class);
builder.registerAction("AirConditionerAlias", "setFanSpeed", SetFanSpeed.class);
api = builder.build();
```
