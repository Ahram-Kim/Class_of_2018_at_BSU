
  "gst SavingsAccount.st"
  
  "Smalltalk SavingsAccount program"
  
  Account subclass: SavingsAccount
  [
      |interest|
 
      init: n customer: c balance: b 
      [
	 
	  number := n.
	  customer := c.
	  balance := b.
	  interest := 0.0
      ]
      
      accrue: rate 
      [
	  balance := balance + (balance * rate).
	  interest := interest + (balance * rate)
      ]
  ]
