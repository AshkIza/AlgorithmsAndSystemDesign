package DesignPatterns.Proxy;


/*  Remote proxy – represent a remotely lactated object.
 * 		 To talk with remote objects, the client need to do additional work on communication over network. 
 * 	 	 A proxy object does this communication on behalf of original object and client focuses on real talk to do.
 *     A remote proxy provides a local representative for an object that resides in a different address space.
 *     This is what the "stub" code in RPC and CORBA provides.
	Virtual proxy – delay the creation and initialization of expensive objects until needed, 
		where the objects are created on demand. Hibernate created proxy entities are example of virtual proxies.
	Protection proxy – help to implement security over original object.
	 	They may check for access rights before method invocations and allow or deny access based on the conclusion.
	Smart Proxy – performs additional housekeeping work when an object is accessed by a client. (AOP)
		An example can be to check if the real object is locked before it is accessed to ensure that no other object can change it.
 * 
 * 
 * */
public class RemoteProxyExample {

	public static void main(String[] args) {

	}

}
