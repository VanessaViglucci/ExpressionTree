
public class infixToPostfix
{
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
