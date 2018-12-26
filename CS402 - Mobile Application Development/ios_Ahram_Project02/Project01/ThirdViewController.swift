
//
//  ThirdViewController.swift
//  Project01
//
//  Created by ahramkim on 12/7/18.
//  Copyright © 2018 ahramkim. All rights reserved.
//

import UIKit

class ThirdViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        let rightSwipe = UISwipeGestureRecognizer(target: self, action: #selector(swipeAction(swipe:)))
        rightSwipe.direction = UISwipeGestureRecognizer.Direction.right
        self.view.addGestureRecognizer(rightSwipe)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
