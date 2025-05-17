import java.util.ArrayList;

public class BigNumArithmetic {

    
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException(
                    "Expected exactly 1 argument: a file name.");
        }
        String filePath = args[0];
        FileProcessor.processFile(filePath);
    }

    public static String performOperation(ArrayList<String> sections){
        Node firstNum = Node.convertStringToNode(sections.get(0));
        Node secondNum = Node.convertStringToNode(sections.get(2));
        firstNum = firstNum.reverseNode();
        secondNum = secondNum.reverseNode();
        Math myMath = new Math();
        for(String part: sections){
            if ("+".equals(part)){
                Node total = myMath.add(firstNum, secondNum, 0);
                total = total.reverseNode();
                System.out.print(sections.get(0) + " + " + sections.get(2) + " = " + total.nodeToString(total) + "\n");
                return (sections.get(0) + " + " + sections.get(2) + " = " + total.nodeToString(total));
            }
            if ("*".equals(part)){
                ArrayList<Node> empty = new ArrayList<Node>();
                Node total = myMath.multiply(firstNum, secondNum, empty);
                total = total.reverseNode();
                System.out.print(sections.get(0) + " * " + sections.get(2) + " = " + total.nodeToString(total) + "\n");
                return (sections.get(0) + " * " + sections.get(2) + " = " + total.nodeToString(total));
            }
            if ("^".equals(part)){
                int power = Node.convertNodeToInt(secondNum);
                Node total = myMath.exponentation(firstNum, power);
                total = total.reverseNode();
                System.out.print(sections.get(0) + " ^ " + sections.get(2) + " = " + total.nodeToString(total) + "\n");
                return (sections.get(0) + " ^ " + sections.get(2) + " = " + total.nodeToString(total));
            }
        }
        return ("No operand given");
    }

}
