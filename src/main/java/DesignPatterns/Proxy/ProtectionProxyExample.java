package DesignPatterns.Proxy;

/* Protection Proxies are used for checking certain conditions. 
 * https://www.javatpoint.com/proxy-pattern
 * Some objects or resources might need appropriate authorization for accessing them (access control policies)
 * It acts as an authorization layer to verify that whether the actual user has access the appropriate content or not
 * */
// security proxy
public class ProtectionProxyExample {
	
	public interface officeInternetAccess{
		public void grantInternetAccess();
	}
	
	public static class RealInternetAccess implements officeInternetAccess{
		String employeeEmail;
		
		RealInternetAccess(String employeeEmail){
			this.employeeEmail = employeeEmail;
		}

		@Override
		public void grantInternetAccess() {
			System.out.println(" granting internet access to employee,  " + employeeEmail );
		}
		
	}
	
	public static class ProxyInternetAccess implements officeInternetAccess{
		RealInternetAccess realInternetAccess;
		String employeeEmail;
		ProxyInternetAccess(String employeeEmail){
			this.employeeEmail = employeeEmail;
		}

		@Override
		public void grantInternetAccess() {
			if(!employeeEmail.endsWith("companyA.com")) {
				System.out.println(" unauthorized internect accessa attempt blocked"  );
			} else {
				realInternetAccess = new RealInternetAccess(employeeEmail);
				realInternetAccess.grantInternetAccess();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(" Protection proxy – help to implement security over original object."  );

		officeInternetAccess authorizedUserAccess = new ProxyInternetAccess("user@companyA.com");
		officeInternetAccess UnauthorizedUserAccess = new ProxyInternetAccess("user@companyB.com");
		authorizedUserAccess.grantInternetAccess();
		UnauthorizedUserAccess.grantInternetAccess();
		
		
	}

}
