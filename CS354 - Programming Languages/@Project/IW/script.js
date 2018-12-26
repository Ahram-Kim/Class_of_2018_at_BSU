// Script file for HTML integration

window.onload = function() {
    // Account class
    class Account {

        constructor(number, customer, balance) {
            this._number = number;
            this._customer = customer;
            this._balance = balance;
        }
        
        accrue(rate) {}
        
        get balance() {
            return this._balance;
        }
        
        deposit(amount) {
            this._balance += amount;
        }
        
        withdraw(amount) {
            this._balance -= amount;
        }
        
        getString() {
            var str = "";
            str += this._number + " : " + this._customer.getString() + " : $" + this._balance.toFixed(2);

            return str;
        }
    }

    // Customer class
    class Customer {

        constructor(name) {
            this._name = name;
        }

        getString() {
            return this._name;
        }
    }

    // Checking Account class
    class CheckingAccount extends Account {
        
        constructor(number, customer, balance) {
            super(number, customer, balance);
        }

        accrue(rate) {}
    }

    // Savings Account class
    class SavingsAccount extends Account {
        
        constructor(number, customer, balance) {
            super(number, customer, balance);
            this._interest = 0.0;
        }

        accrue(rate) {
            this._balance += this._balance * rate;
            this._interest += this._balance * rate;
        }
    }

    // Bank class
    class Bank {
        
        constructor() {
            this._accounts = new Array();
        }

        addAccnt(account) {
            this._accounts.push(account);
        }

        accrue(rate) {
            for(let i = 0; i < this._accounts.length; i++) {
                this._accounts[i].accrue(rate);
            }
        }

        getString() {
            var str = "";
            for(let i = 0; i < this._accounts.length; i++) {
                str += this._accounts[i].getString() + "\n";
            }

            return str;
        }
    }

    // Random account number generator function
    function getAccountNumer() {
        return Math.floor(Math.random() * (10000 - 999 + 1)) + 999;
    }



    // main driver

    // Set up new bank account information
    var accntName = prompt("Enter an account name:");
    var chkStartBal = parseFloat(prompt("Enter a starting balance for your checking account:"));
    var savStartBal = parseFloat(prompt("Enter a starting balance for your savings account:"));

    // Generate new account numbers
    var chkAccntNum = getAccountNumer();
    var savAccntNum = getAccountNumer();

    // Create new bank accounts
    var bank = new Bank();
    var customer = new Customer(accntName);
    bank.addAccnt(new CheckingAccount(chkAccntNum, customer, chkStartBal));
    bank.addAccnt(new SavingsAccount(savAccntNum, customer, savStartBal));
    bank.accrue(0.02);

    // Theses anchor to tags in the HTML index
    document.getElementById('output1').innerHTML = accntName;
    document.getElementById('output2').innerHTML = chkAccntNum;
    document.getElementById('output3').innerHTML = "$" + chkStartBal.toFixed(2);
    console.log(bank.getString());  // outputs bank information when in "inspect" or "console" mode in the browser
}