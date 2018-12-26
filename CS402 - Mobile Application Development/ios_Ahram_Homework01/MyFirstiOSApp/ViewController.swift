//
//  ViewController.swift
//  MyFirstiOSApp
//
//  Created by ahramkim on 9/25/18.
//  Copyright © 2018 ahramkim. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    @IBAction func startClicked(_ sender: Any) {
        let mainTabBarController = storyboard?.instantiateViewController(withIdentifier: "MainTabBarController") as! MainTabBarController
        mainTabBarController.selectedViewController = mainTabBarController.viewControllers?[1]
        present(mainTabBarController, animated: true, completion: nil)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}

