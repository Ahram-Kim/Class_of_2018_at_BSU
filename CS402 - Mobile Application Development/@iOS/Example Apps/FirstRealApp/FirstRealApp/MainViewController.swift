//
//  MainViewController.swift
//  FirstRealApp
//
//  Created by Michael Ziray on 9/11/18.
//  Copyright © 2018 BSU. All rights reserved.
//

import UIKit

class MainViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
        // if not logged in ...
        let loginViewController = storyboard?.instantiateViewController(withIdentifier: String(describing: LoginViewController.self))
        
        present(loginViewController!, animated: true, completion: nil)
        
        
        // else, show the tab bar controller or content
//        let tabbarController = storyboard?.instantiateViewController(withIdentifier: String(describing: tabbarController.self))
//        
//        present(tabbarController!, animated: true, completion: nil)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
