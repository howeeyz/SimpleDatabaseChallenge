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
		HashMap database= new HashMap();
		
		Scanner dbIn = new Scanner(System.in);
		String command;
		String name;
		String value;
		
		
		while(true){
			
			String current = dbIn.next(); 
			System.out.println(current);
			
			if(current.equals("END"))
				break;
			else if(current.equals("SET")){
				
			}		
			else if(current.equals("GET")){
				
			}	
			else if(current.equals("UNSET")){
				
			}	
			else if(current.equals("NUMEQUALTO")){
				
			}	
		}
		
		System.out.println("WE DONE");
		return;
	}

}
