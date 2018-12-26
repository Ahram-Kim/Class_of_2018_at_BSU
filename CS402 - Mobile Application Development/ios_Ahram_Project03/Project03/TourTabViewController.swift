//
//  TourTabViewController.swift
//  Project03
//
//  Created by ahramkim on 11/30/18.
//  Copyright © 2018 ahramkim. All rights reserved.
//

import UIKit
import MapKit

class TourTabViewController: UIViewController {
    
    let placeImage = [UIImage(named: "kyungbokgung"),
                      UIImage(named: "seoul5")]
    let placeName = ["Kyungbok Gung",
                     "Cheonggyecheon"]
    let placeLocation = ["161 Sakij-ro, Sejongno, Jongno-gu, Seoul",
                         "Cheonggyecheon-ro, Yonsin-ding, Jongno-Gu, Seoul"]
    let placeTransportation = ["Subway: Gyeongbokgung Station, Bus: No. 1020, 7025, 109. 171, 172, 601, or 606",
                               "Subway: Jonggak Station, Jongno 3-ga Station, City Hall Station"]
    let placeDescription = ["Gyungbokgung was the main royal palace of the Joseon dynastry.",
                            "Cheonggyecheon is a 10.9 kilometre-long, modern public recreation space in downtown Seoul, South Korea."]
    let placeMap = [CLLocationCoordinate2D(latitude: 37.5796, longitude: 126.9770),
                    CLLocationCoordinate2D(latitude: 37.5714, longitude: 127.0246)]
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
}

extension TourTabViewController: UICollectionViewDataSource, UICollectionViewDelegate
{
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return placeName.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "tourCell", for: indexPath) as! TourTabCollectionViewCell
        
        cell.img.image = placeImage[indexPath.row]
        cell.name.text = placeName[indexPath.row]
        cell.location.text = placeLocation[indexPath.row]
        cell.transportation.text = placeTransportation[indexPath.row]
        cell.descrip.text = placeDescription[indexPath.row]
        cell.map.setCenter(placeMap[indexPath.row], animated: true)
        cell.map.setRegion(MKCoordinateRegion(center:placeMap[indexPath.row], span: MKCoordinateSpan(latitudeDelta: 0.01, longitudeDelta: 0.01)), animated: true)
        
        cell.contentView.layer.cornerRadius = 4.0
        cell.contentView.layer.borderWidth = 1.0
        cell.contentView.layer.borderColor = UIColor.black.cgColor
        cell.contentView.layer.masksToBounds = false
        
        cell.layer.shadowColor = UIColor.gray.cgColor
        cell.layer.shadowOffset = CGSize(width: 0, height: 1.0)
        cell.layer.shadowRadius = 4.0
        cell.layer.shadowOpacity = 1.0
        cell.layer.masksToBounds = false
        cell.layer.shadowPath = UIBezierPath(roundedRect: cell.bounds, cornerRadius: cell.contentView.layer.cornerRadius).cgPath
        
        return cell
    }
    
}
