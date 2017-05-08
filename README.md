# Sources

* `src/main/java/com/cagatayyapici/cashieapp/model` - Java sources
* `src/test/java/com//com/cagatayyapici/cashieapp/model` - unit-tests 
 

---------------------------------------------------------
# Technology

* java version - `java 8`
* unit-tests - `JUnit`  
* Extra Frameworks - Apache Commons-Apache Velocity 
* ORM - Java Lamba

---------------------------------------------------------
# Test With Maven
   
        cd into project folder
        mvn test

---------------------------------------------------------
# Api Level Testing

Api Level Entry is designed with signature as follows:

 
     /**
	 * @param ApiLevel
	 *            Design
	 */
	public void addOrders(List<Order> orders) {
		for (Order item : orders) {
			addOrder(item);
		}
	}


    System Requirements:
        Java 1.8
        Apache Maven

    Building:
    Install Java 8
        http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

    Install maven
        https://maven.apache.org/
        
        
# Sample Output
        

Test output of program is as follows:

-----------------------------------------------------------------------------------

   									 Mon May 08 09:43:20 BST 2017               

                                     Number : 9
-----------------------------------------------------------------------------------
            Cold Coffee @ 1.10 each x 2 : 2.20
            Hot Sandwich @ 2.30 each x 2 : 4.60
          

		    Sub Total     :  6.80
		    Service Charge:  $testBill.serviceCharge
		    Total         :  8.16

 
    
    
    Thank you!
-----------------------------------------------------------------------------------

 