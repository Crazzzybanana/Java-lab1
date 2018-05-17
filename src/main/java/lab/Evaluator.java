package lab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import static java.lang.Character.*;

public class Evaluator {
    private static HashMap<String, Object> variableList = new HashMap<>();

    public static void main(String[] args){
        parse("F = [3,4,6]");
//        parse("S = [1,4,3]");
        parse("B = [[1, 2, 3], [1, 2, 3], [1, 2, 3]]");
//        parse("B = [[5, 4, 3], [1, 6, 3], [3, 55, 3]]");
        System.out.println(parse("F * B"));
        System.out.println(parse("K = F * B"));
    }

    public static Object parse(String expression)
    {
        expression = expression.replaceAll("\\s+","");
        char[] tokens = expression.toCharArray();

        boolean change = false;

        List<Integer> integerList = new ArrayList<>();

        StringBuffer vbuf = new StringBuffer();

        String s;

        int counter = 0;

        Stack<Object> v = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if(isLetter(tokens[i]) && (tokens[i] != 'T' && tokens[i] != '^')){
                vbuf.append(tokens[i]);
//                while (i < tokens.length && isLetter(tokens[i]))
//                    vbuf.append(tokens[i++]);
                if(variableList.containsKey(vbuf.toString()) && tokens[i] != '='){
                    v.push(variableList.get(vbuf.toString()));
                    vbuf.delete(0, vbuf.length());
                }else if(variableList.containsKey(vbuf.toString()) && tokens[i++] == '='){
                    change = true;
                }else if(isLetter(tokens[i]) || tokens[i] == '='){
                    vbuf.delete(0, vbuf.length());
                    continue;
                }
            }
            if(isDigit(tokens[i])){
                StringBuffer sbuf = new StringBuffer();
                while (i < tokens.length && isDigit(tokens[i]))
                    sbuf.append(tokens[i++]);
                integerList.add(Integer.parseInt(sbuf.toString()));
            }
            if(tokens[i] == '[')
            {
                counter++;
            }
            if(tokens[i] == ']' && tokens.length - 1 == i && counter == 1)
            {
                int[] b = integerList.stream()
                        .mapToInt(Integer::intValue)
                        .toArray();
                v.push(new Vector(b));
                if(change){
                    variableList.replace(vbuf.toString(), new Vector(b));
                }else{
                    variableList.put(vbuf.toString(), new Vector(b));
                }
                integerList.clear();
                counter = 0;
                vbuf.delete(0, vbuf.length());
            }
            else if(tokens[i] == ']' && tokens.length - 1 == i)
            {
                counter--;
                int c = 0;
                int [][] buff = new int[counter][integerList.size() / counter];
                for(int j=0; j<buff.length;j++)
                    for(int k=0;k<buff[0].length;k++)
                    {
                        buff[j][k] = integerList.get(c);
                        c++;
                    }
                v.push(new Matrix(buff));
                if(change){
                    variableList.replace(vbuf.toString(), new Matrix(buff));
                }else{
                    variableList.put(vbuf.toString(), new Matrix(buff));
                }
                integerList.clear();
                counter = 0;
                vbuf.delete(0, vbuf.length());
            }
            if (tokens[i] == '(')
                ops.push(tokens[i]);

            if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                    v.push(applyOp(ops.pop(), v.pop(), v.pop()));
                ops.pop();
            }
            if (tokens[i] == '*'|| tokens[i] == '&')
            {
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    v.push(applyOp(ops.pop(), v.pop(), v.pop()));

                ops.push(tokens[i]);
            }
            if(tokens[i] == 'T' && tokens[i-1] == '^')
            {
                v.push(applyOp('T', null, v.pop()));
            }
        }
        while (!ops.empty())
            v.push(applyOp(ops.pop(), v.pop(), v.pop()));

        return v.pop();
    }

    private static boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
//        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
//            return false;
        else
            return true;
    }

    private static Object applyOp(char op, Object b, Object a) {
        switch (op) {
            case '&':
                if(!(a instanceof Vector) || !(b instanceof Vector)) return null;
                Vector v1 = (Vector) a;
                Vector v2 = (Vector) b;
                return v1.cross(v2);
            case '*':
                if(!(a instanceof Vector) || !(b instanceof Matrix)) return null;
                Vector v = (Vector) a;
                Matrix m = (Matrix) b;
                return v.multiply(m);
            case 'T':
                if(!(a instanceof Matrix)) return null;
                Matrix mTranspose = (Matrix) a;
                return mTranspose.transpose();
        }
        return null;
    }
}

