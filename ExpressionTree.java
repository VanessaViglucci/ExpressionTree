import java.util.Scanner; 

public class ExpressionTree 
{
	public static void main(String[] args)
	{
		//create scanner 
		Scanner input = new Scanner(System.in); 
		
		boolean run = true;
		while(run) //Keep asking for input indefinitely until user enters CTRL-C
		{ 
		//Get user input
        System.out.println("Enter an expression: "); 
        String inputExpression = input.nextLine(); 
        
        //Converts input expression to postfix expression
        String postFix = postfixCalc(inputExpression); 
        
        //Creates expression tree 
        //Node expressionTree = new Node(convert(postFix));
        
        //PREORDER Traversal
        System.out.print("Prefix: ");
        prefix(convert(postFix));
        System.out.println(""); 
        
        //INORDER Traversal 
        System.out.print("Infix: "); 
        infix(convert(postFix)); 
        System.out.println(""); 
        
        //POSTORDER Traversal 
        System.out.print("Postfix: "); 
        postfix(convert(postFix)); 
        System.out.println(""); 
        
        System.out.println("");
        
        run = true; 
		}
		input.close(); //close scanner

	}

	
	/*
	 * CONVERT TO EXPRESSION TREE
	 */
	
	//Takes a postfix expression (methods located at bottom of file) and creates an expression tree
    public static Node convert(String s)
    {
    	//Create an empty stack
    	Stack<Node> stack = new LinkedStack<>(); 
    	Node n; 
    	if(s.length() == 0)
    	{
    		n = new Node(0); 
    	}
    	else
    	{
    		for(int i = 0; i < s.length(); i++)
    		{
    			//If the token is a number 
    			Boolean num = Character.isDigit(s.charAt(i)); //check if it is a digit
    			if(num == true)
    			{
    				n = new Node(s.charAt(i)); //create a new expression tree node that represents just that number
    				stack.push(n); //push it onto the stack
    			}
    			//If the token is an operator
    			if(s.charAt(i) == '+'|| s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/')
    			{
    				//pop two off of the stack 
    				Node r = stack.pop(); 
    				Node l = stack.pop(); 
    				n = new Node(s.charAt(i), l, r); //create a new operator expression & set them as the children of the new node
    				stack.push(n); //push node on top of the stack 
    			}
    		}
    		//Once all the tokens in the postfix expression have been processed, the stack will contain only one expression tree.
    		//Pop it out 
    		n = stack.pop(); 
    		
    	}
    	//Return the final popped out tree
    	return n;  
    }
    
    /*
     * RECURSIVE TRAVERSALS OF THE EXPRESSION TREE 
     */
    
    //a PREORDER traversal of the tree
    public static void prefix(Node n) //takes expression tree from the convert method
    {
        if(n == null) //if it is null do nothing
        {
        	return; 
        }
        //Prints node data first 
        System.out.print(n.element); //print value (element)
        prefix(n.leftChild); //first traverse the left side 
        prefix(n.rightChild); //then traverse the right side 
    }
    
    //an INORDER traversal of the tree 
    public static void infix(Node n)
    {
        if(n == null) //if it is null do nothing 
        {
        	return;
        }
        //Prints the left child, the operator and then the right child
        //include parenthesis
        if(n.leftChild != null && n.rightChild != null)
        {
        	System.out.print("(");
        }
        infix(n.leftChild); 
        System.out.print(n.element); 
        infix(n.rightChild); 
        if(n.leftChild != null && n.rightChild != null)
        {
        	System.out.print(")");
        }
    }
    
    //a POSTORDER traversal of the tree 
    public static void postfix(Node n)
    {
        if(n == null) //if it is null do nothing 
        {
        	return; 
        }
        //prints the children of the node first and then prints the data inside the node 
        postfix(n.leftChild); //first traverses left
        postfix(n.rightChild); //then traverses right
        System.out.print(n.element); //then prints value (element) 
        
    }
    
    
    /*
     * CONVERT TO POSTFIX
     * The following two methods are used to convert the input expression to a postfix expression 
     * (Homework 2 methods)
     */
    
    public static int getPriority(char c)
	{
		if(c == '+' || c == '-')
		{
			return 1;
		}
		else
			return 2; 
	}
	public static String postfixCalc(String s)
	{
		Stack<Character> stack = new LinkedStack<Character>(); 	
		String postfix = ""; 
		char ch[] = s.toCharArray(); 
		for(int i = 0; i < ch.length; i++)
		{
			if(ch[i] != '+' && ch[i] != '-' && ch[i] != '*' && ch[i] != '/' && ch[i] != '(' && ch[i] != ')')
			{
				postfix = postfix + ch[i]; 
			}
			else if (ch[i] == '(')
			{
				stack.push(ch[i]);
			}
			else if(ch[i] == ')') 
			{
				while(!stack.isEmpty())
				{
					char temp = stack.pop();
					if(temp != '(')
					{
						postfix = postfix + temp; 
					}
					else
					{
						break; 
					}
				}
			}
			else if(ch[i] == '+' || ch[i] == '-' || ch[i] == '*' || ch[i] == '/')
			{
				if(stack.isEmpty())
				{
					stack.push(ch[i]); 
				}
				else
				{
					while(!stack.isEmpty())
					{
						char temp = stack.pop(); 
						if(temp == '(')
						{
							stack.push(temp); 
							break; 
						}
						else if(temp == '+' || temp == '-' || temp == '*' || temp == '/')
						{
							if(getPriority(temp) < getPriority(ch[i]))
							{
								stack.push(temp); 
								break;
							}
							else
							{
								postfix = postfix + temp; 
							}
						}
					}
					stack.push(ch[i]); 
				}
			}
		} 
		while(!stack.isEmpty())
		{
			postfix = postfix + stack.pop(); 
			
		}
		return postfix;
		
	}
}



