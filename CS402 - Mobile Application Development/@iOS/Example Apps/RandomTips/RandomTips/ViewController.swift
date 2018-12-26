//
//  ViewController.swift
//  RandomTips
//
//  Created by Michael Ziray on 11/6/18.
//  Copyright © 2018 BSU. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    @IBAction func shareTapped(_ sender: Any) {
        let items = ["BSU events. Come to my event!\n\nIt'll be fun!!! It's at my house."]
        let activityViewController = UIActivityViewController(activityItems: items, applicationActivities: nil)
        present(activityViewController, animated: true, completion: nil)
        
    }
    
}

