import java.util.ArrayList;

public class Heuristic {
    public void h1(Node node,ArrayList<ArrayList<Integer>> goal){
        int hammingDistance = 0;
        for (int i = 0; i < node.getState().size(); i++) {
            for (int j = 0; j < node.getState().get(i).size(); j++) {
                if(node.getState().get(i).get(j) != goal.get(i).get(j)){
                    if(node.getState().get(i).get(j) != 0) {//except empty tile, hamming distance for this state.
                        hammingDistance++;
                    }
                }
            }
        }
        node.setCost(hammingDistance);
    }
    public void h2(Node node,ArrayList<ArrayList<Integer>> goal){
        int manhattanDistance = 0;
        for (int i = 0; i < node.getState().size(); i++) {
            for (int j = 0; j < node.getState().get(i).size(); j++) {
                int goalRow = 0;int goalColumn = 0;int currentRow = i; int currentColumn = j;//coordinates
                if(node.getState().get(i).get(j) != goal.get(i).get(j)){//case we need to calculate manhattanDistance,values of all other cases are 0
                    for (int k = 0; k < goal.size(); k++) {
                        for (int l = 0; l < goal.get(k).size(); l++) {
                            if(node.getState().get(i).get(j) == goal.get(k).get(l)){
                                goalRow = k;
                                goalColumn = j;
                            }
                        }
                    }
                    manhattanDistance += Math.abs(currentColumn - goalColumn);
                    manhattanDistance += Math.abs(currentRow - goalRow);
                }
            }
        }
        node.setCost(manhattanDistance);
    }
    public void h3(Node node){
        int permutationInversions = 0;
        ArrayList<Integer> permutationList = new ArrayList<Integer>();
        for (int i = 0; i < node.getState().size(); i++) {//convert 2-D array to 1-D to do permutation inversion
            for (int j = 0; j < node.getState().get(i).size(); j++) {
                permutationList.add(node.getState().get(i).get(j));
            }
        }
        for (int i = 0; i < permutationList.size(); i++) {//do permutation inversion on 1-D array
            for (int j = i; j < permutationList.size(); j++) {
                if(permutationList.get(i) > permutationList.get(j) && permutationList.get(j) != 0){
                    permutationInversions++;
                }
            }
        }
        node.setCost(permutationInversions);
    }
}
