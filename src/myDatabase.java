/**
 * 
 */
import java.util.*;

/**
 * @author Howard
 *
 */
public class myDatabase {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		HashMap<String, Integer> database = new HashMap<String, Integer>();
		HashMap<Integer, Integer> dbCount = new HashMap<Integer, Integer>();
		
		Scanner dbIn = new Scanner(System.in);
		String command;
		String name;
		int value;
		
		while(true){
			command = dbIn.next(); 
						
			if(command.equals("END"))
				break;
			else if(command.equals("SET")){
				name = dbIn.next();
				value = dbIn.nextInt();
				database.put(name, value);
				
				if(dbCount.containsKey(value)){
					int count = dbCount.get(value);
					count++;
					dbCount.put(value, count);
				}
				else
					dbCount.put(value, 1);
				
				System.out.println("Successfully inserted: " + name + " with value " + value);
				
			}		
			else if(command.equals("GET")){
				name = dbIn.next();
				if(database.containsKey(name)){
					value = database.get(name);
					System.out.println(value);
				}
				else{
					System.out.println("NULL");
				}
				
			}	
			else if(command.equals("UNSET")){
				name = dbIn.next();
				if(database.containsKey(name)){
					value = database.get(name);
					database.remove(name);
					
					int count = dbCount.get(value);
					count--;
					
					if(count > 0)
						dbCount.put(value, count);
					else
						dbCount.remove(value);
				}
			}	
			else if(command.equals("NUMEQUALTO")){
				value = dbIn.nextInt();
				if(dbCount.containsKey(value))
					System.out.println(dbCount.get(value));
				else
					System.out.println(0);
			}
			else{
				System.out.println("Please enter a valid command");
			}
		}

		return;
	}

}
