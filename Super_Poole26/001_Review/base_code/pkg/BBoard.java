package pkg;
import java.util.*;
import java.io.*;


public class BBoard {		// This is your main file that connects all classes.
	// Think about what your global variables need to be.
	String defaulttitle;
	String user;
	ArrayList<User> userList;
	ArrayList<Message> messageList;
	User currentUser;
	// Default constructor that creates a board with a defaulttitle, empty user and message lists,
	// and no current user
	public BBoard() {
		defaulttitle = "Poole's Amazing BBoard";
		messageList = new ArrayList<>();
		userList = new ArrayList<>();
		currentUser = null;
	}

	// Same as the default constructor except it sets the title of the board
	public BBoard(String ttl) {	
		defaulttitle = ttl;
		messageList = new ArrayList<>();
		userList = new ArrayList<>();
		currentUser = null;
	}

	// Gets a filename of a file that stores the user info in a given format (users.txt)
	// Opens and reads the file of all authorized users and passwords
	// Constructs a User object from each name/password pair, and populates the userList ArrayList.
	public void loadUsers(String inputFile) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(inputFile));
		
		while (sc.hasNextLine()){
			String line = sc.nextLine().trim();
			if (line.isEmpty()) 
				continue;
			String [] parts = line.split(" ");
			if(parts.length==2){
				String username = parts[0];
				String password = parts[1];
				User u = new User(username, password);
				userList.add(u);
			}
		}
		
		sc.close();
	}

	// Asks for and validates a user/password. 
	// This function asks for a username and a password, then checks the userList ArrayList for a matching User.
	// If a match is found, it sets currentUser to the identified User from the list
	// If not, it will keep asking until a match is found or the user types 'q' or 'Q' as username to quit
	// When the users chooses to quit, sayu "Bye!" and return from the login function
	public void login(){
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("Enter username: ('q' to quit)");
			String name = sc.nextLine();
			
			if(name.equalsIgnoreCase("q")){
				System.out.println("Bye!");
				currentUser = null;
				return;
			}
			
			System.out.println("Enter password: ");
			String pass = sc.nextLine();
			
			boolean found = false;
			
			for (User u:userList){
				if(u.getUsername().equals(name) && u.getPassword().equals(pass)){
					currentUser = u;
					System.out.print("Welcome, " + name + "!");
					found = true;
					break;
				}
			}
			if(found){
				return;
			}
			else{
				System.out.println("Invalid login, try again");
			}
		}
	}

	
	// Contains main loop of Bulletin Board
	// IF and ONLY IF there is a valid currentUser, enter main loop, displaying menu items
	// --- Display Messages ('D' or 'd')
	// --- Add New Topic ('N' or 'n')
	// --- Add Reply ('R' or 'r')
	// --- Change Password ('P' or 'p')
	// --- Quit ('Q' or 'q')
	// With any wrong input, user is asked to try again
	// Q/q should reset the currentUser to 0 and then end return
	// Note: if login() did not set a valid currentUser, function must immediately return without showing menu
	public void run(){
		Scanner sc = new Scanner(System.in);
		if(currentUser==null){
			login();
			if(currentUser==null){
			System.out.println("No user logged in.");
			return;
			}
		}
			
		boolean running = true;
			
		while(running){
			System.out.println("\n--- " + defaulttitle + " ---");
	        System.out.println("Choose an option:");
	        System.out.println("D - Display Messages");
	        System.out.println("N - Add New Topic");
	        System.out.println("R - Add Reply");
	        System.out.println("P - Change Password");
	        System.out.println("Q - Quit");
	        System.out.print("Enter choice: ");
	        
	        String choice = sc.nextLine();
	        
	        if(choice.equalsIgnoreCase("d")){
	        		display();
	        }
	        
	        else if(choice.equalsIgnoreCase("n")){
	        		addTopic();
	        }
	        
	        else if(choice.equalsIgnoreCase("r")){
	        		addReply();
	        }
	        
	        else if(choice.equalsIgnoreCase("p")){
	        		setPassword();
	        }
	        
	       else if(choice.equalsIgnoreCase("q")){
	        		System.out.println("Bye!");
	        		currentUser = null;
	        		running = false;
	        }
	       else{
	       	System.out.println("Invalid choice. try again.");
	       }
		}
		
	}

	// Traverse the BBoard's message list, and invote the print function on Topic objects ONLY
	// It will then be the responsibility of the Topic object to invoke the print function recursively on its own replies
	// The BBoard display function will ignore all reply objects in its message list
	private void display(){
		for(Message m:messageList){
			if(!m.isReply()){
				m.print(0);
			}
		}
	}


	// This function asks the user to create a new Topic (i.e. the first message of a new discussion "thread")
	// Every Topic includes a subject (single line), and body (single line)

	/* 
	Subject: "Thanks"
	Body: "I love this bulletin board that you made!"
	*/

	// Each Topic also stores the username of currentUser; and message ID, which is (index of its Message + 1)

	// For example, the first message on the board will be a Topic who's index will be stored at 0 in the messageList ArrayList,
	// so its message ID will be (0+1) = 1
	// Once the Topic has been constructed, add it to the messageList
	// This should invoke your inheritance of Topic to Message
	private void addTopic(){
		if(currentUser==null){
			System.out.println("You must be logged in to add a topic");
			return;
		}
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Make new subject:");
		String subject = sc.nextLine();
		
		System.out.println("Make a new body: ");
		String body = sc.nextLine();
		
		int id = messageList.size()+1;
		
		Topic topic = new Topic(currentUser.getUsername(), subject, body, id);
		
	}

	// This function asks the user to enter a reply to a given Message (which may be either a Topic or a Reply, so we can handle nested replies).
	//		The addReply function first asks the user for the ID of the Message to which they are replying;
	//		if the number provided is greater than the size of messageList, it should output and error message and loop back,
	// 		continuing to ask for a valid Message ID number until the user enters it or -1.
	// 		(-1 returns to menu, any other negative number asks again for a valid ID number)
	
	// If the ID is valid, then the function asks for the body of the new message, 
	// and constructs the Reply, pushing back the Reply on to the messageList.
	// The subject of the Reply is a copy of the parent Topic's subject with the "Re: " prefix.
	// e.g., suppose the subject of message #9 was "Thanks", the user is replying to that message:


	/*
			Enter Message ID (-1 for Menu): 9
			Body: It was a pleasure implementing this!
	*/

	// Note: As before, the body ends when the user enters an empty line.
	// The above dialog will generate a reply that has "Re: Thanks" as its subject
	// and "It was a pleasure implementing this!" as its body.

	// How will we know what Topic this is a reply to?
	// In addition to keeping a pointer to all the Message objects in BBoard's messageList ArrayList
	// Every Message (wheather Topic or Reply) will also store an ArrayList of pointers to all of its Replies.
	// So whenever we build a Reply, we must immediately store this Message in the parent Message's list. 
	// The Reply's constructor should set the Reply's subject to "Re: " + its parent's subject.
	// Call the addChild function on the parent Message to push back the new Message (to the new Reply) to the parent's childList ArrayList.
	// Finally, push back the Message created to the BBoard's messageList. 
	// Note: When the user chooses to return to the menu, do not call run() again - just return fro mthis addReply function. 
	private void addReply() {
    Scanner sc = new Scanner(System.in);
    int parentId = -2;

    while (true) {
        System.out.print("Enter Message ID (-1 for Menu): ");
        String line = sc.nextLine().trim();

        // check for -1
        if (line.equals("-1")) {
            return;
        }

        // check if the line is a positive integer
        if (line.matches("\\d+")) {  // one or more digits
            parentId = Integer.parseInt(line);

            if (parentId > 0 && parentId <= messageList.size()) {
                break; // valid ID
            } 
            else {
                System.out.println("Invalid ID number. Try again.");
            }
        } 
        else {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
			Message parent = messageList.get(parentId-1);
			String subject = "Reply: "+parent.getSubject();
			
			System.out.print("Body: ");
			String body = sc.nextLine().trim();
			
			int id = messageList.size()+1;
			Reply reply = new Reply(currentUser.getUsername(), subject, body, id);
			parent.addChild(reply);
			messageList.add(reply);
			
			System.out.println("Reply added!");
		
	}

	// This function allows the user to change their current password.
	// The user is asked to provide the old password of the currentUser.
	// 		If the received password matches the currentUser password, then the user will be prompted to enter a new password.
	// 		If the received password doesn't match the currentUser password, then the user will be prompted to re-enter the password. 
	// 		The user is welcome to enter 'c' or 'C' to cancel the setting of a password and return to the menu.
	// Any password is allowed except 'c' or 'C' for allowing the user to quit out to the menu. 
	// Once entered, the user will be told "Password Accepted." and returned to the menu.
	private void setPassword() {
    if (currentUser == null) {
        System.out.println("No user logged in.");
        return;
    }

    Scanner sc = new Scanner(System.in);

    while (true) {
        System.out.print("Enter current password (or 'c' to cancel): ");
        String oldPass = sc.nextLine();

        if (oldPass.equalsIgnoreCase("c")) {
            System.out.println("Password change canceled.");
            return;
        }

        if (currentUser.check(currentUser.getUsername(), oldPass)) {
            System.out.print("Enter new password: ");
            String newPass = sc.nextLine();

            if (newPass.equalsIgnoreCase("c")) {
                System.out.println("Invalid password. Cannot be 'c' or 'C'.");
                continue; // go back to asking for old password
            }

            currentUser.setPassword(oldPass, newPass);
            System.out.println("Password Accepted.");
            return; // success → exit function
        } else {
            System.out.println("Incorrect password, try again.");
        }
    }
}
}

