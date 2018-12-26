//
//  AppDelegate.swift
//  RandomTips
//
//  Created by Michael Ziray on 11/6/18.
//  Copyright © 2018 BSU. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?


    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        
        
        // Guard prevents code execution past this point if the condition is not satisfied
//        guard event != nil else {
//            return
//        }
        
        
        // Im Project settings -> Build Settings -> Other Swift Flags, set the debug setting, for any simulator to `-D IOS_SIMULATOR` to set a flag when running on the simulator
        #if IOS_SIMULATOR
        print("on the simulator")
        #else
        print("on a device")
        #endif
        
        
        // if-let allows you to avoid force unwrapping variables.
//        if let someDate = event.eventDate {
//            print(someDate)
//        }
        
        
        
        
        return true
    }
    
    
    // Convenience method for creating consistent styles.
    static func coloredRoundedStyleButton(_ button:UIButton ){
        button.layer.borderColor = UIColor(red: 221.0/255.0, green: 0.0, blue: 0.0, alpha: 1.0).cgColor
        button.layer.borderWidth = 1.0
        button.layer.cornerRadius = button.frame.size.height/2
    }


    // String replacement
    static func convertDatabaseString(stringToConvert:String)->String{
        return stringToConvert.replacingOccurrences(of: "\\n", with: "\n")

    }


    // Example of if-let
    static func verifyUrl (urlString: String?) -> Bool {
        //Check for nil
        if let urlString = urlString {

            if let url = URL(string: urlString) {
                // check if your application can open the NSURL instance
                return UIApplication.shared.canOpenURL(url)
            }
        }
        return false
    }

    
    
    func applicationWillResignActive(_ application: UIApplication) {
        // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
        // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
    }

    func applicationDidEnterBackground(_ application: UIApplication) {
        // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
        // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
    }

    func applicationWillEnterForeground(_ application: UIApplication) {
        // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
    }

    func applicationDidBecomeActive(_ application: UIApplication) {
        // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
    }

    func applicationWillTerminate(_ application: UIApplication) {
        // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
    }


}

