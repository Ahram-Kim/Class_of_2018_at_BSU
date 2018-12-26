//
//  ViewController.swift
//  Views
//
//  Created by Michael Ziray on 8/28/18.
//  Copyright © 2018 BSU. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var onOffSwitch: UISwitch!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        onOffSwitch.isOn = false
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func switchDidChange(_ switchThatChange: UISwitch, forEvent event: UIEvent) {
        
        print(switchThatChange.isOn)
        if switchThatChange.isOn {
            print("on")
        }
        else{
            print("off")
        }
    }
    @IBAction func presentSecondViewController(_ sender: Any) {
        
        let secondViewController:UIViewController = (self.storyboard?.instantiateViewController(withIdentifier: "SecondViewController"))!
        
        self.present(secondViewController, animated: true, completion: nil)
        
    }
    
}

