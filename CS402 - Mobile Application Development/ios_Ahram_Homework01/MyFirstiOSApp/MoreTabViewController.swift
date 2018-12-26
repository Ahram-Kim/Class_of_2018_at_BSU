//
//  MoreTabViewController.swift
//  MyFirstiOSApp
//
//  Created by ahramkim on 9/25/18.
//  Copyright © 2018 ahramkim. All rights reserved.
//

import UIKit

class MoreTabViewController: UIViewController, UINavigationBarDelegate {

    @IBOutlet weak var navBar: UINavigationBar!
    @IBOutlet weak var info: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        navBar.delegate = self
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    // MARK: UINavigationBarDelegate Methods
    func position(for bar: UIBarPositioning) -> UIBarPosition {
        return UIBarPosition.topAttached
    }
}
