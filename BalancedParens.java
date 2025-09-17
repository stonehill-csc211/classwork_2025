public class BalancedParens {

    public static boolean balancedParens(String code){
        LinkedStack<Character> stack = new LinkedStack<>();
        char top;
        for(char c: code.toCharArray()){
            switch(c){
                case '(':
                stack.push(c);
                break;
                case '[':
                stack.push(c);
                break;
                case '{':
                stack.push(c);
                break;
                case ')':
                top = stack.peek();
                if(top == '(') stack.pop();
                else return false;
                break;
                case ']':
                top = stack.peek();
                if(top == '[') stack.pop();
                else return false;
                break;
                case '}':
                top = stack.peek();
                if(top == '{') stack.pop();
                else return false;
                break;
            }
        }
        return stack.size() == 0;

    }
    public static void main(String[] args){
        System.out.println(
            balancedParens("[({})]()") + " should be true"
        );
        System.out.println(
            balancedParens("(()") + " should be false"
        );
        System.out.println(
            balancedParens("([)]") + " should be false"
        );
    }
}
