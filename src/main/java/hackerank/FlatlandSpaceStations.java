package hackerank;

import java.util.Arrays;
import java.util.stream.Collectors;

/* https://www.hackerrank.com/challenges/flatland-space-stations/problem
 * Flatland is a country with a number of cities, some of which have space stations. 
 * Cities are numbered consecutively and each has a road of  length connecting it to the next city. 
 * It is not a circular route, so the first city doesn't connect with the last city. 
 * Determine the maximum distance from any city to it's nearest space station.
 * 
 * */
public class FlatlandSpaceStations {

	   static int flatlandSpaceStations(int n, int[] c) {
	        if(n == 0 || c.length == 0) return -1;
	        Arrays.sort(c);
	        int furtherStation = c[0];
	        int closerStaion = c.length > 1 ? c[1] : -1;
	        int numStationUsed = 2;
	        int maxDistance = 0;
	        for(int city = 0; city < n; city++){
	            // if going away from closer station should choose new stations
	            if(closerStaion != -1 && closerStaion - city < 0){
	                if(c.length > numStationUsed){
	                    furtherStation = closerStaion;
	                    closerStaion = c[numStationUsed];
	                    numStationUsed++;
	                }else{
	                    furtherStation = closerStaion;
	                    closerStaion = -1;
	                }
	            }
	            int d = toNearestStation(city, furtherStation, closerStaion);
	            maxDistance = (d > maxDistance) ? d : maxDistance;
	        }
	        return maxDistance;
	    }
	    
	    static int toNearestStation(int s, int furtherStation, int closerStaion){
	        if(closerStaion == -1) return Math.abs(s - furtherStation);
	        return 
	        Math.min(Math.abs(s - furtherStation), Math.abs(s - closerStaion));
	    }
	    
	public static void main(String[] args) {
		System.out.println(" when working with Arrays, always ask 'is Array Sorted?'");
		System.out.println(" if array not sorted, ask, 'can i sort it using Arrays.sort() ?'");
		
		/* https://www.geeksforgeeks.org/arrays-sort-in-java-with-examples/
		 * */
		System.out.println("\nvoid sort(int[] arr, int from_Index, int to_Index)");
		int[] arr = {13, 7, 6, 45, 21, 9, 101, 102};
		System.out.println("original array : " + Arrays.stream(arr).boxed().collect(Collectors.toList()));
		Arrays.sort(arr);
		System.out.println("after Arrays.sort(arr) : " + Arrays.stream(arr).boxed().collect(Collectors.toList()));

		
		System.out.println("\nflatlandSpaceStations(5, new int[]{0, 4})) : " + flatlandSpaceStations(5, new int[]{0, 4}));
		System.out.println("flatlandSpaceStations(6, new int[]{0, 1, 2, 4, 3, 5}) : " + flatlandSpaceStations(6, new int[]{0, 1, 2, 4, 3, 5}));

	}

}
