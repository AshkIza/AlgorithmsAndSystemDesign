package DesignPatterns.ChainOfResponsibility;

public class CallCentreSupportService {
	
	enum ServiceLevel {
		LEVEL_ONE, LEVEL_TWO, LEVEL_THREE, LEVEL_FOUR, INVALID_REQUEST
	}
	
	public static abstract class Handler {
		Handler next;
		
		public Handler(Handler next) {
			super();
			this.next = next;
		}
		
		void next(ServiceRequest request){
			if(next != null) {
				 next.handleRequest(request);
			}
		}

		abstract void handleRequest(ServiceRequest request);
	}
	
	public static class ServiceRequest{
		ServiceLevel severityLevel;
		String processingResultSummary;
		public ServiceRequest(ServiceLevel severityLevel) {
			super();
			this.severityLevel = severityLevel;
		}
		public ServiceLevel getSeverityLevel() {
			return severityLevel;
		}
		
		public String getProcessingResultSummary() {
			return processingResultSummary;
		}
		public void setProcessingResultSummary(String processingResultSummary) {
			this.processingResultSummary = processingResultSummary;
		}
		
	}
	
	
	public static class CallCentreSupport extends Handler {//start of chain
		public CallCentreSupport(Handler next) {
			super(next);
		}

		void handleRequest(ServiceRequest request) {
			
			if(request == null) {
				 System.out.println("Invalid Request (null)");
			} else if(ServiceLevel.INVALID_REQUEST.equals(request.getSeverityLevel())) {
				 System.out.println("Invalid Request (INVALID_REQUEST)");
			} else {
				next(request);
			}
		}
	}
	
	public static class FrontDeskSupport extends Handler{
		public FrontDeskSupport(Handler next) {
			super(next);
		}

		@Override
		void handleRequest(ServiceRequest request) {
			 if(ServiceLevel.LEVEL_FOUR.equals(request.getSeverityLevel())) {
				 System.out.println("FrontDeskSupport handling LEVEL_FOUR request ... ");
			 }else {
				next(request);
			 }
		}
	}
	
	public static class SupervisorSupport extends Handler{
		public SupervisorSupport(Handler next) {
			super(next);
		}

		@Override
		void handleRequest(ServiceRequest request) {
			 if(ServiceLevel.LEVEL_THREE.equals(request.getSeverityLevel())) {
				 System.out.println("SupervisorSupport handling LEVEL_THREE request ... ");
			 } else if (ServiceLevel.LEVEL_TWO.equals(request.getSeverityLevel())){
				 System.out.println("SupervisorSupport handling LEVEL_TWO request (needs approval from manager too) ");
				 next(request);
			 } else {
				 next(request);
			 }
		}
	}
	
	public static class ManagerSupport extends Handler{//end of chain
		public ManagerSupport(Handler next) {
			super(next);
		}

		@Override
		void handleRequest(ServiceRequest request) {
			 if(ServiceLevel.LEVEL_TWO.equals(request.getSeverityLevel())) {
				 System.out.println("ManagerSupport approving LEVEL_TWO request ... ");
			 } else if (ServiceLevel.LEVEL_ONE.equals(request.getSeverityLevel())){
				 System.out.println("ManagerSupport handling LEVEL_ONE request ");
			 } 
		}
	}
	
	public static void main(String[] args) {
		CallCentreSupport callCentre = 
			new CallCentreSupport(new FrontDeskSupport(new SupervisorSupport(new ManagerSupport(null))));
		
		callCentre.handleRequest(new ServiceRequest(ServiceLevel.LEVEL_THREE));
		callCentre.handleRequest(new ServiceRequest(ServiceLevel.LEVEL_TWO));
		callCentre.handleRequest(null);
		callCentre.handleRequest(new ServiceRequest(ServiceLevel.LEVEL_FOUR));
		callCentre.handleRequest(new ServiceRequest(ServiceLevel.INVALID_REQUEST));
		callCentre.handleRequest(new ServiceRequest(ServiceLevel.LEVEL_ONE));
	} 
}
