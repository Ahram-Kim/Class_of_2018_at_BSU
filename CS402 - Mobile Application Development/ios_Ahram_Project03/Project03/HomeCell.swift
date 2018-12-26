//
//  HomeCell.swift
//  Project03
//
//  Created by ahramkim on 11/30/18.
//  Copyright © 2018 ahramkim. All rights reserved.
//

import UIKit

class HomeCell
{
    var title = ""
    var featuredImage: UIImage
    var color: UIColor
    
    init(title: String, featuredImage: UIImage, color: UIColor)
    {
        self.title = title
        self.featuredImage = featuredImage
        self.color = color
    }
    
    static func fetchHomeCell() -> [HomeCell]
    {
        return [
            HomeCell(title: "Seoul, the capital of South Korea, is a huge metropolis where modern skyscrapers, high-tech subways and pop culture meet Buddhist temples, palaces and street markets.",
                     featuredImage: UIImage(named: "seoul4")!, color: UIColor(red: 63/255.0, green: 71/255.0, blue: 80/255.0, alpha: 0.8)),
            HomeCell(title: "Seoul has 21 subway lines that interlink every district of the city with one another and with the surrounding area. Seoul also has many big intercity/express bus terminals. These buses connect Seoul to cities all around Korea. Seoul is connected to every major city in South Korea by railroad. Seoul is also linked to most major Korean cities by the train.",
                     featuredImage: UIImage(named: "seoul")!, color: UIColor(red: 240/255.0, green: 133/255.0, blue: 91/255.0, alpha: 0.7)),
            HomeCell(title: "K-pop is characterized by a wide variety of audiovisual elements. While the modern form of K-pop can be traced back as early as to early 90s, the term has been popularized since 2000s and replaced the term Gayo which also refers to domestic pop music in South Korea.",
                     featuredImage: UIImage(named: "kpop")!, color: UIColor(red: 63/255.0, green: 71/255.0, blue: 80/255.0, alpha: 0.8)),
        ]
    }
}
