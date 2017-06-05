//
//  ViewController.swift
//  SampleProject
//
//

import UIKit
import ThingIF

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.

        let owner = Owner(TypedID(.thing, id: "dummyID"), accessToken: "dummyToken")
        // CodeTagStart: variables_ios
        // CodeTagEnd: variables_ios

        // 1. Create ThingIFAPI
        // CodeTagStart: thingIFAPI_ios
        let app = KiiApp("___APPID___", appKey: "___APPKEY___", site: .jp)
        let api = ThingIFAPI(app, owner: owner)
        // CodeTagEnd: thingIFAPI_ios

        // 2. Execute Onboard
        do {
            // CodeTagStart: onboard_ios
            let vendorThingID = "nbvadgjhcbn"
            let thingPassword = "123456"
            let thingType = "AirConditioner"
            let options = OnboardWithVendorThingIDOptions(
                thingType,
                firmwareVersion: "v1",
                thingProperties: nil,
                position: .standalone)
            api.onboardWith(
                vendorThingID: vendorThingID,
                thingPassword: thingPassword,
                options: options) { (target, error) -> Void in
                    if error != nil {
                        switch error! {
                        case .errorResponse(let response):
                            switch response.httpStatusCode {
                            case 403:
                                // Handle the error.
                                break
                            case 404:
                                // Handle the error.
                                break
                            case 500:
                                // Handle the error.
                                break
                            default:
                                break
                            }
                            break
                        default:
                            break
                        }
                    }
            }
            // CodeTagEnd: onboard_ios
        }

        // CodeTagStart: updateThingType_ios
        api.update(thingType: "AirConditioner2") { (error) in
            if error != nil {
                switch error! {
                case .errorResponse(let response):
                    switch response.httpStatusCode {
                    case 400:
                        // Handle the error.
                        break
                    case 403:
                        // Handle the error.
                        break
                    case 404:
                        // Handle the error.
                        break
                    case 409:
                        // Handle the error.
                        break
                    case 503:
                        // Handle the error.
                        break
                    default:
                        break
                    }
                    break
                default:
                    break
                }
            }
        }
        // CodeTagEnd: updateThingType_ios

        // CodeTagStart: updateFirmwareVersion_ios
        api.update(firmwareVersion: "v2") { (error) in
            if error != nil {
                switch error! {
                case .errorResponse(let response):
                    switch response.httpStatusCode {
                    case 403:
                        // Handle the error.
                        break
                    case 404:
                        // Handle the error.
                        break
                    case 503:
                        // Handle the error.
                        break
                    default:
                        break
                    }
                    break
                default:
                    break
                }
            }
        }
        // CodeTagEnd: updateFirmwareVersion_ios

        do {
            // CodeTagStart: postNewCommand_ios
            let form = CommandForm([
                AliasAction(
                    "AirConditionerAlias",
                    actions: [
                        Action("turnPower", value: true),
                        Action("setPresetTemperature", value: 25),
                        Action("setFanSpeed", value: 5)
                    ])
                ])
            api.postNewCommand(form) { (command, error) in
                if error != nil {
                    switch error! {
                    case .errorResponse(let response):
                        switch response.httpStatusCode {
                        case 400:
                            // Handle the error.
                            break
                        case 403:
                            // Handle the error.
                            break
                        case 503:
                            // Handle the error.
                            break
                        default:
                            break
                        }
                        break
                    default:
                        break
                    }
                }
            }
            // CodeTagEnd: postNewCommand_ios
        }

        // CodeTagStart: getCommand_ios
        api.getCommand("commandID") { (command, error) in
            if error != nil {
                switch error! {
                case .errorResponse(let response):
                    switch response.httpStatusCode {
                    case 403:
                        // Handle the error.
                        break
                    case 404:
                        // Handle the error.
                        break
                    case 503:
                        // Handle the error.
                        break
                    default:
                        break
                    }
                    break
                default:
                    break
                }
            } else {
                let results = command!.getActionResult("AirConditionerAlias", actionName: "turnPower")
                for result in results {
                    let actionName = result.actionName
                    let data = result.data
                    let succeeded = result.succeeded
                    let errorMessage = result.errorMessage
                }
            }
        }
        // CodeTagEnd: getCommand_ios

        do {
            // CodeTagStart: postNewTrigger_ios
            let form = TriggeredCommandForm([
                AliasAction(
                    "AirConditionerAlias",
                    actions: [
                        Action("turnPower", value: true),
                        Action("setPresetTemperature", value: 25),
                        Action("setFanSpeed", value: 5)
                    ])
                ], title: "Power on", commandDescription: "Power on and set to 25 deg C")
            let predicate = StatePredicate(
                Condition(
                    RangeClauseInTrigger.greaterThanOrEqualTo(
                        "AirConditionerAlias",
                        field: "currentTemperature",
                        limit: 30)),
                triggersWhen: .conditionFalseToTrue)
            let options = TriggerOptions(
                "Power on",
                triggerDescription: "Power on when the temperture goes over 30 deg C")
            api.postNewTrigger(form, predicate: predicate, options: options) { (trigger, error) in
                if error != nil {
                    switch error! {
                    case .errorResponse(let response):
                        switch response.httpStatusCode {
                        case 403:
                            // Handle the error.
                            break
                        case 404:
                            // Handle the error.
                            break
                        case 503:
                            // Handle the error.
                            break
                        default:
                            break
                        }
                        break
                    default:
                        break
                    }
                } else {
                    // check trigger.
                }
            }
            // CodeTagEnd: postNewTrigger_ios
        }

        do {
            // CodeTagStart: patchTrigger_ios
            let triggerId = "{get trigger id from Trigger instance}"
            let predicate = StatePredicate(
                Condition(
                    RangeClauseInTrigger.greaterThanOrEqualTo(
                        "AirConditionerAlias",
                        field: "currentTemperature",
                        limit: 28)),
                triggersWhen: .conditionFalseToTrue)
            let options = TriggerOptions(
                "Power on",
                triggerDescription: "Power on when the temperture goes over 28 deg C")
            api.patchTrigger(triggerId, triggeredCommandForm: nil, predicate: predicate, options: options) { (trigger, error) in
                if error != nil {
                    switch error! {
                    case .errorResponse(let response):
                        switch response.httpStatusCode {
                        case 403:
                            // Handle the error.
                            break
                        case 404:
                            // Handle the error.
                            break
                        case 503:
                            // Handle the error.
                            break
                        default:
                            break
                        }
                        break
                    default:
                        break
                    }
                } else {
                    // check trigger.
                }
            }
            // CodeTagEnd: patchTrigger_ios
        }

        do {
            // CodeTagStart: ungroupedQuery_ios
            let query = HistoryStatesQuery("AirConditionerAlias", clause: AllClause())
            api.query(query) { (states, nextPaginationKey, error) in
                if error != nil {
                    switch error! {
                    case .errorResponse(let response):
                        switch response.httpStatusCode {
                        case 400:
                            // Handle the error.
                            break
                        case 403:
                            // Handle the error.
                            break
                        case 404:
                            // Handle the error.
                            break
                        case 409:
                            // Handle the error.
                            break
                        case 503:
                            // Handle the error.
                            break
                        default:
                            break
                        }
                        break
                    default:
                        break
                    }
                } else {
                    for state in states! {
                        // check HistoryState.
                    }
                }
            }
            // CodeTagEnd: ungroupedQuery_ios
        }

        do {
            // CodeTagStart: groupedQuery_ios
            let query = GroupedHistoryStatesQuery(
                "AirConditionerAlias",
                timeRange: TimeRange(Date(), to: Date()),
                clause: AllClause())
            api.query(query) { (statesArray, error) in
                if error != nil {
                    switch error! {
                    case .errorResponse(let response):
                        switch response.httpStatusCode {
                        case 400:
                            // Handle the error.
                            break
                        case 403:
                            // Handle the error.
                            break
                        case 404:
                            // Handle the error.
                            break
                        case 409:
                            // Handle the error.
                            break
                        case 503:
                            // Handle the error.
                            break
                        default:
                            break
                        }
                        break
                    default:
                        break
                    }
                } else {
                    for states in statesArray! {
                        // check GroupedHistoryStates.
                    }
                }
            }
            // CodeTagEnd: groupedQuery_ios
        }

        do {
            // CodeTagStart: aggregate_ios
            let query = GroupedHistoryStatesQuery(
                "AirConditionerAlias",
                timeRange: TimeRange(Date(), to: Date()),
                clause: AllClause())
            let aggregation = Aggregation.makeCountAggregation("power", fieldType: .bool)
            api.aggregate(query, aggregation: aggregation) { (results: [AggregatedResult<Int>]?, error) in
                if error != nil {
                    switch error! {
                    case .errorResponse(let response):
                        switch response.httpStatusCode {
                        case 400:
                            // Handle the error.
                            break
                        case 403:
                            // Handle the error.
                            break
                        case 404:
                            // Handle the error.
                            break
                        case 409:
                            // Handle the error.
                            break
                        case 503:
                            // Handle the error.
                            break
                        default:
                            break
                        }
                        break
                    default:
                        break
                    }
                } else {
                    for result in results! {
                        // check AggregatedResult.
                    }
                }
            }
            // CodeTagEnd: aggregate_ios
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

