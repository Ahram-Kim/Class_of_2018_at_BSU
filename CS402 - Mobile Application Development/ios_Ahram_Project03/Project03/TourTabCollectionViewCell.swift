//
//  TourTabCollectionViewCell.swift
//  Project03
//
//  Created by ahramkim on 12/2/18.
//  Copyright © 2018 ahramkim. All rights reserved.
//

import UIKit
import MapKit

class TourTabCollectionViewCell: UICollectionViewCell
{
    @IBOutlet var img: UIImageView!
    @IBOutlet var name: UILabel!
    @IBOutlet var location: UILabel!
    @IBOutlet var transportation: UILabel!
    @IBOutlet var descrip: UILabel!
    @IBOutlet weak var map: MKMapView!
}
