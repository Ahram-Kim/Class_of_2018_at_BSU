//
//  ReviewTabViewController.swift
//  Project03
//
//  Created by ahramkim on 12/3/18.
//  Copyright © 2018 ahramkim. All rights reserved.
//

import UIKit
import CoreData

let appDelegate = UIApplication.shared.delegate as? AppDelegate

class ReviewTabViewController: UIViewController
{
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var searchBar: UISearchBar!
    
    var reviews = [Review]()
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
        
        let fetchRequest: NSFetchRequest<Review> = Review.fetchRequest()
        
        do
        {
            let review = try PersistenceService.context.fetch(fetchRequest)
            self.reviews = review
            self.tableView.reloadData()
        }
        catch{}
        searchBar.delegate = self
    }
    
    @IBAction func plusClicked()
    {
        let alert = UIAlertController(title: "Add your opinion", message: nil, preferredStyle: .alert)
        
        alert.addTextField { (textField) in
            textField.placeholder = "Name"
        }
        
        alert.addTextField { (textField) in
            textField.placeholder = "Your Opinion"
        }
        
        let action = UIAlertAction(title: "Post", style: .default) { (_) in
            let name = alert.textFields![0].text!
            let opinion = alert.textFields![1].text!
            
            let review = Review(context: PersistenceService.context)
            
            review.rName = name
            review.rOpinion = opinion
            
            PersistenceService.saveContext()
            
            self.reviews.append(review)
            self.tableView.reloadData()
        }
        alert.addAction(action)
        present(alert, animated: true, completion: nil)
    }
    
}

extension ReviewTabViewController: UITableViewDataSource
{
    func numberOfSections(in tableView: UITableView) -> Int
    {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return reviews.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = UITableViewCell(style: .subtitle, reuseIdentifier: nil)
        cell.textLabel?.text = reviews[indexPath.row].rOpinion
        cell.detailTextLabel?.text = reviews[indexPath.row].rName
        return cell
    }
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath)
    {
        guard editingStyle == .delete else { return }
        reviews.remove(at: indexPath.row)
        
        tableView.deleteRows(at: [indexPath], with: .automatic)
    }
    
}


extension ReviewTabViewController: UISearchDisplayDelegate, UISearchBarDelegate
{
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String)
    {
        if searchText != " "
        {
            var predicate: NSPredicate = NSPredicate()
            predicate = NSPredicate(format: "rOpinion contains[c] '\(searchText)'")
            
            let appDelegate = UIApplication.shared.delegate as! AppDelegate
            let context = appDelegate.persistentContainer.viewContext
            let getRequest = NSFetchRequest<NSFetchRequestResult>(entityName: "Review")
            getRequest.predicate = predicate
            
            do
            {
                reviews = try context.fetch(getRequest) as! [Review]
                print("Searched successfully")
            }
            catch
            {
                print("Failed to get Data: ", error.localizedDescription)
            }
        }
        else
        {
            let appDelegate = UIApplication.shared.delegate as! AppDelegate
            let context = appDelegate.persistentContainer.viewContext
            let getRequest = NSFetchRequest<NSManagedObject>(entityName: "Review")
            
            do
            {
                reviews = try context.fetch(getRequest) as! [Review]
            }
            catch
            {
                print("Failed to searching data")
            }
        }
        tableView.reloadData()
    }
}

