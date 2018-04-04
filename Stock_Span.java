/*
*	Chris Warren V00850504 - last modified January 31, 2018
*/
import java.util.Scanner;
import java.io.File;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;


public class Stock{

	public static int[] CalculateSpan(int[] p){
		//A stack with the first index pushed onto it
		Stack<Integer> spanStack = new Stack<>();
		spanStack.push(0);
		
		//array to store span values
		int[] span = new int[p.length];
		
		// Span value day 1 is always 1
        span[0] = 1;
		
		//loop to calculate span
		for(int i=1; i<p.length; i++){
			//while stack is not empty and p[top of stack]
			//is less or equal to p[i], then pop elemts off of stack
			while(p[spanStack.peek()] <= p[i] && !spanStack.empty()){
				spanStack.pop();
			}
			//When the stack is empty,it means that the span of p[i] is i+1 
			//when the stack is not empty, it means that the span of p[i] is i - (top of stack)
			if(spanStack.empty()){
				span[i] = i+1;
			}else{
				span[i] = i - spanStack.peek();
			}
			//push the curent index onto the stack
			spanStack.push(i);
		}
		return span;
	}
	
	public static int[] readInput(Scanner s){
		Queue<Integer> q = new LinkedList<Integer>();
		int n=0;
		if(!s.hasNextInt()){
			return null;
		}
		int temp = s.nextInt();
		while(temp>=0){
			q.offer(temp);
			temp = s.nextInt();
			n++;
		}
		int[] inp = new int[q.size()];
		for(int i=0;i<n;i++){
			inp[i]= q.poll();
		}
		return inp;
	}
	public static void main(String[] args){
		Scanner s;
        Stock m = new Stock();
        if (args.length > 0){
        	try{
        		s = new Scanner(new File(args[0]));
        	} catch(java.io.FileNotFoundException e){
        		System.out.printf("Unable to open %s\n",args[0]);
        		return;
        	}
        	System.out.printf("Reading input values from %s.\n", args[0]);
        }else{
        	s = new Scanner(System.in);
        	System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
        }
            
        int[] price = m.readInput(s);
        System.out.println("The stock prices are:");
        for(int i=0;i<price.length;i++){
        	System.out.print(price[i]+ (((i+1)==price.length)? ".": ", "));
        }

        if(price!=null){
        	int[] span = m.CalculateSpan(price);
        	if(span!=null){
        		System.out.println("The spans are:");
        		for(int i=0;i<span.length;i++){
        			System.out.print(span[i]+ (((i+1)==span.length)? ".": ", "));
        		}
        	}
        }
    }
}
