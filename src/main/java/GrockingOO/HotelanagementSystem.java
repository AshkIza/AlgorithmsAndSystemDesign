package GrockingOO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class HotelanagementSystem {
	// Data structures
	static class Account{
		String name;
		String address;
		boolean isStaff;
		public Account(String name, String address, boolean isStaff) {
			super();
			this.name = name;
			this.address = address;
			this.isStaff = isStaff;
		}
	}
	static class Staff extends Account{
		Integer staffId;
		Role role;
		public Staff(String name, String address, Role role) {
			super(name, address, true);
			this.role = role;
			this.staffId = HotelManagement.getNextStaffId();
		}
		enum Role{
			MANAGER, RECEPTIONIST,HOUSEKEEPER,SERVER
		}
	}
	static class Manager extends Staff {
		public Manager(String name, String address) {
			super(name, address, Role.MANAGER);
		}
		void hire(String name, String address, Role role){
			HotelManagement.registerStaffAccount(name, address, role);
		}
	}
	static class Receptionist extends Staff {
		List<Guest> guestWiating = new ArrayList<>();//waiting to checkin
		public Receptionist(String name, String address) {
			super(name, address, Role.RECEPTIONIST);
		}
		void checkin(String name, String address){
			Optional<Guest> oGuest = HotelManagement.searchGuest(name, address);
			oGuest.ifPresent( guest -> {
				Optional<Booking> oBooking = oGuest.get().guestBookings.stream()
						.filter( booking -> booking.isCheckinForToday()).findAny();
				oBooking.ifPresent( booking -> {
					Room room = booking.room;
					guest.removeBooking(booking);
					room.removeBooking(booking);
					guest.currentReservation = booking;
					room.currentReservation = booking;
					if(Room.RoomStatus.AVAILABLE.equals(room.status)) {
						room.keySerailNumber = HotelManagement.getUUID();//issue key
						room.status = Room.RoomStatus.OCCUPIED;//room is geting occupied by the guest
						System.out.println(String.format(" Room %d has been assigned to guest % with the key %s.", room.roomNumber, guest.name,room.keySerailNumber));
						return;
					}else if(Room.RoomStatus.NOT_READY.equals(room.status)) {
						guestWiating.add(guest);
						System.out.println(" Room is not ready, please wait!");
						return;
					}else {
						System.err.println(" There is a problem with the current booking (room over-booked). we asign another room to you");
						return;
					}
				});
			});
			System.err.println(String.format(" can not checkin customer %s. no record found in our system for this person", name));		
		}
		void checkout(String name, String address){
			Optional<Guest> oGuest = HotelManagement.searchGuest(name, address);
			oGuest.ifPresent( guest -> {
				Booking currentReservation = guest.currentReservation;
				Room room = currentReservation.room;
				Billing billing = guest.billing;
				room.roomserviceRequests.forEach( task -> {
					double bill = task.getBillingAmmount();
					if(bill > 0.0) {
						billing.addAmount(bill, String.format(" ( %s : %d ) ", task.type, bill));
					}
				});
				if(billing.outstandingBalance > 0.0) {
					billing.makePayment(billing.outstandingBalance);
					System.out.println(String.format("guest %s payed %d and checked out", guest.name, billing.outstandingBalance));
					guest.notifications.add(billing.issueInvoice());//prepare invoice for guest

				}
				guest.currentReservation = null;
				room.currentReservation = null;
				room.keySerailNumber = null;//return key
				room.houseKeepingRequests.forEach(hk -> {
						if(Task.TaskStatus.FULLFILLED.equals(hk.status)) {
							HotelManagement.addtoTaskLog(hk);
						}
				});
				room.roomserviceRequests.forEach(rs -> {
					if(Task.TaskStatus.FULLFILLED.equals(rs.status)) {
						HotelManagement.addtoTaskLog(rs);
					}
				});
				room.houseKeepingRequests.clear();
				room.roomserviceRequests.clear();
				room.status = Room.RoomStatus.NOT_READY;
				HotelManagement.newTask(new Task(room.roomNumber, "please clean the room for check-in", Task.TaskType.CLEANING));
			});
		}
		//this room is ready now, issue key for the guest
		void notify(Room room) {
			if(room != null && room.currentReservation != null && room.currentReservation.guest!= null) {
				if(guestWiating.contains(room.currentReservation.guest)) {
					room.keySerailNumber = HotelManagement.getUUID();//issue key
					room.status = Room.RoomStatus.OCCUPIED;//room is geting occupied by the guest
					System.out.println(String.format(" Room %d has been assigned to guest % with the key %s.", room.roomNumber, room.currentReservation.guest.name,room.keySerailNumber));
					return;
				} 
			}
		}
	}
	static class ServiceStaff extends Staff{
		public ServiceStaff(String name, String address, Role role) {
			super(name, address, role);
		}
		Queue<Task> pendingTasks;
		Task currentTask;
		void assignNewTask(Task task){
			pendingTasks.add(task);
			if(currentTask == null || Task.TaskStatus.FULLFILLED.equals(currentTask.status)) {
				currentTask = pendingTasks.remove();
			}
		}
		void run(){
			try {
				Thread.sleep(500L);//each task take 0.5 sec
				currentTask.runTask(staffId);
				HotelManagement.notifyTaskFulfilled(currentTask);
			}catch(Exception e) {
			}
		}
	}
	static class Housekeeper extends ServiceStaff {
		public Housekeeper(String name, String address) {
			super(name, address, Role.HOUSEKEEPER);
		}
	}
	static class Server extends ServiceStaff {
		public Server(String name, String address) {
			super(name, address, Role.SERVER);
		}
	}
	static class Task{
		static Map<TaskType, Double> pricingMap = new HashMap<>();
		static {
			pricingMap.put(TaskType.CLEANING, 0.00);
			pricingMap.put(TaskType.AMENITY_REQ, 5.00);
			pricingMap.put(TaskType.FOOD_REQ, 20.00);
		}
		Integer assingedStaff;
		Integer roomId;
		String description;
		TaskStatus status;
		TaskType type;
		Double billingAmmout;
		public Task(Integer roomId, String description, TaskType type) {
			this.roomId = roomId;
			this.description = description;
			this.type = type;
			this.billingAmmout = 0.0;
			this.status = TaskStatus.PENDING;
		}
		void runTask(Integer assingedStaff) {
			this.assingedStaff = assingedStaff;
			status = TaskStatus.FULLFILLED;
		}
		double getBillingAmmount() {
			if(TaskStatus.PENDING.equals(status)) {
				return 0.0;
			}
			return pricingMap.get(type);
		}
		enum TaskStatus{
			PENDING, FULLFILLED
		}
		enum TaskType{
			AMENITY_REQ, FOOD_REQ, CLEANING
		}
	}
	static class Guest extends Account{
		List<Booking> guestBookings = new ArrayList<>();
		Booking currentReservation;
		Billing billing;
		List<String> notifications = new ArrayList<>();
		public Guest(String name, String address) {
			super(name, address, false);
		}
		boolean isAnyRoomAvailable(Room.RoomType roomtype, LocalDate from, LocalDate to){
			return HotelManagement.isAnyRoomAvailable(roomtype, from, to);
		}
		void makeBooking(Room.RoomType roomtype, LocalDate from, LocalDate to) {
			HotelManagement.makeBooking(this, roomtype, from, to);
		}
		void removeBooking(Booking booking){
			Iterator<Booking> bit = guestBookings.iterator();
			while(bit.hasNext()) {
				Booking b = bit.next();
				if(b.equals(booking)) {
					bit.remove();
				}
			}
		}
		void cancelBooking(Booking booking) {
			HotelManagement.cancelBooking(booking);
		}
		void orderFood(String comment) {
			requestTask(comment, Task.TaskType.FOOD_REQ);
		}
		void orderAmenity(String comment) {
			requestTask(comment, Task.TaskType.AMENITY_REQ);
		}
		void cleantheRoom(String comment) {
			requestTask(comment, Task.TaskType.CLEANING);
		}
		void requestTask(String comment, Task.TaskType type) {
			if(currentReservation != null && currentReservation.room != null) {
				HotelManagement.newTask(new Task(currentReservation.room.roomNumber, comment, type));
			}
		}
	}
	static class Room {
		RoomType type;
		RoomStatus status;
		Integer roomNumber;//unique/immutable
		UUID keySerailNumber;//unique/mutable
		List<Booking> roomBookings = new ArrayList<>();
		Booking currentReservation;// null if currently not occupied(not checked-in)
		List<Task> houseKeepingRequests = new ArrayList<>();// fullfilled requests
		List<Task> roomserviceRequests = new ArrayList<>();
		public Room(RoomType type) {
			this.type = type;
			status = RoomStatus.AVAILABLE;
			this.roomNumber = HotelManagement.getNextRoomId();
		}
		void removeBooking(Booking booking){
			Iterator<Booking> bit = roomBookings.iterator();
			while(bit.hasNext()) {
				Booking b = bit.next();
				if(b.equals(booking)) {
					bit.remove();
				}
			}
		}
		void notify(Task task) {
			if(Task.TaskType.CLEANING.equals(task.type)) {
				houseKeepingRequests.add(task);
				if(RoomStatus.NOT_READY.equals(status)) {
					HotelManagement.getInstance().receptionist.notify(this);
				}
			}else {
				roomserviceRequests.add(task);
			}
		}
		enum RoomType{
			STANDARD, DELUEX, FAMILY
		}
		enum RoomStatus{
			OCCUPIED, AVAILABLE, NOT_READY
		}
	}
	static class Booking {
		Bookingstatus status;
		LocalDate checkIn;
		LocalDate checkout;
		Guest guest;
		Room room;
		public Booking(LocalDate checkIn, LocalDate checkout, Guest guest, Room room) {
			this.checkIn = checkIn;
			this.checkout = checkout;
			this.guest = guest;
			this.room = room;
			status = Bookingstatus.RESERVED;
		}
		enum Bookingstatus{
			RESERVED, CHECKED_IN
		}
		boolean isCheckinForToday() {
			LocalDateTime today = LocalDate.now().atStartOfDay();
			return today.isEqual(checkIn.atStartOfDay());
		}
		boolean isAvailable(LocalDate from, LocalDate to){
			return from.isAfter(checkout) || to.isBefore(checkIn);
		}
		private boolean shouldNotify(LocalDateTime today) {
			return today.isEqual(checkIn.atStartOfDay()) || today.isEqual(checkout.atStartOfDay());
		}
		void notifyGuest() {
			LocalDateTime today = LocalDate.now().atStartOfDay();
			if(shouldNotify(today)) {
				String notificationMessage = today.isEqual(checkIn.atStartOfDay()) ? 
						"you have a hotel check-in for today, " : " you have a hotel check-out for today, ";
				notificationMessage += toString();
				guest.notifications.add(notificationMessage);
			}
		}
		@Override
		public
		String toString() {
			return "Booking Details[checkIn:" + checkIn.toString() +", checkout:" + checkout.toString() + 
					", guest:" + guest.name + "roomType:" +  room.type;
		}
	}
	static class Billing {
		Double outstandingBalance;
		Integer creditCardinfo;//encrypted
		String checkPayableTo;
		String invoice;
		public Billing(Integer creditCardinfo, String checkPayableTo) {
			this.creditCardinfo = creditCardinfo;
			this.checkPayableTo = checkPayableTo;
			this.outstandingBalance = 0.0;
		}
		void addAmount(double amount, String desc){
			outstandingBalance += amount;
			invoice += desc;
		}
		void makePayment(double payment) {
			outstandingBalance -= payment;
		}
		String issueInvoice(){
			invoice += "\n total amount payable : " + outstandingBalance + " $";
			return invoice;
		}
		void Printcheck() {
			System.out.println(" check payable to " + checkPayableTo);
		}
		void makeOnlinePayment() {
			System.out.println(" online paymnet payed for credit card number " + creditCardinfo);
		}
	}
	static class Hotelinventory{
		List<Booking> bookings;//for record keeping and notifications
		Manager manager;//simple case with 1 manager/ 1 receptionist
		Receptionist receptionist;
		List<Housekeeper> housekeepers = new ArrayList<>();
		List<Server> servers = new ArrayList<>();
		Queue<Task> pendingHouseKeepingTasks = new PriorityQueue<>();
		Queue<Task> pendingRoomServiceTasks = new PriorityQueue<>();
		List<Task> tasksLog = new ArrayList<>();
		// indexed data
		Map<Integer, Room> rooms = new HashMap<>();
		Map<String, Guest> guests = new HashMap<>();
		Map<Room.RoomType, List<Integer>> roomsByetype;// search by room type and avalability
		void newTask(Task task){
			if(Task.TaskType.CLEANING.equals(task.type)) {
				pendingHouseKeepingTasks.add(task);
			}else {
				pendingRoomServiceTasks.add(task);
			}			
			assignTasks();
		}
		void assignTasks() {
			if(!pendingHouseKeepingTasks.isEmpty()) {
				Optional<Housekeeper> oHouseKeeper = findAvailableHousekeeper();
				while(!pendingHouseKeepingTasks.isEmpty() && oHouseKeeper.isPresent()) {
					Task nextTask = pendingHouseKeepingTasks.remove();
					oHouseKeeper.get().assignNewTask(nextTask);
					oHouseKeeper = findAvailableHousekeeper(); 
				}
			}
			if(!pendingRoomServiceTasks.isEmpty()) {
				Optional<Server> oServer = findAvailableServer();
				while(!pendingRoomServiceTasks.isEmpty() && oServer.isPresent()) {
					Task nextTask = pendingRoomServiceTasks.remove();
					oServer.get().assignNewTask(nextTask);
					oServer = findAvailableServer();
				}
			}
		}
		Optional<Housekeeper> findAvailableHousekeeper(){
			return housekeepers.stream().filter(hk -> hk.pendingTasks.size() < 2).findFirst();
		}
		Optional<Server> findAvailableServer(){
			return servers.stream().filter(sr -> sr.pendingTasks.size() < 2).findFirst();
		}
		boolean checkAvailablity(Room.RoomType roomtype, LocalDate from, LocalDate to){
			if(roomsByetype.containsKey(roomtype)) {
				Stream<Room> allRooms = roomsByetype.get(roomtype).stream().map(roomId -> rooms.get(roomId));
				return allRooms.anyMatch(room -> Available(room, from, to));
			}
			return false;
		}
		private Optional<Room> searchForRoom(Room.RoomType roomtype, LocalDate from, LocalDate to){
			if(roomsByetype.containsKey(roomtype)) {
				Stream<Room> allRooms = roomsByetype.get(roomtype)
						.stream().map(roomId -> rooms.get(roomId));
				return allRooms.filter(room -> Available(room, from, to)).findFirst();
			}
			return Optional.empty();
		}
		boolean makeBooking(Guest guest, Room.RoomType roomtype, LocalDate from, LocalDate to) {
			Optional<Room> searchForRoom = searchForRoom(roomtype, from, to);
			if(searchForRoom.isPresent()) {
				Room room = searchForRoom.get();// lock the system for accidental concurrent access
				Booking booking = new Booking(from, to, guest, room);
				//update room and guest with this booking
				room.roomBookings.add(booking);
				guest.guestBookings.add(booking);
				guest.notifications.add(" booking make under your name. " + booking.toString());
				bookings.add(booking);
				System.out.println(" booking make for : " + guest.name + " " + booking.toString());
				return true;
			}else {
				System.err.println(" could NOT make a booking for : " + guest.name + " change your search criteria");
				return false;// no room availble based on this criteria
			}
		}
		void notifyBookings(){// in real scenario, a cron job runs this once every day
			bookings.forEach(booking -> booking.notifyGuest());
		}
		static boolean Available(Room room, LocalDate from, LocalDate to)  {
			if(room.roomBookings.isEmpty()) {
				return true;
			}
			return room.roomBookings.stream().anyMatch(booking -> booking.isAvailable(from, to));
		}
	}
	static class HotelManagement {// portal to the system
		static Hotelinventory instance;//singlton
		static int nextRoomId = 1;
		static int nextStaffId = 1;
		static Hotelinventory getInstance() {
			if(instance == null) {
				instance = new Hotelinventory();
			}
			return instance;
		}
		static Integer getNextRoomId() {
			return Integer.valueOf(nextRoomId++);
		}
		static Integer getNextStaffId() {
			return Integer.valueOf(nextStaffId++);
		}
		static UUID getUUID() {//for key ids
			return UUID.randomUUID();
		}
		
		static boolean isAnyRoomAvailable(Room.RoomType roomtype, LocalDate from, LocalDate to){
			return getInstance().checkAvailablity(roomtype, from, to);
		}// anyone (even a un-registered guest) can search for rooms
		static boolean makeBooking(Guest guest, Room.RoomType roomtype, LocalDate from, LocalDate to) {
			return getInstance().makeBooking(guest, roomtype, from, to);
		}// either registered guest or receptionist can make a booking
		static void cancelBooking(Booking booking) {
			Guest guest = booking.guest;
			Room room = booking.room;
			guest.removeBooking(booking);
			room.removeBooking(booking);
			getInstance().bookings.remove(booking);
		}// either registered guest or receptionist can cancel a booking
		static Optional<Guest> searchGuest(String name, String address){
			return getInstance().guests.entrySet().stream()
				.filter(entry -> entry.getKey().equals(name) && entry.getValue().address.equals(address))
				.map(entry -> entry.getValue()).findAny();
		}
		// account management services
		static void registerGuestAccount(String name, String address) {
			if(!getInstance().guests.containsKey(name)) {
				getInstance().guests.put(name, new Guest(name, address));
			}
		}
		static Optional<Guest> loginAsGuest(String guestname) {
			if(getInstance().guests.containsKey(guestname)) {
				return Optional.of(getInstance().guests.get(guestname));
			}
			return Optional.empty();
		}
		Optional<Staff> loginAsStaff(String name, Staff.Role role) {
			Optional<Staff> staff = Optional.empty();
			switch(role) {
			case SERVER:
				staff =  getInstance().servers.stream().filter(server -> server.name.equals(name)).map(o -> (Staff)o).findFirst();
			case HOUSEKEEPER :
				staff =  getInstance().housekeepers.stream().filter(hk -> hk.name.equals(name)).map(o -> (Staff)o).findFirst();
			case RECEPTIONIST :
				staff =  Optional.of(getInstance().receptionist.name.equals(name) ? getInstance().receptionist : null);
			case MANAGER : 	
				staff =  Optional.of(getInstance().manager.name.equals(name) ? getInstance().manager : null);
			}
			if(!staff.isPresent()) {
				System.err.println(String.format(" unauthorized member login for %s. can NOT access", name));
			}
			return staff;
	   }
	   static void registerStaffAccount(String name, String address, Staff.Role role) {
			switch(role) {
				case SERVER:
					getInstance().servers.add(new Server(name, address));
				case HOUSEKEEPER :
					getInstance().housekeepers.add(new Housekeeper(name, address));
				case RECEPTIONIST :
					getInstance().receptionist = new Receptionist(name, address);
				case MANAGER : 	
					getInstance().manager = new Manager(name, address);
			}
		}
	    static void addRoom(Room.RoomType type) {
	    	Room room = new Room(type);
	    	getInstance().rooms.put(room.roomNumber, room);
	    	if(getInstance().roomsByetype.containsKey(type)) {
	    		getInstance().roomsByetype.get(type).add(room.roomNumber);
	    	}else {
	    		getInstance().roomsByetype.put(type, Arrays.asList(Integer.valueOf(room.roomNumber)));
	    	}
	    }
	    //Task Management services
	    static void addtoTaskLog(Task task) {
	    	getInstance().tasksLog.add(task);
	    }
	    static void newTask(Task task) {
	    	getInstance().newTask(task);
	    }
	    static void assignTasks() {
	    	getInstance().assignTasks();
	    }
		static void notifyTaskFulfilled(Task task) {
			Room room = getInstance().rooms.get(task.roomId);
			room.notify(task);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
