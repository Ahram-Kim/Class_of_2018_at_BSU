//
//  ViewController.swift
//  AnimationGestures
//
//  Created by Michael Ziray on 10/30/18.
//  Copyright © 2018 BSU. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var imageView: UIImageView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }


    fileprivate func animateMrHipster() {
        UIView.animate(withDuration: 3.0, delay: 0.5, usingSpringWithDamping: 1.0, initialSpringVelocity: 2.0, options: [], animations: {
            self.imageView.frame.origin.x = 15
        }) { (isCompleted) in
            print("finished")
        }
    }
    
    @IBAction func animateTapped(_ sender: Any) {
        
        animateMrHipster()
    }
    
    
    @IBAction func swipeHappened(_ sender: UISwipeGestureRecognizer) {
        
        if sender.direction == .left {
            animateMrHipster()
        }
        else if( sender.direction == .up){
            UIView.animate(withDuration: 3.0, delay: 0.5, usingSpringWithDamping: 1.0, initialSpringVelocity: 2.0, options: [], animations: {
                self.imageView.alpha = 1
            }) { (isCompleted) in
                print("finished")
            }
        }
        else if( sender.direction == .down){
            UIView.animate(withDuration: 3.0, delay: 0.5, usingSpringWithDamping: 1.0, initialSpringVelocity: 2.0, options: [], animations: {
                self.imageView.alpha = 0
            }) { (isCompleted) in
                print("finished")
            }
        }
    }
    
}

