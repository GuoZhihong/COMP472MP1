import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class As {
    public PriorityQueue<Node> openList = new PriorityQueue<Node>(new Comparator<Node>() {
        @Override
        /*this method is to compare two nodes according to their total costs */
        public int compare(Node o1, Node o2) {
            int value = o1.getCost() + o1.getLevel() - (o2.getCost() +o2.getLevel());// total cost = heuristic cost + depth(I used level here)
            if(value > 0){
                return 1;
            }else if(value < 0){
                return -1;
            }else {// this case is to consider two nodes have same total cost,then its order is from shallow to deep in priority Queue
                if(o1.getLevel() < o2.getLevel()){
                    return 1;
                }else if(o1.getLevel() > o2.getLevel()){
                    return -1;
                }else {
                    return 0;
                }
            }
        }
    });

    public PriorityQueue<Node> closeList = new PriorityQueue<Node>(new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {//dont care the close list in what order
            return 0;
        }
    });

    public boolean search(PuzzleBoard puzzleBoard, ArrayList<ArrayList<Integer>> goal, String heuristicType) {
        OutFile outFile = new OutFile();
        Heuristic heuristic = new Heuristic();
        openList.add(puzzleBoard.root);
        while (!openList.isEmpty()) {
            Node x = openList.poll();

            if (x.getState().equals(goal)) {// solution found
                if(heuristicType.equals("H1")){
                    System.out.println("Found Solution for A* with heuristic h1");
                    outFile.outputFile(x.getPosition(), x.getState(), puzzleBoard.startState, "puzzleAS-h1.txt");
                }else if(heuristicType.equals("H2")){
                    System.out.println("Found Solution for A* with heuristic h2");
                    outFile.outputFile(x.getPosition(), x.getState(), puzzleBoard.startState, "puzzleAS-h2.txt");
                }else if(heuristicType.equals("H3")){
                    System.out.println("Found Solution for A* with heuristic h3");
                    outFile.outputFile(x.getPosition(), x.getState(), puzzleBoard.startState, "puzzleAS-h3.txt");
                }
                return true;
            } else {
                puzzleBoard.setNeighbours(x);//to initialise x's children nodes
                closeList.add(x);

                /*output each move in the file*/
                if(heuristicType.equals("H1")){
                    outFile.outputFile(x.getPosition(), x.getState(), puzzleBoard.startState, "puzzleAS-h1.txt");
                }else if(heuristicType.equals("H2")){
                    outFile.outputFile(x.getPosition(), x.getState(), puzzleBoard.startState, "puzzleAS-h2.txt");
                }else if(heuristicType.equals("H3")){
                    outFile.outputFile(x.getPosition(), x.getState(), puzzleBoard.startState, "puzzleAS-h3.txt");
                }

                /*push children Node to priority queue with descending order to achieve ties' order */
                for (int i = 0 ; i < x.getNeighbours().size() ; i++) {
                    if (closeList.contains(x.getNeighbours().get(i))) {
                        continue;
                    } else {
                        /*to set x's heuristic costs for its children nodes*/
                        if(heuristicType.equals("H1")){
                            heuristic.h1(x.getNeighbours().get(i),goal);
                        }else if(heuristicType.equals("H2")){
                            heuristic.h2(x.getNeighbours().get(i),goal);
                        }else if(heuristicType.equals("H3")){
                            heuristic.h3(x.getNeighbours().get(i));
                        }

                        openList.add(x.getNeighbours().get(i));//push children nodes into openlist
                    }
                }
            }
        }
        /*not find solution*/
        System.out.println("No solution Found");
        return false;
    }
}
