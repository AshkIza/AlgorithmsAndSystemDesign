package DesignPatterns.Adapter;

public class EmployeeLDAPAdaptor implements Employee{
	EmployeeLDAP legacyInstance;

	public EmployeeLDAPAdaptor(EmployeeLDAP empoloyeeLdap) {
		legacyInstance = empoloyeeLdap;
	}

	public String getId() {
		return legacyInstance.getCn();
	}

	public String getFirstName() {
		return legacyInstance.getSurname();
	}

	public String getLastName(){
		return legacyInstance.getGivenName();
	}

	public String getEmail(){
		return legacyInstance.getMail();
	}
	
	public String toString() {
		return "EmployeeDB [id=" + legacyInstance.getCn() + 
				", firstName=" + legacyInstance.getSurname() + 
				", lastName=" + legacyInstance.getGivenName() + 
				", email=" + legacyInstance.getMail()
				+ "]";
	}

}
