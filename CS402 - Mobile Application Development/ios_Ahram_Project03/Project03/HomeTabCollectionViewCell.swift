//
//  HomeTabCollectionViewCell.swift
//  Project03
//
//  Created by ahramkim on 11/30/18.
//  Copyright © 2018 ahramkim. All rights reserved.
//

import UIKit

class HomeTabCollectionViewCell: UICollectionViewCell
{
    @IBOutlet weak var featuredImageView: UIImageView!
    @IBOutlet weak var homeTitleLable: UILabel!
    @IBOutlet weak var backgroundColorView: UIView!
    
    var home: HomeCell? {
        didSet {
            self.updateUI()
        }
    }
    
    private func updateUI()
    {
        if let home = home {
            featuredImageView.image = home.featuredImage
            homeTitleLable.text = home.title
            backgroundColorView.backgroundColor = home.color
        } else {
            featuredImageView.image = nil
            homeTitleLable.text = nil
            backgroundColorView.backgroundColor = nil
        }
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        
        self.layer.cornerRadius = 3.0
        layer.shadowRadius = 10
        layer.shadowOpacity = 0.4
        layer.shadowOffset = CGSize(width: 5, height: 10)
        
        self.clipsToBounds = false
    }
}
