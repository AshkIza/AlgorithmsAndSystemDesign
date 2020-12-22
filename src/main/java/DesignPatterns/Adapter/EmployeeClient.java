package DesignPatterns.Adapter;

import java.util.ArrayList;
import java.util.List;

/* Client code to use Employee
 * 
 * Adaptor

Connecting new code to legacy -> without changing the existing contract (interface) that was produced from legacy code 
Converting a legacy interface into another interface. When one portion of our code is legacy code which we can not change.
Does NOT add any new functionality to the legacy api ( decorator patten does)
examples:
 +Arrays.asList() 
 +Streams adaptors :  list.stream()
Integer[] arrayOfInts = new Integer[]{1, 10, 100};
List<Integer> listOfInt = Arrays.asList(arrayOfInts);
 * 
 * */
public class EmployeeClient {
	
	@SuppressWarnings("unchecked")
	private static List<Employee> cache_entries = new ArrayList();
	
	
	private static void queryDB() {
		for (Employee cache_entry : cache_entries) {
			System.out.println(cache_entry.toString());
		}
	}
	

	public static void main(String[] args) {
		queryDB();

		// data entries from UI (matched with DB format EmployeeDB
		EmployeeDB employeeFromUI = new EmployeeDB("Jsmith","John", "Smith", "jsmith@yahoo.com");
		// data entries from legacy interfaces -> need to get persisted in DB (converted to EmployeeDB)
		EmployeeLDAP employeeFromLdap01 = new EmployeeLDAP("chewie", "Solo", "Han", "han@solo.com");
		EmployeeLDAP employeeFromLdap02 = new EmployeeLDAP("joey", "blank", "Han", "jblank@solo.com");
		EmployeeCSV employeeFromCSV = new EmployeeCSV("567,Sherlock,Holmes,sherlock@holmes.com");
		
		cache_entries.add(employeeFromUI);
		//cache_entries.add(employeeFromLdap01);
		//cache_entries.add(employeeFromLdap02);
		cache_entries.add(new EmployeeLDAPAdaptor(employeeFromLdap01));
		cache_entries.add(new EmployeeLDAPAdaptor(employeeFromLdap02));

		//cache_entries.add(employeeFromCSV);
		cache_entries.add(new EmployeeCSVAdaptor(employeeFromCSV));

		
		queryDB();
		
		System.out.println("\nAdptor and new code share same interface, adaptor takes legacy code as input");
		System.out.println("EmployeeDB implements Employee");
		System.out.println("EmployeeCSVAdaptor implements Employee");
		System.out.println("EmployeeLDAPAdaptor implements Employee");

		

	}

}
