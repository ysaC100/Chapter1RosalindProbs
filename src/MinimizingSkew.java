import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MinimizingSkew {
    private int count;
    private String genome;
    private StringBuilder index;
    private ArrayList<String> wordList1;
    private ArrayList<String> wordList;

    public String MinimizingSkew(String genes){
        String list = genes;
        wordList = new ArrayList<>();
        for(int j = 0; j < list.length(); j++){
            wordList.add(list.charAt(j)+ "");
        }
        index = new StringBuilder();
        count = 0;
        int min = 0;
        for(int i = 0; i < wordList.size(); i++){
            if(wordList.get(i).equals("C")){
                count--;
            }
            if(wordList.get(i).equals("G")){
                count++;
            }
            if(count < min){
                    min = count;
                    index = new StringBuilder();
                if(!index.toString().contains(index)) {
                    index.append(i + 1 + " ");
                }
            }
            if(count == min){
                index.append(i + 1 + " ");
            }
        }
        return (index.toString());
    }
}