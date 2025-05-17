import java.util.ArrayList;

public class Math {
    Node result = new Node(0, null);

    Math() {
    }

    public Node add(Node firstNum, Node secondNum, int carryOver) {
        if (firstNum == null && secondNum == null && carryOver == 0) {
            return null;
        } else {
            if (firstNum == null) {
                firstNum = new Node(0, null);
            }
            if (secondNum == null) {
                secondNum = new Node(0, null);
            }
        }
        int total = firstNum.getValue() + secondNum.getValue() + carryOver;
        int newValue = total % 10;
        carryOver = total / 10;
        Node result = new Node(newValue, null);
        result.nextPointer = add(firstNum.getNextPointer(), secondNum.getNextPointer(), carryOver);

        return result;
    }

    public Node subtract(Node firstNum, Node secondNum, int carryOver) {
        if (firstNum == null && secondNum == null && carryOver == 0) {
            return null;
        } else {
            if (firstNum == null) {
                firstNum = new Node(0, null);
            }
            if (secondNum == null) {
                secondNum = new Node(0, null);
            }
        }
        int total = firstNum.getValue() - secondNum.getValue() + carryOver;
        int newValue = total % 10;
        carryOver = total / 10;
        Node result = new Node(newValue, null);
        result.nextPointer = subtract(firstNum.getNextPointer(), secondNum.getNextPointer(), carryOver);

        return result;
    }

    public Node multiply(Node firstNum, Node secondNum, ArrayList<Node> beforeAdd) {
        if (firstNum == null) {
            while (beforeAdd.size() != 1) {
                Node temp = add(beforeAdd.get(0), beforeAdd.get(1), 0);
                beforeAdd.removeFirst();
                beforeAdd.removeFirst();
                beforeAdd.add(temp);
            }
            return beforeAdd.getFirst();
        }

        Node shiftedPart = multHelper(firstNum, secondNum, 0);
        for (int i = 0; i < beforeAdd.size(); i++) {
            Node zero = new Node(0, shiftedPart);
            shiftedPart = zero;
        }
        beforeAdd.add(shiftedPart);
        return multiply(firstNum.getNextPointer(), secondNum, beforeAdd);
    }

    public Node multHelper(Node firstNum, Node secondNum, int carryOver) {
        int total = 0;
        Node addToBeforeAdd = new Node(0, null);
        if (secondNum != null) {
            total = (firstNum.getValue() * secondNum.getValue()) + carryOver;
            int value = total % 10;
            carryOver = total / 10;
            addToBeforeAdd.setValue(value);
            addToBeforeAdd.setNextPointer(multHelper(firstNum, secondNum.getNextPointer(), carryOver));
        } else {
            addToBeforeAdd.setValue(carryOver);
        }

        return addToBeforeAdd;
    }

    public Node exponentation(Node firstNum, int power) {
        if (power == 0){
            return Node.convertStringToNode("1");
        }

        if (power == 1){
            return firstNum;
        }

        if (power % 2 == 0){
            Node squared = multiply(firstNum, firstNum, new ArrayList<>());
            return exponentation(squared, power /2);
        } else{
            Node squared = multiply(firstNum, firstNum, new ArrayList<>());
            Node squaredToPower = exponentation(squared, (power -1)/2);
            Node total = multiply(firstNum, squaredToPower, new ArrayList<>());
            return total;
        }

    }
}
