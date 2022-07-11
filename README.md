<h1> Expression Tree: </h1> 
<p> This project takes user input, converts it into a postfix expression and he converts the postfix expression into an expression tree.</p>
<p> After the tree is created, preoder, iorder and postorder traversals are used to print the prefix, infix (with appropriate parenthesis),
  and postfix expressions representing the user input. The appliction can handle multiple inputs before existing.</p> 
<p> The implementation is done with a stack. Each element in the stack is an expression tree. We loop thrugh each token in the postfix expression. 
If the token is a number, we create a tree node that represents the number and we push it to the stack. If it is an operator, we create a new operator expression, 
and pop two expressions off the top of the stack and set them as children of this new node. Once all token are processed, the stack only 
contains one expression tree which is the one we want. </p> 
  
