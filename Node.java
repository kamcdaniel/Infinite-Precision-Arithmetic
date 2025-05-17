import java.util.ArrayList;
import java.util.Arrays;

public class Node {
    int value;
    Node nextPointer;


    Node(int num, Node next) {
        value = num;
        nextPointer = next;
    }


    public static ArrayList<String> splitInput(String textFile) {
        String[] tempSections = textFile.split(" ");
        ArrayList<String> sections = new ArrayList<>(Arrays.asList(tempSections));
        for (int i = sections.size() - 1; i >= 0; i--) {
            if (sections.get(i).isEmpty()) {
                sections.remove(i);
            }
        }
        return sections;
    }

    public static Node convertStringToNode(String input) {
        Node head = null;
        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        //checks every character from the input string
        for (int i = input.length() - 1; i >= 0; i--) {
            char character = input.charAt(i);
            //if the individual character we are looking at is in the array...
            for (char num : numbers) {
                //...then it is a number so we need to turn it into a node
                if (character == num) {
                    //will type casting work here??
                    head = new Node(Character.getNumericValue(character), head);
                    break;
                }
            }
        }
        return head;
    }

    public static int convertNodeToInt(Node secondNum){
        secondNum = secondNum.reverseNode();
        String secondInt = "";

        while (secondNum != null){
            secondInt += secondNum.getValue();
            secondNum = secondNum.getNextPointer();
        }
        return Integer.parseInt(secondInt);
    }


    public Node reverseNode() {
        ArrayList<Integer> temp = new ArrayList<>();
        Node current = this;
        while (current != null) {
            temp.add(current.value);
            current = current.nextPointer;
        }
        Node finalSum = reverseHelper(temp);

        return finalSum;

    }

    private Node reverseHelper(ArrayList<Integer> temp) {
        if (temp.isEmpty()) {
            return null;
        }
        int val = temp.remove(temp.size() - 1);
        Node newNode = new Node(val, reverseHelper(temp));
        return newNode;
    }

    public int getValue(){
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNextPointer() {
        return this.nextPointer;
    }

    public void setNextPointer(Node next) {
        this.nextPointer = next;
    }


    public String nodeToString(Node given){
        String outcome = "";
        boolean foundFirstNum = false;

        while (given != null){
            int current = given.getValue();

            if (current != 0){
                foundFirstNum = true;
            }

            if (foundFirstNum || given.getNextPointer() == null){
                outcome += current;
            }
            given = given.getNextPointer();
        }
        return outcome;
    }

}

