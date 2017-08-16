/**
 * @(#)MazeExit.java
 *
 *
 * @author 
 * @version 1.00 2016/9/2
 */
import java.util.Stack;
import java.util.Scanner;
import java.util.Arrays;
public class MazeBacktracking {
	static Stack<Cell> stack = new Stack<Cell>();
	
    public static void main(String[] args){
    	Scanner in = new Scanner(System.in);
    	System.out.println("Please input row of maze: ");
    	int row = in.nextInt();
    	System.out.println("Please input column of maze: ");
    	int col = in.nextInt();
    	char[][] coords = new char[row+2][col+2];
    	in.nextLine();
    	Arrays.fill(coords[0], '1');
    	Arrays.fill(coords[row+1], '1');
    	for(int i = 1; i < row+1; i++){
    		coords[i] = ("1" + in.nextLine() +"1").toCharArray();
    	}
    	
    	/*for(int i = 0; i < coords.length; i++){
    		System.out.println(coords[i]);
    	}*/
    	
    	System.out.println("Please input starting point: row # and col #");
    	Cell start = new Cell(in.nextInt() + 1, in.nextInt() + 1);
    	System.out.println("Please input exit point: row # and col #");
    	Cell end = new Cell(in.nextInt() + 1, in.nextInt() + 1);
    	
    	
    	
    	Cell current = start;
    	while(current != end){
    		int hor = current.hor;
    		int vert = current.vert;
    		
    		System.out.println("Current row and column set:" + (hor-1) + " " + (vert-1));
    		//Check W
    		if(Character.getNumericValue((coords[hor][vert-1])) != 1 )
    			stack.push(new Cell(hor, vert-1));
    		//Check E
    		if(Character.getNumericValue((coords[hor][vert+1])) != 1)
    			stack.push(new Cell(hor, vert+1));
    		//Check S
    		if(Character.getNumericValue((coords[hor+1][vert])) != 1)
    			stack.push(new Cell(hor+1, vert));
    		//Check N
    		if(Character.getNumericValue((coords[hor-1][vert])) != 1)
    			stack.push(new Cell(hor-1, vert));
    		
    		if(!stack.isEmpty() && stack.peek() != current){
    			coords[hor][vert] = '1';
    			current = stack.pop();
    			if(current.vert == end.vert && current.hor == end.hor){
    				System.out.println("Reached exit!");
    				System.out.println("Last placement" + current.hor +" "+ current.vert);
    				break;
    			}
    				
    		}else{
    			System.out.println("Unreachable exit!");
    			break;
    		}
    	}
    }
    
    static class Cell{
    	int hor,vert;
    	Cell(int hor, int vert){
    		this.hor = hor;
    		this.vert = vert;
    	}
    }
    
    
}

