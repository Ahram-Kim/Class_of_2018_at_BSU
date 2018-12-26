  
  "gst CheckingAccount.st"
  
  "Smalltalk CheckingAccount program"
  
  Account subclass: CheckingAccount 
  [ 
      init: n customer: c balance: b
      [
	  number := n.
	  customer := c.
	  balance := b
      ]
      
      accrue: rate
      [
      
      ] 
  ]



	
