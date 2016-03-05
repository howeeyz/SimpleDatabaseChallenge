import java.util.*;

public class MyDatabase {
	
	public static void main(String[] args) {
		HashMap<String, Integer> database = new HashMap<String, Integer>();
		HashMap<Integer, Integer> dbCount = new HashMap<Integer, Integer>();
		
		Stack<String> history = null;
		Stack<Stack<String>> historyList = new Stack<Stack<String>>(); 
		
		Scanner dbIn = new Scanner(System.in);
		String command;
		String name;
		int value;
		
		while(true){
			command = dbIn.next(); 
						
			if(command.equals("END")){
				//Exit the program
				break;
			}
			
			else if(command.equals("SET")){
				//Take in values
				name = dbIn.next();
				value = dbIn.nextInt();
				
				
				
				//If this key already exists, need to update the number counter
				if(database.containsKey(name) == true){
					int oldValue = database.get(name);
					
					//For keeping history in case of rollback
					if(history != null){
						System.out.println("PUSHING A CHANGE SET STATEMENT");
						history.push("SET " + name + " " + oldValue);
					}
					
					int count = dbCount.get(oldValue);
					count--;
					
					if(count > 0)
						dbCount.put(oldValue, count);
					else
						dbCount.remove(oldValue);
				}
				else{
					//For keeping history in case of rollback
					if(history != null){
						System.out.println("PUSHING AN UNSET STATEMENT");
						history.push("UNSET " + name);
					}
				}
				
				//Put the key, value in the database
				database.put(name, value);
				
				//Update the number counter
				if(dbCount.containsKey(value)){
					int count = dbCount.get(value);
					count++;
					dbCount.put(value, count);
				}
				else
					dbCount.put(value, 1);
			}
			
			else if(command.equals("GET")){
				//Take in value
				name = dbIn.next();
				
				//Only print if the key is in the database, otherwise NULL
				if(database.containsKey(name)){
					value = database.get(name);
					System.out.println(value);
				}
				else{
					System.out.println("NULL");
				}
			}
			
			else if(command.equals("UNSET")){
				//Take in value
				name = dbIn.next();
				
				//Only unset if value exists
				if(database.containsKey(name)){
					value = database.get(name);
					database.remove(name);
					
					int count = dbCount.get(value);
					
					//For keeping history in case of rollback
					if(history != null){
						System.out.println("PUSHING A SET STATEMENT");
						history.push("SET " + name + " " + value);
					}
					
					count--;
					
					if(count > 0)
						dbCount.put(value, count);
					else
						dbCount.remove(value);
				}
			}
			
			else if(command.equals("NUMEQUALTO")){
				//Take in value
				value = dbIn.nextInt();
				
				//Check if the value exists...if so print it, otherwise 0
				if(dbCount.containsKey(value))
					System.out.println(dbCount.get(value));
				else
					System.out.println(0);
			}
			
			else if(command.equals("BEGIN")){
				//If this is the head, then no need to push
				if(history != null){
					historyList.push(history);
				}
				
				//Create a new history stack
				history = new Stack<String>();
			}
			
			else if(command.equals("ROLLBACK")){
				if(history != null){
					
					String fullCommand;
					String delims = "[ ]+";
					
					while(history.empty() == false){
						fullCommand = history.pop();
						String[] arguments = fullCommand.split(delims);
						
						if(arguments[0].equals("SET")){
							System.out.println("SETTING SOMETHING!");
							name = arguments[1];
							value = Integer.parseInt(arguments[2]);
							
							//If this key already exists, need to update the number counter
							if(database.containsKey(name) == true){
								int oldValue = database.get(name);
								int count = dbCount.get(oldValue);
								count--;
								
								if(count > 0)
									dbCount.put(oldValue, count);
								else
									dbCount.remove(oldValue);
							}
							
							//Put the key, value in the database
							database.put(name, value);
							
							//Update the number counter
							if(dbCount.containsKey(value)){
								int count = dbCount.get(value);
								count++;
								dbCount.put(value, count);
							}
							else
								dbCount.put(value, 1);
						}
						else if(arguments[0].equals("UNSET")){
							System.out.println("UNSETTING SOMETHING!");
							name = arguments[1];
							
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
						}	//End if if Statement
					}	//End of While Loop
					
					if(historyList.empty() == false){
						history = historyList.pop();
					}
				}	
			}
			
			else if(command.equals("COMMIT")){
				if(historyList.empty() == false){
					historyList.clear();
				}
				history = null;
			}
			
			else{
				System.out.println("Please enter a valid command");
			}
		}
		
		dbIn.close();
		return;
	}

}
