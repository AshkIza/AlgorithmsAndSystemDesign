package DesignPatterns.state;
/*  sate machine: states and state transitions (operations)
 * the package can be ordered, delivered and received,
 * 
 *   ordered --> delivered --> received
 *   only state transition is next() 
 *   
 *   https://www.baeldung.com/java-state-design-pattern
 * */
public class PackageProcessingUsingState {
	
	static class Package{
		String deliveryAddress;
		PackageState state;
		

		Package(String address){
			deliveryAddress = address;
			state = new Ordered();
		}
		
		void setAddress(String address){
			deliveryAddress = address;
		}
		
		void next(){
			state.next(this);
		}
		
		void currentPackageStatus(){
			state.currentSate();
		}
		
		
	}
	
	interface PackageState{
		void next(Package context);
		void currentSate();
	}
	
	static class Ordered implements PackageState{
		@Override
		public void next(Package contextObject) {
			if(contextObject.deliveryAddress == ""){
				System.out.println(" NO delivery address. package can not be delivered. provide delivery address first.");
			}else{
				contextObject.state = new Delivered();
				System.out.println(" packaeg is getting delivered ...");
			}
		}

		@Override
		public void currentSate() {
			System.out.println(" packaeg is Ordered ");
		}
		
	}
	
	static class Delivered implements PackageState{
		
		@Override
		public void next(Package contextObject) {
			contextObject.state = new Received();
			System.out.println(" packaeg is recieved");
		}

		@Override
		public void currentSate() {
			System.out.println(" packaeg is Delivered ");
		}
	}
	
	
	static class Received implements PackageState{
	
		@Override
		public void next(Package contextObject) {
			System.out.println(" packaeg already Received at " + contextObject.deliveryAddress);
		}

		@Override
		public void currentSate() {
			System.out.println(" packaeg is Received ");
		}
	}
	
	

	public static void main(String[] args) {//client object 
		Package packageToCanada = new Package(" 1 king street west, Toronto");
		packageToCanada.next();
		packageToCanada.next();
		packageToCanada.next();
		packageToCanada.next();
		packageToCanada.currentPackageStatus();
		
		Package packageWithNOAddress = new Package("");
		packageWithNOAddress.next();
		packageWithNOAddress.next();
		packageWithNOAddress.next();
		packageWithNOAddress.currentPackageStatus();
		packageWithNOAddress.setAddress(" 22 quebec street, Montreal");
		packageWithNOAddress.next();
		packageWithNOAddress.currentPackageStatus();

		
	}

}
