//
//  TableViewCell.swift
//  Project01
//
//  Created by ahramkim on 10/23/18.
//  Copyright © 2018 ahramkim. All rights reserved.
//

import UIKit

class TableViewCell: UITableViewCell
{

    @IBOutlet weak var vLavel: UILabel!
    
    override func awakeFromNib()
    {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool)
    {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
