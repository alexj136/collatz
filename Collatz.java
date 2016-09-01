import java.util.ArrayList;

public class Collatz {

    public static void main(String[] args) {
        final int limit = Integer.parseInt(args[0]);
        ArrayList<Node> allNodes = new ArrayList<Node>();
        allNodes.add(new Node(1, null));
        for(int next = 2; next <= limit; next++) {
            if(getNode(next, allNodes) == null) {
                insertNode(next, allNodes);
            }
        }
        System.out.println("digraph G {");
        for(Node node : allNodes) System.out.println("    " + node);
        System.out.println("}");
    }

    public static Node insertNode(int value, ArrayList<Node> allNodes) {
        Node succ = getNode(computeNext(value), allNodes);
        if(succ == null) {
            succ = insertNode(computeNext(value), allNodes);
        }
        Node newNode = new Node(value, succ);
        allNodes.add(newNode);
        return newNode;
    }

    public static Node getNode(int value, ArrayList<Node> allNodes) {
        for(Node node : allNodes) {
            if(node.value == value) return node;
        }
        return null;
    }

    public static int computeNext(int value) {
        return (value % 2) == 0 ? value / 2 : (3 * value) + 1;
    }
}

class Node {
    public final int value;
    public final Node succ;
    public Node(int value, Node succ) {
        this.value = value;
        this.succ = succ;
    }
    @Override
    public String toString() {
        if(this.succ != null)
            return "" + this.value + " -> " + this.succ.value + ";";
        else
            return "" + this.value + ";";
    }
}
