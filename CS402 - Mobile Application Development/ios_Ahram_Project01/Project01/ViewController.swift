//
//  ViewController.swift
//  Project01
//
//  Created by ahramkim on 10/23/18.
//  Copyright © 2018 ahramkim. All rights reserved.
//

import UIKit
import CoreData

// Global Variables and Constants
let appDelegate = UIApplication.shared.delegate as? AppDelegate

class ViewController: UIViewController{
    // Outlets
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var searchBar: UISearchBar!
    
    //Variables
    var todosArray = [TodoList]()
    
    // Constants
    let cellid = "cell"
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.

        cellDelegates()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        updatingData()
        getData { (done) in
            if done {
                if todosArray.count > 0
                {
                    tableView.isHidden = false
                }
                else
                {
                    tableView.isHidden = true
                }
            }
        }
        tableView.reloadData()
    }
    
    func updatingData() {
        getData{ (done) in
            if done
            {
                if todosArray.count > 0
                {
                    tableView.isHidden = false
                }
                else
                {
                    tableView.isHidden = true
                }
            }
        }
    }
    
    func cellDelegates()
    {
        tableView.delegate = self
        tableView.dataSource = self
        tableView.isHidden = true
        searchBar.delegate = self
    }
    

}

extension ViewController: UITableViewDataSource, UITableViewDelegate
{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
    {
        return todosArray.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: cellid, for: indexPath) as! TableViewCell
        let todos = todosArray[indexPath.row]
        cell.vLavel.text = todos.todo

        if todos.todoStatus == true
        {
            cell.backgroundColor = #colorLiteral(red: 0.9607843161, green: 0.7058823705, blue: 0.200000003, alpha: 1)
            cell.textLabel?.textColor = #colorLiteral(red: 1.0, green: 1.0, blue: 1.0, alpha: 1.0)
        }
        return cell
    }
    
    func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        return true
    }
    
    func tableView(_ tableView: UITableView, editingStyleForRowAt indexPath: IndexPath) -> UITableViewCell.EditingStyle {
        return .none
    }
    
    func tableView(_ tableView: UITableView, editActionsForRowAt indexPath: IndexPath) -> [UITableViewRowAction]? {
        let delete = UITableViewRowAction(style: .destructive, title:  "Delete") { (action, indexPath) in
            self.deleteData(indexPath: indexPath)
            self.updatingData();
            tableView.deleteRows(at: [indexPath], with: .automatic)
        }
        
        let todoStatus = UITableViewRowAction(style: .normal, title: "Completed") { (action, indexPath) in
            self.updateTodoStatus(indexPath: indexPath)
            self.updatingData()
            tableView.reloadRows(at: [indexPath], with: .automatic)
        }
        todoStatus.backgroundColor = #colorLiteral(red: 0.1019607857, green: 0.2784313858, blue: 0.400000006, alpha: 1)
        delete.backgroundColor = #colorLiteral(red: 0.7450980544, green: 0.1568627506, blue: 0.07450980693, alpha: 1)
        return [delete, todoStatus]
    }
}

extension ViewController: UISearchDisplayDelegate, UISearchBarDelegate
{
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String)
    {
        if searchText != " "
        {
            var predicate: NSPredicate = NSPredicate()
            predicate = NSPredicate(format: "todo contains[c] '\(searchText)'")
            let appDelegate = UIApplication.shared.delegate as! AppDelegate
            let context = appDelegate.persistentContainer.viewContext
            let getRequest = NSFetchRequest<NSFetchRequestResult>(entityName: "TodoList")
            getRequest.predicate = predicate
            
            do
            {
                todosArray = try context.fetch(getRequest) as! [TodoList]
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
            let getRequest = NSFetchRequest<NSManagedObject>(entityName: "TodoList")
            
            do
            {
                todosArray = try context.fetch(getRequest) as! [TodoList]
            }
            catch
            {
                print("Failed to searching data")
            }
        }
        tableView.reloadData()
    }}

extension ViewController
{
    func getData(completion: (_ complete: Bool) -> ())
    {
        guard let managedContext = appDelegate?.persistentContainer.viewContext else { return }
        let request = NSFetchRequest<NSFetchRequestResult>(entityName: "TodoList")
        
        do
        {
            todosArray = try managedContext.fetch(request) as! [TodoList]
            print("Fetch Data!!")
            completion(true)
        }
        catch
        {
            print("Failed to fetch data: ", error.localizedDescription)
            completion(false)
        }
    }
    
    func deleteData(indexPath: IndexPath) {
        guard let managedContext = appDelegate?.persistentContainer.viewContext else { return }
        managedContext.delete(todosArray[indexPath.row])
        
        do
        {
            try managedContext.save()
            print("Deleted successfully")
        }
        catch
        {
            print("Failed to delete data : ", error.localizedDescription)
        }
    }
    
    func updateTodoStatus(indexPath: IndexPath)
    {
        guard let managedContext = appDelegate?.persistentContainer.viewContext else { return }
        let todo = todosArray[indexPath.row]
        
        if todo.todoStatus == true
        {
            todo.todoStatus = false
        }
        else
        {
            todo.todoStatus = true
        }
        
        do
        {
            try managedContext.save()
            print("Update Status successfully")
        }
        catch
        {
            print("Failed to update status : ", error.localizedDescription)
        }
    }

}

