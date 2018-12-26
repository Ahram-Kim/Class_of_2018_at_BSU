//
//  Restaurant.swift
//  FirstRealApp
//
//  Created by Michael Ziray on 9/11/18.
//  Copyright © 2018 BSU. All rights reserved.
//

import UIKit

class Restaurant: NSObject {

    var restaurantName:String = "Default name"
    var restaurantDescription:String?
    var restaurantLocation:String
    
    
    override init() {
        restaurantLocation = "Boise"
    }
}
