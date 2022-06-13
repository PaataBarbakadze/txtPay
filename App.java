import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);
	static File users = new File("src/users.txt");
	static File phones = new File("src/phones.txt");
	static File phonesAndUsers = new File("src/phonesAndUsers.txt");
	static File balances = new File("src/balances.txt");
	static File balancesAndUsers = new File("src/balancesAndUsers.txt");
	
	public static void main(String [] args) throws IOException{
				
		// giveUsersPhones(2 , 5);
		// giveUsersBalances(3, 6, 3);
				
		pay(3, 6, 56);
				
		// registerUser(1 , "pako" , "pass" , "pako@");
		// registerUser(2 , "mari" , "pass" , "mari@");
		// registerUser(3 , "ana" , "pass" , "ana@");
		// registerUser(4 , "gio" , "pass" , "gio@");
		
		// registerPhone(1 , 555000000);
		// registerPhone(2 , 555000001);
		// registerPhone(3 , 555000002);
		// registerPhone(4 , 555000003);
		// registerPhone(5 , 555000004);
		
		// registerBalance(1, 50050000);
		// registerBalance(2, 50050001);
		// registerBalance(3, 50050002);
		// registerBalance(4, 50050003);
		// registerBalance(5, 50050004);
		// registerBalance(6, 50050005);
				
		//deleteUser("ana");
		
	}
	
	
	
	public static void registerPhone(int id , int phone) throws IOException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(phones, true));
		BufferedReader reader = new BufferedReader(new FileReader(phones));
		if(findPhoneById(id) == null && isPhoneInPN(phone) == true) {  //false iyo
			if(reader.readLine() != null) writer.newLine();
			writer.write(id + " " + phone);
			writer.close();
		}else {
            System.out.println(findPhoneById(id));
            System.out.println(isPhoneInPN(phone));

			System.out.println("id or phone is used");
		}
		reader.close();
		
	}
	
	
	
	public static boolean isPhoneInPN(int phoneNum) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(phones));
		String line = null;
		boolean isPhone = false;
		
		while((line = reader.readLine()) != null) {
			String phoneNumber = line.substring(line.indexOf(' ') + 1);
			if(phoneNumber.equals(phoneNumber + "")) {
				isPhone = true;
			}
			
		}
		reader.close();
		return isPhone;
		
	}
	
	
	
	public static void registerBalance(int id , int cardNum) throws IOException {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(balances, true));
			BufferedReader reader = new BufferedReader(new FileReader(balances));
			
			if(findBalanceById(id) == null && isBalanceInbalances(cardNum) == true) { //false iyo
				if(reader.readLine() != null) writer.newLine();
				writer.write(id + " " + cardNum);
				writer.close();
			}else {
				System.out.println("id or card number is used");
			}
			reader.close();
			
		}
	
	
	
	public static boolean isBalanceInbalances(int cardNum) throws IOException {
			
			BufferedReader reader = new BufferedReader(new FileReader(balances));
			String line = null;
			boolean isBalance = false;
			
			while((line = reader.readLine()) != null) {
				String cardNumber = line.substring(line.indexOf(' ') + 1);
				if(cardNumber.equals(cardNumber + "")) {
					isBalance = true;
				}
				
			}
			reader.close();
			return isBalance;
			
		}
	
	
	
	public static void registerUser(int id , String userName , String pass , String mail ) throws IOException   {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(users, true));
		BufferedReader reader = new BufferedReader(new FileReader(users));
		
		if(findUserById(id) == null && findUser(userName) == null) {
			if(reader.readLine() != null) writer.newLine();
			writer.write(id + " " + userName + " " + pass + " " + mail);
			writer.close();
		}else {
			System.out.println("id or user name is used");
		}
		reader.close();
		
	}
	
	
	
	public static void deleteUser(String userName) throws IOException {
		
		File tempFile = new File("src/Temp.txt");
		BufferedReader reader = new BufferedReader(new FileReader(users));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
		
		String lineToRemove = findUser(userName);
		String line;
		int index = 0;
		
		while((line = reader.readLine()) != null) {
		    index++;
		    String trimmedLine = line.trim();
		    if(trimmedLine.equals(lineToRemove)) continue;
		    writer.write(line);
		    if(index < usersNum()) {
		    	writer.newLine();
		    }
		    	  		    
		}
		writer.close(); 
		reader.close(); 
		tempFile.renameTo(users);
			    	    
	}
	
	
	
	public static void giveUsersPhones(int userId , int phoneId) throws IOException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(phonesAndUsers , true));
		BufferedReader reader = new BufferedReader(new FileReader(phonesAndUsers));
		
		if(findUserById(userId) != null && findPhoneById(phoneId) != null) {
			if(isPhoneInUATP(phoneId) == false) {
				if(findUserInUATP(userId) == null) {
					if(reader.readLine() != null) {
						writer.newLine();
					}
					writer.write(userId + " " + phoneId);
					
				}else {
					replacePhone(userId, phoneId);
				}
			}else {
				System.out.println("phone is already taken");
			}
		}else {
			System.out.println("user or phone doesnt exist");
		}
		reader.close();
		writer.flush();
		writer.close();
		
	}
	
	
	
	public static void giveUsersBalances(int userId , int balanceId , int balance) throws IOException {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(balancesAndUsers , true));
			BufferedReader reader = new BufferedReader(new FileReader(balancesAndUsers));
			
			if(findUserById(userId) != null && findBalanceById(balanceId) != null) {
				if(isBalanceInUATB(balanceId) == false) {
					if(findUserInUATB(userId) == null) {
						if(reader.readLine() != null) {
							writer.newLine();
						}
						writer.write(userId + " " + balanceId + "[" + balance + "]");
						
					}else {
						replaceBalance(userId, balanceId, balance);
					}
				}else {
					System.out.println("balance is used");
					
				}
			}else {
				System.out.println("user or balance doesnt exist");
			}
			reader.close();
			writer.flush();
			writer.close();
			
		}
	
	
	
	public static void pay(int userId , int balanceId , int value) throws IOException {
		
		
		String userInfo = findUserBalanceInfo(userId);
		int index = balanceId;	
		String left = null;
		String right = null;
		
		
		for(int i = 0 ; i < userInfo.length() ; i++) {      
			
			if(userInfo.charAt(i) == ',' || userInfo.charAt(i) == ' '){
				String tmp = userInfo.substring(i + 1);
				if(tmp.substring(0 , tmp.indexOf('[')).equals(index +"")) {
				
					left = userInfo.substring(0 , i + 1);
					if(tmp.contains(",")) {
					right = tmp.substring(tmp.indexOf(','));
					}else {
						right = null;
					}
						
				}
				
			}
			
		}
		//System.out.println(left);
		//System.out.println(right);
		//System.out.println(left+right);
		
		File tempFile = new File("src/Temp.txt");
		BufferedReader reader = new BufferedReader(new FileReader(balancesAndUsers));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		String lineToReplace = findUserInUATB(userId);
		String line;
		int tmp = 0;
		
		while((line = reader.readLine()) != null){
			tmp++;
			if(!line.equals(lineToReplace)) {
				if(tmp != 1) writer.newLine();
				writer.write(line);
				
			}else {
				if(tmp != 1) writer.newLine();
				if(right != null) {
					writer.write(left + balanceId + "[" + (findMoneyByid(userId, balanceId) + value) + "]" + right);
				}else {
					writer.write(left + balanceId + "[" + (findMoneyByid(userId, balanceId) + value) + "]");
				}
				continue;
			}
			
		}
		writer.flush();
		writer.close(); 
		reader.close(); 
		tempFile.renameTo(balancesAndUsers);
		
		
		
	}
	
	
	
	public static int findMoneyByid(int userId , int balanceId) throws IOException {
		
		
		BufferedReader reader = new BufferedReader(new FileReader(balancesAndUsers));
		String line = null; 
		int balanceI = 0;
		
		while((line = reader.readLine()) != null){
			
			String uIdT = line.substring(0 , line.indexOf(' '));
			int uId = Integer.parseInt(uIdT);
			
			if(uId == userId) {
				
				line = line.substring(line.indexOf(' ') + 1);
				
				while(line.contains("]")) {
					
					String balanceIndex = line.substring(0 , line.indexOf('['));
					int balanceIndexI = Integer.parseInt(balanceIndex);
					
					if(balanceIndexI == balanceId) {
						String balance = line.substring(line.indexOf('[') + 1, line.indexOf(']'));
						balanceI = Integer.parseInt(balance);
						line = "";
						
					}else {						
						line = line.substring(line.indexOf(',') + 1);						
					}
				}
				 
			}else {
				continue;
			}
		}
		reader.close();
		return balanceI;
	}
	
	
	
	public static String findUserBalanceInfo(int userId) throws IOException {
			
			BufferedReader reader = new BufferedReader(new FileReader(balancesAndUsers));
			
			String line = null;
			String userBalanceInfo = null;
			
			while((line = reader.readLine()) != null){
						
				String uIdT = line.substring(0 , line.indexOf(' '));
				int uId = Integer.parseInt(uIdT);
						
				if(uId == userId) {
					userBalanceInfo = line;		
				}
			}			
			reader.close();
			return userBalanceInfo;
			
		}
	
	
	
	public static String findUserById(int id) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(users));
		String line = null;
		String user = null;
		
		while((line = reader.readLine()) != null) {
			String tmp = line.substring(0 , line.indexOf(' '));
			int idT = Integer.parseInt(tmp);
			if(idT == id) {
				user = line.substring(line.indexOf(' ') + 1);
			}
		}
		reader.close();		
		return user;
		
	}
	
	
	
	public static String findPhoneById(int id) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(phones));
		String line = null;
		String phone = null;
		
		while((line = reader.readLine()) != null) {
			String tmp = line.substring(0 , line.indexOf(' '));
			int idT = Integer.parseInt(tmp);
			if(idT == id) {
				phone = line.substring(line.indexOf(' ') + 1);
			}
		}
		reader.close();		
		return phone;
		
	}
	
	
	
	public static String findBalanceById(int id) throws IOException {
			
			BufferedReader reader = new BufferedReader(new FileReader(balances));
			String line = null;
			String balance = null;
			
			while((line = reader.readLine()) != null) {
				String tmp = line.substring(0 , line.indexOf(' '));
				int idT = Integer.parseInt(tmp);
				if(idT == id) {
					balance = line.substring(line.indexOf(' ') + 1);
				}
			}
			reader.close();		
			return balance;
			
		}
	
	
	
	public static String findUserInUATP(int id) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(phonesAndUsers));
		String line = null;
		String user = null;
		
		while((line = reader.readLine()) != null) {
			String tmp = line.substring(0, line.indexOf(' '));
			int idT = Integer.parseInt(tmp);
			if(idT == id) {
				user = line;
			}
		}
		reader.close();
		return user;
		
	}
	
	
	
	public static String findUserInUATB(int id) throws IOException {
			
			BufferedReader reader = new BufferedReader(new FileReader(balancesAndUsers));
			String line = null;
			String user = null;
			
			while((line = reader.readLine()) != null) {
				String tmp = line.substring(0, line.indexOf(' '));
				int idT = Integer.parseInt(tmp);
				if(idT == id) {
					user = line;
				}
			}
			reader.close();
			return user;
			
		}
	
	
	
	public static boolean isPhoneInUATP(int id) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(phonesAndUsers));
		boolean isPhone = false;
		String line = null;
		
		while((line = reader.readLine()) != null) {
			String tmp = line.substring(line.indexOf(' ') + 1);
			String idT = id+"";
			if(tmp.contains(idT)) {
				isPhone = true;
			}
		}
		reader.close();
		return isPhone;
		
	}
	
	
	
	public static boolean isBalanceInUATB(int balanceId) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(balancesAndUsers));
		String line = null;
		boolean isBalance = false;
		
		while((line = reader.readLine()) !=null) {
			line = line.substring(line.indexOf(' ') + 1);
			String [] tmp = line.split(",");
			
			for(int i = 0 ; i <= tmp.length - 1 ; i++) {
				
				if(tmp[i].substring(0, tmp[i].indexOf('[')).equals(balanceId+"")) {
					isBalance = true;
					
				}			
				
			}
			
			
		}
		reader.close();
		return isBalance;
		
		
	}
	
	
	
	public static int userNumInUATB() throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(balancesAndUsers));
		int userNum = 0;
		@SuppressWarnings("unused")
		String line = null;
		
		while((line = reader.readLine()) != null) {
			userNum ++;
		}
		reader.close();
		return userNum;
		
	}
	
	
	
	public static void replacePhone(int userId , int phoneId) throws IOException {
		
		File tempFile = new File("src/Temp.txt");
		BufferedReader reader = new BufferedReader(new FileReader(phonesAndUsers));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		String lineToReplace = findUserInUATP(userId);
		String line;
		int tmp = 0;
		
		while((line = reader.readLine()) != null){
			tmp++;
			if(!line.equals(lineToReplace)) {
				if(tmp != 1) writer.newLine();
				writer.write(line);
				
			}else {
				writer.newLine();
				writer.write(lineToReplace + ',' + phoneId);
				continue;
			}
		}
		writer.flush();
		writer.close(); 
		reader.close(); 
		tempFile.renameTo(phonesAndUsers);
		
	}
	
	
	
	public static void replaceBalance(int userId , int phoneId , int balance) throws IOException {
			
			File tempFile = new File("src/Temp.txt");
			BufferedReader reader = new BufferedReader(new FileReader(balancesAndUsers));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
	
			String lineToReplace = findUserInUATB(userId);
			String line;
			int tmp = 0;
			
			while((line = reader.readLine()) != null){
				tmp++;
				if(!line.equals(lineToReplace)) {
					if(tmp != 1) writer.newLine();
					writer.write(line);
					
				}else {
					writer.newLine();
					writer.write(lineToReplace + ',' + phoneId + '[' + balance + ']');
					continue;
				}
			}
			writer.flush();
			writer.close(); 
			reader.close(); 
			tempFile.renameTo(balancesAndUsers);
			
		}
	
	
	
	public static int usersNum() throws IOException {
			
			BufferedReader reader = new BufferedReader(new FileReader(users));
			
			@SuppressWarnings("unused")
			String line;
			int index = 0;
			while((line = reader.readLine()) != null) {
				index++;
			}
			reader.close();
			return index;
			
		}
	
	
	
	public static String findUser(String userName) throws IOException {				
			
			BufferedReader reader = new BufferedReader(new FileReader(users));	
			String user = null;
			String line = null;
			
		    while(((line = reader.readLine()) != null)) {
		    	if(line.contains(userName)) {
		    		user = line.substring(line.indexOf(' ') + 1);
		    	} 	    	
		    }
		    sc.close();
		    reader.close();
            return user;
        }
}