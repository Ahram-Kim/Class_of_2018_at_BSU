
  "gst Customer.st"
  
  "Smalltalk Customer program"
  
  Object subclass: Customer 
  [    
      |name|
      
      Customer class
      [
	  new: n
	  [
	      ^self new init: n
	  ]
      ]
      
      init: n
      [
	  name := n
      ]
      
      toString 
      [
	  ^name asString
      ]
  ]
