import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class OutFile {

    /*this method is for outputting data into a file*/
    public void outputFile(int position, ArrayList<ArrayList<Integer>> state,ArrayList<ArrayList<Integer>> originState,String path){
        File outFile = new File(path);
        BufferedWriter bufferedWriter = null;
        StringBuilder stringBuilder = new StringBuilder();
        try{
            bufferedWriter = new BufferedWriter(new FileWriter(outFile,true));
            if(state.equals(originState)){
                stringBuilder.append(0 +" ");
            }else {
                stringBuilder.append(((char) (position + 97)) + " ");
            }
            stringBuilder.append("[");
            for (int i = 0; i < state.size(); i++) {
                for (int j = 0; j < state.get(i).size(); j++) {
                    if(j == state.get(i).size() - 1 && i == state.size() - 1){
                        stringBuilder.append(state.get(i).get(j));
                    }else {
                        stringBuilder.append(state.get(i).get(j) + " , ");
                    }
                }
            }
            stringBuilder.append("]");
            bufferedWriter.append("\n");
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
