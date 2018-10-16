import java.util.ArrayList;

public class PuzzleBoard {

    public ArrayList<ArrayList<Integer>> startState;
    public Node root;

    public PuzzleBoard(ArrayList<ArrayList<Integer>> startState) {
        this.startState = startState;
        this.root = new Node(startState);
        root.setState(startState);
    }

    public ArrayList<Node> getNeighbours(){
        return root.getNeighbours();
    }

    /*this method determine the direction and order of children*/
    public void setNeighbours(Node node){
        boolean usedUp = false,usedDown = false,usedLeft = false,usedRight = false,usedUpLeft = false,usedUpRight =false,usedDownLeft=false,usedDownRight = false;
        while(true) {
            if (hasUp(node)&& !usedUp) {
                Node newNode = move("UP", node);
                if(!existedMove(node,newNode)) {
                    node.setNeighbours(newNode);
                }
                usedUp = true;
                continue;
            } else if (hasUpRight(node)&& !usedUpRight) {
                Node newNode = move("UP-RIGHT", node);
                if(!existedMove(node,newNode)) {
                    node.setNeighbours(newNode);
                }
                usedUpRight = true;
                continue;
            } else if (hasRight(node) && !usedRight) {
                Node newNode = move("RIGHT", node);
                if(!existedMove(node,newNode)) {
                    node.setNeighbours(newNode);
                }
                usedRight = true;
                continue;

            } else if (hasDownRight(node)&& !usedDownRight) {
                Node newNode = move("DOWN-RIGHT", node);
                if(!existedMove(node,newNode)) {
                    node.setNeighbours(newNode);
                }
                usedDownRight = true;
                continue;
            } else if (hasDown(node)&& !usedDown) {
                Node newNode = move("DOWN", node);
                if(!existedMove(node,newNode)) {
                    node.setNeighbours(newNode);
                }
                usedDown = true;
                continue;
            } else if (hasDownLeft(node)&& !usedDownLeft) {
                Node newNode = move("DOWN-LEFT", node);
                if(!existedMove(node,newNode)) {
                    node.setNeighbours(newNode);
                }
                usedDownLeft = true;
                continue;
            } else if (hasLeft(node)&& !usedLeft) {
                Node newNode = move("LEFT", node);
                if(!existedMove(node,newNode)) {
                    node.setNeighbours(newNode);
                }
                usedLeft = true;
                continue;
            } else if (hasUpLeft(node)&& !usedUpLeft) {
                Node newNode = move("UP-LEFT", node);
                if(!existedMove(node,newNode)) {
                    node.setNeighbours(newNode);
                }
                usedUpLeft = true;
                continue;
            }
            return;
        }
    }

    /*to check if there is a exsited state in previous search*/
    public boolean existedMove(Node node,Node newNode){
        while (!node.equals(null)){
            if(node.getParent() == null){
                return false;
            }
            if(node.getParent().getState().equals(newNode.getState())){
                return true;
            }
            node = node.getParent();
        }
        return false;
    }

    public boolean hasUp(Node node){
        if(node.getEmptyPositionX() > 0){
            return true;
        }else {
            return false;
        }
    }
    public boolean hasDown(Node node){
        if(node.getEmptyPositionX() < node.getState().size() - 1){
            return true;
        }else {
            return false;
        }
    }
    public boolean hasLeft(Node node){
        if(node.getEmptyPositionY() > 0){
            return true;
        }else {
            return false;
        }
    }
    public boolean hasRight(Node node){
        if(node.getEmptyPositionY() < node.getState().get(0).size() - 1){
            return true;
        }else {
            return false;
        }
    }
    public boolean hasUpRight(Node node){
        if((node.getEmptyPositionY() < node.getState().get(0).size() - 1)&& (node.getEmptyPositionX() > 0)){
            return true;
        }else {
            return false;
        }
    }
    public boolean hasUpLeft(Node node){
        if((node.getEmptyPositionX() > 0 )&& (node.getEmptyPositionY() > 0)){
            return true;
        }else {
            return false;
        }
    }
    public boolean hasDownRight(Node node){
        if((node.getEmptyPositionY() < node.getState().get(0).size() - 1) && (node.getEmptyPositionX() < node.getState().size() - 1)){
            return true;
        }else {
            return false;
        }
    }
    public boolean hasDownLeft(Node node){
        if((node.getEmptyPositionX() < node.getState().size() - 1)&& (node.getEmptyPositionY() > 0)){
            return true;
        }else {
            return false;
        }
    }

    /*This method is to move current puzzle to the next possible puzzle with new empty position*/
    public Node move(String s,Node node){
        ArrayList<ArrayList<Integer>> currentState = node.getState();
        ArrayList<ArrayList<Integer>> newState = new ArrayList<ArrayList<Integer>>();
        int temp1;
        for (int i = 0; i < currentState.size(); i++) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            newState.add(row);
            for (int j = 0; j < currentState.get(i).size(); j++) {
                row.add(currentState.get(i).get(j));
            }
        }
        Node newNode = new Node(newState);
        newNode.setParent(node);
        if(s.equals("UP")){
            temp1 = newState.get(node.getEmptyPositionX() - 1).get(node.getEmptyPositionY());
            newState.get(node.getEmptyPositionX() - 1).add(node.getEmptyPositionY(),0);
            newState.get(node.getEmptyPositionX() - 1).remove(node.getEmptyPositionY() + 1);

            newNode.setEmptyPositionX(node.getEmptyPositionX() - 1);
            newNode.setEmptyPositionY(node.getEmptyPositionY());

            newState.get(node.getEmptyPositionX()).add(node.getEmptyPositionY(),temp1);
            newState.get(node.getEmptyPositionX()).remove(node.getEmptyPositionY() + 1);
        }else if(s.equals("UP-RIGHT")){
            temp1 = newState.get(node.getEmptyPositionX() - 1).get(node.getEmptyPositionY() + 1);
            newState.get(node.getEmptyPositionX() - 1).add(node.getEmptyPositionY() + 1,0);
            newState.get(node.getEmptyPositionX() - 1).remove(node.getEmptyPositionY() + 2);

            newNode.setEmptyPositionX(node.getEmptyPositionX() - 1);
            newNode.setEmptyPositionY(node.getEmptyPositionY() + 1);

            newState.get(node.getEmptyPositionX()).add(node.getEmptyPositionY(),temp1);
            newState.get(node.getEmptyPositionX()).remove(node.getEmptyPositionY() + 1);
        }else if(s.equals("RIGHT")){//
            temp1 = newState.get(node.getEmptyPositionX()).get(node.getEmptyPositionY() + 1);
            newState.get(node.getEmptyPositionX()).add(node.getEmptyPositionY() + 1,0);
            newState.get(node.getEmptyPositionX()).remove(node.getEmptyPositionY() + 2);

            newNode.setEmptyPositionX(node.getEmptyPositionX());
            newNode.setEmptyPositionY(node.getEmptyPositionY() + 1);

            newState.get(node.getEmptyPositionX()).add(node.getEmptyPositionY(),temp1);
            newState.get(node.getEmptyPositionX()).remove(node.getEmptyPositionY() + 1);
        }else if(s.equals("DOWN-RIGHT")){
            temp1 = newState.get(node.getEmptyPositionX() + 1).get(node.getEmptyPositionY() + 1);
            newState.get(node.getEmptyPositionX() + 1).add(node.getEmptyPositionY() + 1,0);
            newState.get(node.getEmptyPositionX() + 1).remove(node.getEmptyPositionY() + 2);

            newNode.setEmptyPositionX(node.getEmptyPositionX() + 1);
            newNode.setEmptyPositionY(node.getEmptyPositionY() + 1);

            newState.get(node.getEmptyPositionX()).add(node.getEmptyPositionY(),temp1);
            newState.get(node.getEmptyPositionX()).remove(node.getEmptyPositionY() + 1);
        }else if(s.equals("DOWN")){
            temp1 = newState.get(node.getEmptyPositionX() + 1).get(node.getEmptyPositionY());
            newState.get(node.getEmptyPositionX() + 1).add(node.getEmptyPositionY(),0);
            newState.get(node.getEmptyPositionX() + 1).remove(node.getEmptyPositionY() + 1);

            newNode.setEmptyPositionX(node.getEmptyPositionX() + 1);
            newNode.setEmptyPositionY(node.getEmptyPositionY());

            newState.get(node.getEmptyPositionX()).add(node.getEmptyPositionY(),temp1);
            newState.get(node.getEmptyPositionX()).remove(node.getEmptyPositionY() + 1);
        }else if(s.equals("DOWN-LEFT")){
            temp1 = newState.get(node.getEmptyPositionX() + 1).get(node.getEmptyPositionY() - 1);
            newState.get(node.getEmptyPositionX() + 1).add(node.getEmptyPositionY() - 1,0);
            newState.get(node.getEmptyPositionX() + 1).remove(node.getEmptyPositionY());

            newNode.setEmptyPositionX(node.getEmptyPositionX() + 1);
            newNode.setEmptyPositionY(node.getEmptyPositionY() - 1);

            newState.get(node.getEmptyPositionX()).add(node.getEmptyPositionY(),temp1);
            newState.get(node.getEmptyPositionX()).remove(node.getEmptyPositionY() + 1);
        }else if(s.equals("LEFT")){
            temp1 = newState.get(node.getEmptyPositionX()).get(node.getEmptyPositionY() - 1);
            newState.get(node.getEmptyPositionX()).add(node.getEmptyPositionY() - 1,0);
            newState.get(node.getEmptyPositionX()).remove(node.getEmptyPositionY());

            newNode.setEmptyPositionX(node.getEmptyPositionX());
            newNode.setEmptyPositionY(node.getEmptyPositionY() - 1);

            newState.get(node.getEmptyPositionX()).add(node.getEmptyPositionY(),temp1);
            newState.get(node.getEmptyPositionX()).remove(node.getEmptyPositionY() + 1);
        }else if(s.equals("UP-LEFT")){
            temp1 = newState.get(node.getEmptyPositionX() - 1).get(node.getEmptyPositionY() - 1);
            newState.get(node.getEmptyPositionX() - 1).add(node.getEmptyPositionY() - 1,0);
            newState.get(node.getEmptyPositionX() - 1).remove(node.getEmptyPositionY());

            newNode.setEmptyPositionX(node.getEmptyPositionX() - 1);
            newNode.setEmptyPositionY(node.getEmptyPositionY() - 1);

            newState.get(node.getEmptyPositionX()).add(node.getEmptyPositionY(),temp1);
            newState.get(node.getEmptyPositionX()).remove(node.getEmptyPositionY() + 1);
        }
        return newNode;
    }
}
