//
//  ViewController.swift
//  HelloWorld
//
//  Created by Michael Ziray on 8/23/18.
//  Copyright © 2018 Talloo. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var label: UILabel!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let originalString:String = label.text!
        
        print(originalString)
        
        label.text = "Hello There!"
    }
    
    
    @IBAction func changeTextTapped(_ sender: UIButton) {
        sender.isHidden = true
        
        label.text = "The button was tapped!!!"
    }
    

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

