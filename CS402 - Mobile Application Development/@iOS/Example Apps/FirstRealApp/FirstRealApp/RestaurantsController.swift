//
//  RestaurantsController.swift
//  FirstRealApp
//
//  Created by Michael Ziray on 9/11/18.
//  Copyright © 2018 BSU. All rights reserved.
//

import UIKit
import Alamofire


class RestaurantsController: NSObject {

    static var restaurantsArray:Array<Restaurant> = Array()
    
    
    
    // Subclassable - able to be overridden
    class func sortRestaurants() -> String{
        return "String"
    }
    
    static func deleteAllRestaurants(){
        restaurantsArray.removeAll()
    }
    
    static func numberOfRestaurants() -> Int{
        return restaurantsArray.count
    }
    
    class func loadRestaurantData(){
        /*[
         {
         "restaurantName": "BSU Eats",
         "restaurantLocation": "Boise"
         },
         {
         "restaurantName": "ISU Food",
         "restaurantLocation": "Pocatello",
         "gps": {
         "latitude": 123,
         "longitude": 45,
         "history": [
         {},
         {}
         ]
         }
         }
         ]*/
        
        
        if let requestURL = URL(string: "http://zstudiolabs.com/labs/cs402/restaurants.json") {
            Alamofire.request(requestURL).responseJSON { (responseData) in
//                print(responseData)
                
                // Recaste result value into an array
                let jsonArray:NSArray = (responseData.result.value as? NSArray)!
                
                // Loop through all objects in returned json array
                for restaurant in jsonArray {
                    let restaurantData:NSDictionary = restaurant as! NSDictionary
                    
                    let currentRestaurant:Restaurant = Restaurant()

                    currentRestaurant.restaurantName = restaurantData["restaurantName"] as! String
                    currentRestaurant.restaurantDescription = restaurantData["restaurantDescription"] as? String

                    RestaurantsController.restaurantsArray.append(currentRestaurant)
                    
                    print(" ") // Sometimes the debugger will put variables out of scope. Having another line at the end of the loop will allow you to investigate variables in the debugger before the go out of scope.
                }
            }
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
}
