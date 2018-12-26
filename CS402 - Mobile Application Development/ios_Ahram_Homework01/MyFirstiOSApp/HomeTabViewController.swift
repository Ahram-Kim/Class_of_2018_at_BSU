//
//  HomeTabViewController.swift
//  MyFirstiOSApp
//
//  Created by ahramkim on 9/25/18.
//  Copyright © 2018 ahramkim. All rights reserved.
//

import UIKit

class HomeTabViewController: UIViewController, UIScrollViewDelegate {

    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet weak var pageControl: UIPageControl!
    
    var images: [String] = ["home", "more", "tour"]
    var frame = CGRect(x:0, y:0, width:0, height:0)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        pageControl.numberOfPages = images.count
        for index in 0..<images.count {
            frame.origin.x = scrollView.frame.size.width * CGFloat(index)
            frame.size = scrollView.frame.size
            
            let imgView = UIImageView(frame: frame)
            imgView.image = UIImage(named: images[index])
            self.scrollView.addSubview(imgView)
        }
        scrollView.contentSize = CGSize(width: (scrollView.frame.size.width * CGFloat(images.count)), height: scrollView.frame.size.height)
        scrollView.delegate = self
   }

    func scrollViewDidEndDecelerating(_ scrollView: UIScrollView) {
        let pageNum = scrollView.contentOffset.x / scrollView.frame.size.width
        pageControl.currentPage = Int(pageNum)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
