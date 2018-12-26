//
//  ViewController.swift
//  Misc
//
//  Created by Michael Ziray on 11/13/18.
//  Copyright © 2018 BSU. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
    }
    
    override func viewDidAppear(_ animated: Bool) {
        
        
        
//        let hasLaunched = UserDefaults.standard.bool(forKey: "HAS_LAUNCHED")
//
//        if( !hasLaunched ){
            let alertController = UIAlertController(title: "Welcome to my app", message: "Thanks for downloading me! Let's enable some of the services.", preferredStyle: .alert)
            alertController.addAction(UIAlertAction(title: "Dismiss", style: .default, handler: { (action) in
                print("handle first launch tutorial here.")
            }))
            alertController.addAction(UIAlertAction(title: "Go to settings", style: .default, handler: { (action) in
                
                let settingsURL = URL(string: UIApplication.openSettingsURLString)
                UIApplication.shared.open(settingsURL!, options: [:], completionHandler: nil)
            }))
        
        alertController.addAction(UIAlertAction(title: "More Info", style: .default, handler: { (action) in
            
            let moreInfoURL = URL(string: "http://boisestate.edu")
            if UIApplication.shared.canOpenURL(moreInfoURL!) {
                UIApplication.shared.open(moreInfoURL!, options: [:], completionHandler: nil)
            }
        }))
        
        
            self.present(alertController, animated: true, completion: nil)
            
            UserDefaults.standard.set(true, forKey: "HAS_LAUNCHED")
//        }
        
    }


}

