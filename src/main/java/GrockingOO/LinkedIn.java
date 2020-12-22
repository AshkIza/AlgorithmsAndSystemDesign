package GrockingOO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LinkedIn {
	//enums
	enum ProfileType {
		MEMBER, COMPANY, GROUP
	}
	
	
	// data structures
	static class Profile {//member / company / group
		UUID id;
		String name;
		String address;
		List<Member> followers = new ArrayList<>();//uni-directional (follower is always a Mmeber)
		public Profile(String name, String address, UUID id) {
			this.name = name;
			this.address = address;
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public String getAddress() {
			return address;
		}
		public UUID getId() {
			return id;
		}
		void addfollower(Member follower){
			if(!followers.contains(follower)) {
				followers.add(follower);
			}		
		}
		void removeFollwer(Member follower){
			if(followers.contains(follower)) {
				followers.remove(follower);
			}
		}
	}
	
	static class Company extends Profile {
		List<JobPosting> jobPostings = new ArrayList<>();
		LinkedInApplicationContext linkedInAccess;
		public Company(String name, String address, UUID id) {
			super(name, address, id);
		}
		void withLinkedinAccess(LinkedInApplicationContext access) {
			this.linkedInAccess = access;
		}
		JobPosting addjobPosting(String title, String location){
			JobPosting job = new JobPosting(title, location, id);
			jobPostings.add(job);
			linkedInAccess.jobs.put(job.id, job);
			if(linkedInAccess.jobsByname.containsKey(title)){
				linkedInAccess.jobsByname.get(title).add(job.id);
			}else {
				linkedInAccess.jobsByname.put(title, Arrays.asList(job.id));
			}
			return job;
		}
	}
	
	static class JobPosting {
		UUID id;
		UUID companyId;
		LocalDate postDate;
		String title;
		String location;
		List<UUID> appliedMembers = new ArrayList<>();
		public JobPosting(String title, String location, UUID companyId) {
			this.postDate = LocalDate.now();
			this.title = title;
			this.location = location;
			this.id = UUID.randomUUID();
			this.companyId = companyId;
		}
		void addApplication(UUID memberId) {
			appliedMembers.add(memberId);
		}
	}
	
	static class Member extends Profile{
		String password;
		String memberProfiledeatils;
		List<Member> connections = new ArrayList<>();// bi-directional
		List<String> notofications = new ArrayList<>();
		List<Message> messages = new ArrayList<>();
		List<Post> posts = new ArrayList<>();
		ProfileStats profileStats;
		List<String> recomendatios = new ArrayList<>();
		List<Post> newsFeed = new ArrayList<>();// all posts of member and its connections
		List<Member> pendingconnections = new ArrayList<>();//need to accept or reject
		List<Group> groups = new ArrayList<>();
		List<UUID> jobsApplied = new ArrayList<>();
		LinkedInApplicationContext linkedInAccess;
		
		public Member(String name, String password, String address, UUID id) {
			super(name, address, id);
			this.password = password;
		}
		void withLinkedinAccess(LinkedInApplicationContext access) {
			this.linkedInAccess = access;
		}
		void addProfileDetaails(String experiences, String education,
				String skills, String accomplishments) {
			memberProfiledeatils += "	Experiences : " + experiences + "\n";	
			memberProfiledeatils += "	Education : " + education + "\n";	
			memberProfiledeatils += "	Skills : " + skills + "\n";	
			memberProfiledeatils += "	Accomplishments : " + accomplishments;	
		}
		public void addToNewsFeed(Post post) {
			newsFeed.add(post);
		}
		List<Member> searchMember(String name){
			 return SearchService.searchProfile(name, ProfileType.MEMBER, linkedInAccess)
					 .stream().map( profile -> (Member) profile).collect(Collectors.toList());
		}
		List<Company> searchCompany(String name){
			 return SearchService.searchProfile(name, ProfileType.COMPANY, linkedInAccess)
					 .stream().map( profile -> (Company) profile).collect(Collectors.toList());
		}
		List<JobPosting> searchJobs(String companySearchKey){
			 return SearchService.searchJobs(companySearchKey, linkedInAccess);
		}
		void applyForJob(JobPosting job) {
			jobsApplied.add(job.id);
			job.addApplication(id);
		}
		void follow(Profile profile) {
			RequestAndNotificationService.followMember(this, profile);
		}
		void unfollow(Profile profile) {
			RequestAndNotificationService.unfollowMember(this, profile);
		}
		void connectionReuest(Member followee) {
			RequestAndNotificationService.sendConnectionRequest(this, followee);
		}
		void acceptPendingConnections(List<Member> pConnectionsToAccept) {
			Iterator<Member> it = pConnectionsToAccept.iterator();
			while(it.hasNext()){
				Member pConnection = it.next();
				if(pendingconnections.contains(pConnection)) {
					addToConnections(pConnection);
					it.remove();
				}
			}
		}
		void rejectPendingConnection(List<Member> pConnectionsToReject) {
			Iterator<Member> it = pConnectionsToReject.iterator();
			while(it.hasNext()){
				it.next();
				it.remove();
			}
		}
		void sendMessage(Member to, String messageBody) {
			RequestAndNotificationService.sendMessage(this, to, messageBody);
		}
		void addpost(String postContent) {
			RequestAndNotificationService.createPost(this, postContent);
		}
		void addPostComment(Member member, Post post, String commentContent) {
			 RequestAndNotificationService.addPostComment(member, post, new Comment(this.id, commentContent));
		}
		 void addPostLike(Post post, UUID liker) {
			 RequestAndNotificationService.addPostLike(post, this.id);
		}
		// utility functions
		void addToConnections(Member pConnection) {
			//connection is a bi-directional association
			addMember(connections, pConnection);
			addMember(pConnection.connections, this);
			//if I am your connection, I am following you and vice versa
			addMember(followers, pConnection);
			addMember(pConnection.followers, this);
		}
		private void addMember(List<Member> membersList, Member member) {
			if(!membersList.contains(member)) {
				membersList.add(member);
			}
		}
	}
	
	static class Message {
		UUID sender;
		LocalDate date;
		String content;
		public Message(UUID sender, String content) {
			this.sender = sender;
			this.date = LocalDate.now();
			this.content = content;
		}
		String readMessage() {
			return content;
		}
	}
	
	static class Post {
		int postId;
		String content;
		List<Comment> comments = new ArrayList<>();
		List<UUID> likes = new ArrayList<>();
		public Post(String content, int id) {
			this.content = content;
			this.postId = id;
		}
		void addComment(Comment comment) {
			comments.add(comment);
		}
		void addLike(UUID like) {
			likes.add(like);
		}
	}
	
	static class Comment {
		UUID commentor;
		String content;
		LocalDate date;
		public Comment(UUID commentor, String content) {
			super();
			this.commentor = commentor;
			this.content = content;
			this.date = LocalDate.now();
		}
	}
	
	static class ProfileStats {
	}
	
	static class Group {
	}
	

	static class AccountMgtService {// creates profiles and assign unique UUID
		static  Profile createAccountProfile(String name, String password, String address, ProfileType type,
				LinkedInApplicationContext applicationContect) {
			UUID id = UUID.randomUUID();
			switch (type) {
			case COMPANY:
				Company company =  new Company(name, address, id);
				applicationContect.companies.put(id, company);
				updateIndexedData(applicationContect.companiesByname, name, id);
				System.out.println("COMPANY " + name + " created");
				return company;
			case MEMBER:
				Member member =  new Member(name, password, address, id);
				applicationContect.members.put(id, member);
				updateIndexedData(applicationContect.membersByname, name, id);
				System.out.println("MEMBER " + name + " created");
				return member;
			default :
				return null;
			}
		}
		private static void updateIndexedData(Map<String, List<UUID>> indexedMap, String name, UUID id) {
			if(indexedMap.containsKey(name)) {
				indexedMap.get(name).add(id);
			} else {
				List<UUID> idList = Arrays.asList(id);
				indexedMap.put(name, idList);
			}
		}
	}
	
	// Posting, Messaging, connection/follower Requesting service and Notification
	static class RequestAndNotificationService {// creates posts, like/comment on posts
		// connection requests
		static void sendConnectionRequest(Member requester, Member member) {
			member.pendingconnections.add(requester);//waiting for requested to accept/reject
			member.notofications.add("new connection request! : " + requester.name + " want to get connected");
		}
		
		//follow/un-follow requests
		static void followMember(Member follower, Profile profile) {
			profile.addfollower(follower);
		}
		static void unfollowMember(Member follower, Profile profile) {
			profile.removeFollwer(follower);
		}
		
		//Message requests
		static void sendMessage(Member from, Member to, String messageBody) {
			Message msg = new Message(from.id, messageBody);
			to.messages.add(msg);
			to.notofications.add("new Message receive from : " + from.name);
		}
		
		//post/comment/like requests
		static void createPost(Member member, String post) {
			int postCount = member.posts.size();
			Post newPost = new Post(post, postCount);
			member.posts.add(newPost);// update member feed and its connections/followers newsfeed
			member.addToNewsFeed(newPost);// connection is a follower (connections subset of followers)
			member.followers.forEach(follower -> follower.addToNewsFeed(newPost));
		}
		static void addPostComment(Member member, Post post, Comment comment) {
			post.comments.add(comment);
			member.notofications.add("new post comment! : " + comment.content);
		}
		static void addPostLike(Post post, UUID liker) {
			post.likes.add(liker);
		}
	}
	
	static class SearchService{// search members, companies, jobs by name
		static List<Profile> searchProfile(String name, ProfileType type,
				LinkedInApplicationContext applicationContect) {
			List<UUID> profileIds = new ArrayList<>();
			switch (type) {
			case COMPANY:
				return (List<Profile>) applicationContect.companiesByname.get(name)
					.stream().map(id-> (Profile) applicationContect.companies.get(id)).collect(Collectors.toList());
			case MEMBER:
				return  applicationContect.membersByname.get(name)
						.stream().map(id-> (Profile) applicationContect.members.get(id)).collect(Collectors.toList());
			default :
				return null;
			}
		}
		
		static List<JobPosting> searchJobs(String jobtitle, LinkedInApplicationContext applicationContect) {
			Stream<String> searchKeyStream = applicationContect.jobsByname.keySet().stream().filter(name -> name.contains(jobtitle));
			return searchKeyStream
					.flatMap(searchKey -> applicationContect.jobsByname.get(searchKey).stream())
					.map(uuid -> applicationContect.jobs.get(uuid))
					.collect(Collectors.toList());
		}
		
	}
	
	//application contect (application layer)
	static class LinkedInApplicationContext{
		private static LinkedInApplicationContext instance;
		static LinkedInApplicationContext getInstance() {
			if(instance == null) {
				instance = new LinkedInApplicationContext();
			}
			return instance;
		}
		
		Map<UUID, Member> members = new HashMap<>();
		Map<UUID, Company> companies = new HashMap<>();
		Map<UUID, Group> groups = new HashMap<>();
		Map<UUID, JobPosting> jobs = new HashMap<>();
		// indexed data for search optimization
		Map<String, List<UUID>> membersByname = new HashMap<>();//we allow profiles with same names to exist
		Map<String, List<UUID>> companiesByname = new HashMap<>();//we allow profiles with same names to exist
		Map<String, List<UUID>> groupsByname = new HashMap<>();//we allow profiles with same names to exist
		Map<String, List<UUID>> jobsByname = new HashMap<>();//we allow jobs with same names to exist
		//services
		AccountMgtService accountMgtService;
		SearchService searchService;
		
		static Member login(String username, String password) {
			if(getInstance().membersByname.containsKey(username)) {
				Optional<UUID> idO = getInstance().membersByname.get(username).stream()
						.filter(id -> getInstance().members.get(id).password.equals(password)).findFirst();
				if(idO.isPresent()) {
					Member authorizedMem = getInstance().members.get(idO.get());
					authorizedMem.withLinkedinAccess(getInstance());
					return authorizedMem;
				}
			}
			System.out.println("Unauthorized access by " + username + ". we can NOT log you in!");
			return null;
		}
		static Company loginCompany(String companyName, String location) {
			if(getInstance().companiesByname.containsKey(companyName)) {
				Optional<UUID> idO = getInstance().companiesByname.get(companyName).stream()
						.filter(id -> getInstance().companies.get(id).address.equals(location)).findFirst();
				if(idO.isPresent()) {
					Company authorized = getInstance().companies.get(idO.get());
					authorized.withLinkedinAccess(getInstance());
					return authorized;
				}
			}
			System.out.println("Unauthorized access by " + companyName + ". we can NOT log you in!");
			return null;
		}
		static Member  registerMember(String name, String password) {
			return (Member) AccountMgtService.createAccountProfile(name, password, null, 
					ProfileType.MEMBER, getInstance());
		}
		static Company registerCompanyPage(String companyname, String companyAddress) {
				return (Company) AccountMgtService.createAccountProfile(companyname, null, 
						companyAddress, ProfileType.COMPANY, getInstance());
		}
	}
	
	static void viewMemberPage(Member mem){
		System.out.println(mem.name + " :");
		System.out.println("	Notofications ");
		mem.notofications.forEach(no -> System.out.println("		" + no));
		System.out.println("	Messages ");
		mem.messages.forEach(ms -> System.out.println("		" + ms.readMessage()));
		System.out.println("	News Feed ");
		mem.newsFeed.forEach(nf -> System.out.println("		" + nf.content));
		System.out.println("	Connections ");
		mem.connections.forEach(con -> System.out.println("		" + con.name));
		System.out.println("	Followers ");
		mem.followers.forEach(fol -> System.out.println("		" + fol.name));
		System.out.println("	Jobs Applied ");
		mem.jobsApplied.forEach(jobId -> {
			JobPosting job = mem.linkedInAccess.jobs.get(jobId);
			System.out.println("		Job Title : " + job.title + ", Job Location : " + job.location);
			});
	}

	public static void main(String[] args) {
		LinkedInApplicationContext.registerMember("Ashkan Izadpanah", "Ashk1362!");
		LinkedInApplicationContext.registerMember("Alex-Colleague", "alexx!");
		LinkedInApplicationContext.registerMember("Bob-StudentOganda", "bobYYY1999");
		LinkedInApplicationContext.registerMember("Greg Google-HR", "google2020");
		LinkedInApplicationContext.registerMember("Mark Zuckerberg", "mark11");
		LinkedInApplicationContext.registerCompanyPage("Google Inc.", "California, mountainView");
		
		Company google = LinkedInApplicationContext.loginCompany("Google Inc.", "California, mountainView");
		if(google != null) {// logged-in
			google.addjobPosting("Software Engineer I", "California");
			google.addjobPosting("Product Manager", "New york");
			google.addjobPosting("Software Architect", "Waterloo");
		}
		
		Member nonAuthorzed = LinkedInApplicationContext.login("nonUser", "ilegarPassword!");
		
		Member alex = LinkedInApplicationContext.login("Alex-Colleague", "alexx!");
		if(alex != null) {// logged-in
			alex.searchMember("Ashkan Izadpanah").forEach( member -> alex.connectionReuest(member));
			alex.searchMember("Bob-StudentOganda").forEach( member -> alex.connectionReuest(member));
			alex.searchMember("Greg Google-HR").forEach( member -> alex.connectionReuest(member));
			alex.searchMember("Mark Zuckerberg").forEach( member -> alex.connectionReuest(member));
		} 
		
		Member ashkan = LinkedInApplicationContext.login("Ashkan Izadpanah", "Ashk1362!");
		if(ashkan != null) {// logged-in
			ashkan.searchMember("Alex-Colleague").forEach( member -> ashkan.connectionReuest(member));
			ashkan.searchMember("Greg Google-HR").forEach( member -> ashkan.connectionReuest(member));
			ashkan.searchMember("Mark Zuckerberg").forEach( member -> ashkan.follow(member));
		} 
		
		Member greg = LinkedInApplicationContext.login("Greg Google-HR", "google2020");
		if(greg != null) {// logged-in
			greg.acceptPendingConnections(greg.pendingconnections);
			greg.searchMember("Mark Zuckerberg").forEach( member -> greg.connectionReuest(member));
			greg.addpost(" google linkedin page has been created..job postings coming soon");
			greg.searchMember("Mark Zuckerberg").forEach( mark -> greg.sendMessage(mark, " hi Mark. I am Grep from Google. Please add our company page in Linkedin"));							
		}
		
		Member mark = LinkedInApplicationContext.login("Mark Zuckerberg", "mark11");
		if(mark != null) {// logged-in
			List<Member> conToReject = mark.pendingconnections.stream().filter(con -> con.name.contains("HR")).collect(Collectors.toList());
			List<Member> conToAccept = mark.pendingconnections.stream().filter(con -> !con.name.contains("HR")).collect(Collectors.toList());
			mark.rejectPendingConnection(conToReject);
			mark.acceptPendingConnections(conToAccept);
			mark.addpost(" Happy to annouce new release of facebook resolving current security issues");
			
		}
		
		
		Member bob = LinkedInApplicationContext.login("Bob-StudentOganda", "bobYYY1999");
		if(bob != null) {// logged-in
			bob.searchCompany("Google Inc.").forEach( com -> bob.follow(com));
			bob.searchMember("Mark Zuckerberg").forEach( member -> bob.follow(member));
			bob.searchJobs("Software").forEach( job -> bob.applyForJob(job));
			bob.searchJobs("Product").forEach(job -> 
				bob.sendMessage(alex, " Hi Alex, look at this job posting I just found : " + job.title));
		}
		
		viewMemberPage(greg);
		viewMemberPage(mark);
		viewMemberPage(ashkan);
		viewMemberPage(alex);
		viewMemberPage(bob);
	}

}
