//
//  SecondViewController.swift
//  FirstRealApp
//
//  Created by Michael Ziray on 9/11/18.
//  Copyright © 2018 BSU. All rights reserved.
//

import UIKit

class ListViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        RestaurantsController.sortRestaurants()
        
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return RestaurantsController.numberOfRestaurants()
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell:UITableViewCell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
        
        cell.textLabel?.text = "Restaurant"
        
        return cell
    }
    
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if( indexPath.row % 2 == 0){
            let alertController = UIAlertController(title: "Title - Selected", message: "You selected \(indexPath.row)", preferredStyle: .alert)
            
            alertController.addAction(UIAlertAction(title: "Dismiss", style: .default, handler: { (alertAction) in
                // Do something interesting
            }))
            
            self.present(alertController, animated: true, completion: nil)
        }
    }
    
}

