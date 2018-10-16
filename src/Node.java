import java.util.ArrayList;
import java.util.Queue;

public class Node {
    private ArrayList<ArrayList<Integer>> state;
    private int emptyPositionX;
    private int emptyPositionY;
    private int cost;
    private int level;
    private Node parent;
    private int position;

    public int getPosition() {
        position = 0;
        for (int i = 0; i < state.size(); i++) {
            for (int j = 0; j < state.get(i).size(); j++) {
                if(state.get(i).get(j).equals(0)){
                    return position;
                }else {
                    position++;
                }
            }
        }
        return position;
    }

    public ArrayList<Node> neighbours = new ArrayList<Node>();

    public Node(ArrayList<ArrayList<Integer>> state) {
        this.state =state;
        for (int i = 0; i < state.size(); i++) {
            for (int j = 0; j < state.get(i).size(); j++) {
                if(state.get(i).get(j).equals(0)){
                    emptyPositionX = i;
                    emptyPositionY = j;
                }
            }
        }
        this.position = getPosition();
    }

    public Node() {
    }

    public int getEmptyPositionX() {
        return emptyPositionX;
    }

    public void setEmptyPositionX(int emptyPositionX) {
        this.emptyPositionX = emptyPositionX;
    }

    public int getEmptyPositionY() {
        return emptyPositionY;
    }

    public void setEmptyPositionY(int emptyPositionY) {
        this.emptyPositionY = emptyPositionY;
    }


    public ArrayList<Node> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Node neighbour) {
        int level = this.getLevel() + 1;
        neighbour.setLevel(level);
        neighbours.add(neighbour);
    }

    public ArrayList<ArrayList<Integer>> getState() {
        return state;
    }

    public void setState(ArrayList<ArrayList<Integer>> state) {
        this.state = state;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
