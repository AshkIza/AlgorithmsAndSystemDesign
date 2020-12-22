package DesignPatterns.Mediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import DesignPatterns.Mediator.AirTrafficControlExample.Plane;

/*  https://www.javacodegeeks.com/2019/09/mediator-design-pattern-java.html
 * 
 * Mediator acts like router / control center. manages the objects interactions.
 * it encapsulates all the communications/interaction of colleague objects. 
 * All other functionalities should stay in the colleague objects (otherwise mediator becomes diety object)
 * 
 * */
public class AirTrafficControlExample {
	
	public static class UniqueID {
		static int currentId = 0;
		public static int generateId() {
			currentId++;
			return currentId;
		}
	}
	
	public static class Runway {
		String name;
		Integer id;
		Runway(String name){
			this.name = name;
			id = new Integer(UniqueID.generateId());
		}
		public Integer getId() {
			return id;
		}
	}
	
	// Colleague
	public static class Plane {
		AirTrafficControl mediator;
		String flightNumber;
		Plane(String flightNumber, AirTrafficControl mediator){
			this.flightNumber = flightNumber;
			this.mediator = mediator;//contacted with the ATC
		}
		
		public void startLanding() {
			mediator.startLanding(this);
		}
		
		public void landingFinished() { // Taxiing
			mediator.landingFinished(this);
		}
	}
	
	public static class AirTrafficControl {
		Queue<Plane> planes = new LinkedList<>();//all planes queueing to land
		Map<String, Integer> busyRunways = new HashMap<>();
		List<Integer> freeRunways = new ArrayList<>();
		
		AirTrafficControl(){
		}
		
		void registerRunway(Runway runway) {
			freeRunways.add(runway.getId());
		}
		
		public void startLanding(Plane plane) {
			System.out.println(plane.flightNumber + " has requestedto land");
			planes.add(plane);//added to the end of queue
			assignFreeRunwaysToPlanes();
			if(freeRunways.size() == 0 && planes.contains(plane)) {
				System.out.println(" no runway available for " + plane.flightNumber + " , please wait!");
			} 
		}
		
		public void landingFinished(Plane plane) { // Taxiing
			Integer runwayId = busyRunways.get(plane.flightNumber);
			if(runwayId != null) {
				busyRunways.remove(plane.flightNumber);
				freeRunways.add(runwayId);
				System.out.println( plane.flightNumber + " finished landing on runway " + runwayId + " ...taxiing");
				assignFreeRunwaysToPlanes();
			}
		}
		
		private void assignFreeRunwaysToPlanes() {
			while(freeRunways.size() != 0 && planes.size() != 0) {
				Integer runwayId = freeRunways.remove(freeRunways.size() - 1);
				Plane nextPlane = planes.remove();
				if(nextPlane != null) { // there should be plane waiting to be landed
					busyRunways.put(nextPlane.flightNumber, runwayId);
					System.out.println( nextPlane.flightNumber + " : runway " +  runwayId + " is ready ... landing");
				}
			}
		}
	}
	
	

	public static void main(String[] args) {
		
		System.out.println( " Mediator acts like router / control center. manages the objects interactions.");
		System.out.println( " it encapsulates all the communications/interaction of colleague objects.");
		System.out.println( " All other functionalities should stay in the colleague objects (otherwise mediator becomes diety object) \n");
		
		AirTrafficControl controlCenter = new AirTrafficControl();
		controlCenter.registerRunway(new Runway(" RunwayA-north east"));
		controlCenter.registerRunway(new Runway(" RunwayB-north west"));
		controlCenter.registerRunway(new Runway(" RunwayC-south"));
		
		
		Plane plane01 = new Plane("FlightNumber01", controlCenter);
		Plane plane02 = new Plane("FlightNumber02", controlCenter);
		Plane plane03 = new Plane("FlightNumber03", controlCenter);
		Plane plane04 = new Plane("FlightNumber04", controlCenter);
		Plane plane05 = new Plane("FlightNumber05", controlCenter);
		Plane plane06 = new Plane("FlightNumber06", controlCenter);
		Plane plane07 = new Plane("FlightNumber07", controlCenter);
		
		plane01.startLanding();
		plane02.startLanding();
		plane03.startLanding();
		plane04.startLanding();
		plane05.startLanding();
		plane06.startLanding();
		plane02.landingFinished();
		plane02.landingFinished();
		plane03.landingFinished();
		plane01.landingFinished();
		plane04.landingFinished();
		plane05.landingFinished();
		plane07.startLanding();

				
		
	}

}
