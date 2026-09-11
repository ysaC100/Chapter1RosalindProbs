import java.util.ArrayList;
import java.util.HashMap;

public class MostFreq {
    private PatternCount patternCounter;
    private int length;
    private HashMap codes;
    private ArrayList<Integer> count;
    private ArrayList<StringBuilder> code;
    private String text;
    public String MostFreq(int lengthCode, String txt){
        count = new ArrayList<>();
        code = new ArrayList<>();
        patternCounter = new PatternCount();
        length = lengthCode;
        text = txt;
        codes = new HashMap<>();
        int countNum = 0;
        String segment = "";
        for(int i = 0; i < text.length(); i++){
            if((length + i) < text.length()) {
                segment = text.substring(i, i + length);
                codes.put(patternCounter.PatternCount(text, segment), segment);
                countNum = patternCounter.PatternCount(text, segment);
                if (count.contains(countNum)) {
                    if(!code.get(count.indexOf(countNum)).toString().contains(segment)) {
                        code.get(count.indexOf(countNum)).append(" " + text.substring(i, i + length));
                    }
                }
                else {
                    if(!code.contains(text.substring(i, i + length))) {
                        code.add(new StringBuilder(text.substring(i, i + length)));
                        count.add(countNum);
                    }
                }
            }
        }
        int mostFreqNum = 0;
        String mostFreq = "";
        for(int j = 0; j < count.size(); j++) {
            if (count.get(j) > mostFreqNum) {
                mostFreqNum = count.get(j);
            }
        }
        mostFreq = code.get(count.indexOf(mostFreqNum)) + "";
        return mostFreq + " " + mostFreqNum;
    }
}