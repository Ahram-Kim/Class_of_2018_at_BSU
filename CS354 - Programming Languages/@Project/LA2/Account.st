
  "gst Account.st"

  "Smalltalk Account program"
  
  Object subclass: Account 
  [
      |number|
      |customer|
      |balance|
      

      Account class
      [
          new: n customer: c balance: b
          [
              ^self new init: n customer: c balance: b
          ]
      ]

      init: n customer: c balance: b
      [
      	  ^self subclassResponsibility     
      ]

      accrue: rate 
      [
	  ^self subclassResponsibility
      ]
      
      balance
      [
	  ^balance
      ]
      
      deposit: amount 
      [
	  balance := balance + amount
      ]
      
      withdraw: amount
      [
	  balance := balance - amount
      ]
      
      asString
      [
	  ^number asString, ':', customer toString, ':', balance asString
      ]
  
  ]
