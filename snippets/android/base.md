1. Create ThingIFAPI(especially for Android) 

```java
{{#variables}}
{{.}}
{{/variables}}

{{#thingIFAPI}}
{{.}}
{{/thingIFAPI}}
```

2. Execute Onboard

```java
{{#onboard}}
{{.}}
{{/onboard}}
```

3. Update thing type 

```java
{{#updateThingType}}
{{.}}
{{/updateThingType}}
```

4. Update firmware version 

```java
{{#updateFirmwareVersion}}
{{.}}
{{/updateFirmwareVersion}}
```

5. Command Operations
  - Post new Command (create alias action)

```java
{{#postNewCommand}}
{{.}}
{{/postNewCommand}}
```

  - get Command (handle action result) 

```java
{{#getCommand}}
{{.}}
{{/getCommand}}
```

6. Trigger Operations
  - post command trigger(state predicate)

```java
{{#postNewTrigger}}
{{.}}
{{/postNewTrigger}}
```

  - patch command trigger(state predicate)

```java
{{#patchTrigger}}
{{.}}
{{/patchTrigger}}
```

7. Query thing states
  - ungrouped query

```java
{{#ungroupedQuery}}
{{.}}
{{/ungroupedQuery}}
```

  - grouped query

```java
{{#groupedQuery}}
{{.}}
{{/groupedQuery}}
```

  - aggregate

```java
{{#aggregate}}
{{.}}
{{/aggregate}}
```


