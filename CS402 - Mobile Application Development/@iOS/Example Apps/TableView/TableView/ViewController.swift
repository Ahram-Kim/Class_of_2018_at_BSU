//
//  ViewController.swift
//  TableView
//
//  Created by Michael Ziray on 9/6/18.
//  Copyright © 2018 BSU. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    
    @IBOutlet weak var tableView: UITableView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        // tableView.reloadData()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        print(indexPath.row)
        print(indexPath.section)
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 100
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if let cell:UITableViewCell = tableView.dequeueReusableCell(withIdentifier: "CustomCell"){
            cell.textLabel?.text = "Something"
            
            if( indexPath.row % 2 == 1){
                cell.detailTextLabel?.text = "This is an odd row: \(indexPath.row)"
            }
            else{
                cell.detailTextLabel?.text = ""
            }
            
            return cell
        }
        else{
            // Don't do this
            return UITableViewCell()
        }
    }
    
    
    
    
    

    
}

