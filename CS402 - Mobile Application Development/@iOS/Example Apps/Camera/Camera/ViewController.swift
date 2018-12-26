//
//  ViewController.swift
//  Camera
//
//  Created by Michael Ziray on 9/4/18.
//  Copyright © 2018 BSU. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIImagePickerControllerDelegate, UINavigationControllerDelegate {

    @IBOutlet weak var imageView: UIImageView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    @IBAction func choosePhotoTapped(_ sender: Any) {
        
        
        let alertController = UIAlertController(title: "Choose a profile photo", message: "Choose a photo from your camera or your photo library to upload to this app.", preferredStyle: .actionSheet)
        
        alertController.addAction(UIAlertAction(title: "Library", style: .default, handler: { (action) in
            self.choosePhoto(UIImagePickerControllerSourceType.photoLibrary)
            
            
        }))
        
        alertController.addAction(UIAlertAction(title: "Camera", style: .default, handler: { (action) in
            self.choosePhoto(UIImagePickerControllerSourceType.camera)
        }))
        
        alertController.addAction(UIAlertAction(title: "Cancel", style: .cancel, handler: { (action) in
            print("cancel")
        }))
        
        
        self.present(alertController, animated: true, completion: nil)
    }
    
    
    func choosePhoto(_ sourceType:UIImagePickerControllerSourceType ){
        
//        let someUnusedVar = 10
        _ = 10
        
        
        let imagePickerController = UIImagePickerController()
        
        imagePickerController.delegate = self as UIImagePickerControllerDelegate & UINavigationControllerDelegate
        
        if( sourceType == .camera && UIImagePickerController.isSourceTypeAvailable(UIImagePickerControllerSourceType.camera) ) {
            imagePickerController.sourceType = .camera
            imagePickerController.allowsEditing = true
        }
        else if sourceType == .photoLibrary {
             imagePickerController.sourceType = .photoLibrary
            imagePickerController.allowsEditing = true
        }
        else{
             imagePickerController.sourceType = .savedPhotosAlbum
        }
        
        
        self.present(imagePickerController, animated: true) {
            print("Animation did complete")
        }
    }
    
    
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : Any]) {
        let userChosenImage:UIImage = info[UIImagePickerControllerOriginalImage] as! UIImage
        
        self.imageView.image = userChosenImage
        
        picker.dismiss(animated: true, completion: nil)
    }
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

