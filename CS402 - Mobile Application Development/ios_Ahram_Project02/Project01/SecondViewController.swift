//
//  SecondViewController.swift
//  Project01
//
//  Created by ahramkim on 10/23/18.
//  Copyright © 2018 ahramkim. All rights reserved.
//

import UIKit
import CoreData

class SecondViewController: UIViewController
{
    @IBOutlet weak var todosView: UITextView!
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        let leftSwipe = UISwipeGestureRecognizer(target: self, action: #selector(swipeAction(swipe:)))
        leftSwipe.direction = UISwipeGestureRecognizer.Direction.left
        self.view.addGestureRecognizer(leftSwipe)
    }
    
    @IBAction func saveButton(_ sender: Any)
    {
        saveData{ (done) in
            if done
            {
                print("We need to return now")
                navigationController?.popViewController(animated: true)
                self.dismiss(animated: true, completion: nil)
            }
            else
            {
                print("Try again")
            }
        }
    }
    
    func saveData(completion: (_ finished:Bool) -> ()) {
        guard let managedContext = appDelegate?.persistentContainer.viewContext else { return }
        
        let todolist = TodoList(context: managedContext)
        
        todolist.todo = todosView.text
        todolist.todoStatus = false
        
        do
        {
            try managedContext.save()
            print("Saved successfully")
            completion(true)
        }
        catch
        {
            print("Failed to save data: ", error.localizedDescription)
            completion(false)
        }
        
    }
}

extension UIViewController
{
    @objc func swipeAction(swipe: UISwipeGestureRecognizer)
    {
        switch swipe.direction.rawValue {
            case 1:
                performSegue(withIdentifier: "goLeft", sender: self)
            case 2:
                performSegue(withIdentifier: "goRight", sender: self)
            default:
                break
        }
    }
}
