import java.util.ArrayList;
import java.util.Stack;

public class DFS {
    private Stack<Node> openList = new Stack<Node>();
    private Stack<Node> closeList = new Stack<Node>();

    public boolean search(PuzzleBoard puzzleBoard, ArrayList<ArrayList<Integer>> goal){
        OutFile outFile = new OutFile();
        openList.push(puzzleBoard.root);
        while(!openList.empty()){
            Node x = openList.pop();
            if(x.getState().equals(goal)){// solution found
                System.out.println("Found Solution with DFS");
                outFile.outputFile(x.getPosition(), x.getState(), puzzleBoard.startState,"puzzleDFS.txt");
                return true;
            }else {
                if(x.getLevel() <= 10) {//this is a depth limit here，there is a solution from depth at 4, but shortest solution path is 10
                    puzzleBoard.setNeighbours(x);//to initialise x's children nodes
                    closeList.push(x);

                    outFile.outputFile(x.getPosition(), x.getState(), puzzleBoard.startState, "puzzleDFS.txt");

                    /*push children Node to stack with descending order to achieve ties' order */
                    for (int i = x.getNeighbours().size() - 1; i >= 0; i--) {
                        if (closeList.contains(x.getNeighbours().get(i))) {
                            continue;
                        } else {
                            openList.push(x.getNeighbours().get(i));
                        }
                    }
                }
            }
        }
        System.out.println("No Solution Found");
        return false;
    }
}
