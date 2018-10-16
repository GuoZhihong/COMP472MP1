import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        /*This part is for uncertain puzzle size,start and goal,so only experiment parts used this part*/

//        Scanner inputStream = new Scanner(System.in);
//        System.out.println("Please input your puzzle size,ex:3*4");
//        String size = inputStream.next();
//        if(!size.contains("*")){
//            System.out.println("Wrong size input");
//            System.exit(1);
//        }
//        int columnNumber,rowNumber;
//        String [] sArray = size.split("\\*");
//        rowNumber = Integer.valueOf(sArray[0]);
//        columnNumber = Integer.valueOf(sArray[1]);
//        System.out.println("Please input all of elements in your puzzle one by one");
//
//        /*initialise the puzzle*/
//        int input;
//        ArrayList<ArrayList<Integer>> start = new ArrayList<ArrayList<Integer>>();
//
//        for (int i = 0; i < rowNumber; i++) {
//            ArrayList<Integer> row =new ArrayList<Integer>();
//            start.add(row);
//            for (int j = 0; j < columnNumber; j++) {
//                input = inputStream.nextInt();
//                row.add(input);
//            }
//        }
//
//
//        System.out.println("Please input your final goal one by one");
//        ArrayList<ArrayList<Integer>> goal = new ArrayList<ArrayList<Integer>>();
//        for (int i = 0; i < rowNumber; i++) {
//            ArrayList<Integer> row =new ArrayList<Integer>();
//            goal.add(row);
//            for (int j = 0; j < columnNumber; j++) {
//                input = inputStream.nextInt();
//                row.add(input);
//            }
//        }

        /*Fixed 11-d puzzle with example data given by the project*/
        ArrayList<Integer> arrayList1 = new ArrayList<Integer>(){{add(10);add(11);add(6);add(7);}};
        ArrayList<Integer> arrayList2 = new ArrayList<Integer>(){{add(5);add(2);add(8);add(4);}};
        ArrayList<Integer> arrayList3 = new ArrayList<Integer>(){{add(9);add(3);add(1);add(0);}};
        ArrayList<ArrayList<Integer>> start = new ArrayList<ArrayList<Integer>>(){{add(arrayList1);add(arrayList2);add(arrayList3);}};

        PuzzleBoard puzzleBoard = new PuzzleBoard(start);

        ArrayList<Integer> arrayList4 = new ArrayList<Integer>(){{add(1);add(2);add(3);add(4);}};
        ArrayList<Integer> arrayList5 = new ArrayList<Integer>(){{add(5);add(6);add(7);add(8);}};
        ArrayList<Integer> arrayList6 = new ArrayList<Integer>(){{add(9);add(10);add(11);add(0);}};
        ArrayList<ArrayList<Integer>> goal = new ArrayList<ArrayList<Integer>>(){{add(arrayList4);add(arrayList5);add(arrayList6);}};


        DFS dfs = new DFS();
        System.out.println(dfs.search(puzzleBoard,goal));
        BFS bfs = new BFS();
        System.out.println(bfs.search(puzzleBoard,goal,"H1"));
        System.out.println(bfs.search(puzzleBoard,goal,"H2"));
        System.out.println(bfs.search(puzzleBoard,goal,"H3"));
        As as = new As();
        System.out.println(as.search(puzzleBoard,goal,"H1"));
        System.out.println(as.search(puzzleBoard,goal,"H2"));
        System.out.println(as.search(puzzleBoard,goal,"H3"));

    }
}
