package DesignPatterns.Adapter;

public class EmployeeCSVAdaptor implements Employee {
	EmployeeCSV legacyInstance;
	
	EmployeeCSVAdaptor(EmployeeCSV employeeCSV){
		legacyInstance = employeeCSV;
	}

	@Override
	public String getId() {
		return String.valueOf(legacyInstance.getId());
	}

	@Override
	public String getFirstName() {
		return legacyInstance.getFirstname();
	}

	@Override
	public String getLastName() {
		return legacyInstance.getLastname();
	}

	@Override
	public String getEmail() {
		return legacyInstance.getEmailAddress();
	}
	
	@Override
	public String toString() {
		return "EmployeeDB [id=" + getId() + 
				", firstName=" + getFirstName() + 
				", lastName=" + getLastName() + 
				", email=" + getEmail()
				+ "]";
	}
}
