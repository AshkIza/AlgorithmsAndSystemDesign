package DesignPatterns.state;

/*   state machine diagram for request processing:
 * https://robertogallea.com/posts/development/state-pattern-implementation-of-finite-state-machine-fsm-with-laravel
 *
 * */
public class OrderProcessingUsingState {
	
	static class Request{//context object which changes sate and its behaviour (based on current state)
		// aka : current state defined current behaviour
		DraftState draft = new DraftState();
		SubmittedState submitted = new SubmittedState();
		RejectedState rejected = new RejectedState();
		ProcessingState processing = new ProcessingState();
		ClosedState closed = new ClosedState();
		RequestSate currentState;
		String requestID;
		
		Request(String id){
			currentState = draft;
			requestID = id;
		}
		
		/*passing in the contexts to the state operations as local variables
		(context is instance-specific, but states are pure logic)
			
			currentState.operation(contextObject);
			   how to pass context to each state (operation)

		*/
		void submit(){
			currentState.submit(this);
		}
		void reject(){
			currentState.reject(this);
		}
		void approve(){
			currentState.approve(this);
		}
		void close(){
			currentState.close(this);
		}
		
		public void currentStatus(){
			System.out.println("requestID :" + requestID + ", current Status : " + currentState.stateName());	
		}
		
	}
	
	interface RequestSate{
		void submit(Request context);
		void reject(Request context);
		void approve(Request context);
		void close(Request context);
		String stateName();
	}
	
	static class  DraftState implements RequestSate{
		
		/* instead of  having context as  instance variables, 
		 * we can pass in the context within each state operation as local variable
		 * Hence, we can have stateless state objects (can make them singleton / thread safe)
		 * 
		 * 
		
		Request context;//to access current state of the context
		DraftState(Request request){
			context = request;
		}
		
		*/

		@Override
		public void submit(Request context) {
			System.out.println( "requestID : " + context.requestID + " submitting the request");			
			context.currentState = context.submitted; 
		}

		@Override
		public void reject(Request context) {	
			System.out.println("requestID : " + context.requestID + " submit the request first");		
		}

		@Override
		public void approve(Request context) {		
			System.out.println("requestID : " + context.requestID + " submit the request first");		
		}

		@Override
		public void close(Request context) {	
			System.out.println("requestID : " + context.requestID + " can not close a draft.");	
		}

		@Override
		public String stateName() {
			return "Draft";
		}
		
	}
	
	static class SubmittedState implements RequestSate{
		@Override
		public void submit(Request context) {
			System.out.println("requestID : " + context.requestID + " request already submitted. Reject or approve?");			
		}

		@Override
		public void reject(Request context) {		
			System.out.println("requestID : " + context.requestID + " request has been rejected");			
			context.currentState = context.rejected;
			context.currentState = context.closed;
		}

		@Override
		public void approve(Request context) {		
			System.out.println("requestID : " + context.requestID + " request has been approved. processing ...");			
			context.currentState = context.processing;
		}

		@Override
		public void close(Request context) {			
			System.out.println("requestID : " + context.requestID + " can not close request. need to approve or reject first");		
		}
		
		@Override
		public String stateName() {
			return "Submitted";
		}
		
	}
	
	static class  RejectedState implements RequestSate{
		@Override
		public void submit(Request context) {
			System.out.println("requestID : " + context.requestID + " request has been rejected! can not submit.");			
		}

		@Override
		public void reject(Request context) {		
			System.out.println("requestID : " + context.requestID + " request hasalready been rejected! can not reject again.");	
		}

		@Override
		public void approve(Request context) {			
			System.out.println("requestID : " + context.requestID + " request has already been rejected! can not approve.");	
		}

		@Override
		public void close(Request context) {		
			System.out.println("requestID : " + context.requestID + " rejected request automatically gets closed");	
		}
		
		@Override
		public String stateName() {
			return "Rejected";
		}
	}
	
	static class  ProcessingState implements RequestSate{
		@Override
		public void submit(Request context) {
			System.out.println("requestID : " + context.requestID + " processing. request already been submitted. can NOT submit again");
		}

		@Override
		public void reject(Request context) {
			System.out.println("requestID : " + context.requestID + " processing. request already been approved. can NOT reject");
		}

		@Override
		public void approve(Request context) {	
			System.out.println("requestID : " + context.requestID + " processing. request already been approved.");	
		}

		@Override
		public void close(Request context) {		
			System.out.println("requestID : " + context.requestID + " processing finished. closing the request ...");	
			context.currentState = context.closed;
		}
		
		@Override
		public String stateName() {
			return "Processing";
		}
	}
	
	
	static class  ClosedState implements RequestSate{
		@Override
		public void submit(Request context) {
			System.out.println("requestID : " + context.requestID + " already closed. can NOT submit");			
		}

		@Override
		public void reject(Request context) {	
			System.out.println("requestID : " + context.requestID + " already closed. can NOT reject");			
		}

		@Override
		public void approve(Request context) {		
			System.out.println("requestID : " + context.requestID + " already closed. can NOT approved");			
		}

		@Override
		public void close(Request context) {	
			System.out.println("requestID : " + context.requestID + " already closed. can NOT close again");			
		}
		
		@Override
		public String stateName() {
			return "Closed";
		}
	}
	
	public static void main(String[] args) {
		Request requestOrderONE = new Request("REQUEST_1");
		Request requestOrderTWO = new Request("REQUEST_2");

		requestOrderONE.close();
		requestOrderONE.approve();
		requestOrderONE.submit();
		requestOrderONE.approve();
		requestOrderTWO.submit();
		requestOrderTWO.approve();
		requestOrderONE.reject();
		requestOrderONE.currentStatus();
		requestOrderONE.close();
		requestOrderTWO.currentStatus();

		}

}
