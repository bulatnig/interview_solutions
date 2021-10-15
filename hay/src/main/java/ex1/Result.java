package ex1;

import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.Collectors;

public class Result {

    /*
     * Complete the 'braces' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING_ARRAY values as parameter.
     */

    public static List<String> braces(List<String> values) {
        return values.stream().map(Result::isBalanced).collect(Collectors.toList());
    }

    static String isBalanced(String input) {
        var stack = new ArrayDeque<Character>();
        for (var current : input.toCharArray()) {
            if (isOpenBrace(current)) {
                stack.push(current);
                continue;
            }
            if (stack.isEmpty() || !openAndCloseBracesMatch(stack.pop(), current)) {
                return "NO";
            }
        }
        return stack.isEmpty() ? "YES" : "NO";
    }

    static boolean isOpenBrace(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    static boolean openAndCloseBracesMatch(char open, char close) {
        return (open == '(' && close == ')')
                || (open == '[' && close == ']')
                || (open == '{' && close == '}');
    }

    public static void main(String[] args) {
        System.out.println(isBalanced("[{}]"));
        System.out.println(isBalanced("[{]}"));
    }

}
