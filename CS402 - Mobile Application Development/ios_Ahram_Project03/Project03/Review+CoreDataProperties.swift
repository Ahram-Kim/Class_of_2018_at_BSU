//
//  Review+CoreDataProperties.swift
//  Project03
//
//  Created by ahramkim on 12/3/18.
//  Copyright © 2018 ahramkim. All rights reserved.
//
//

import Foundation
import CoreData


extension Review {

    @nonobjc public class func fetchRequest() -> NSFetchRequest<Review> {
        return NSFetchRequest<Review>(entityName: "Review")
    }

    @NSManaged public var rName: String?
    @NSManaged public var rOpinion: String?

}
