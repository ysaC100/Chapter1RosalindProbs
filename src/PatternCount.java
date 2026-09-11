public class PatternCount {
    private int count;
    private String newText;
    private String newPattern;
    private String startPositions;
    public int PatternCount(String text, String pattern) {
        count = 0;
        newPattern = pattern;
        newText = text;
        startPositions = "";
        for(int i = 0; i < (Math.abs(newText.length() - newPattern.length())); i++) {
            if (i < newText.length()) {
                if (newText.substring(i, newPattern.length() + i).equals(newPattern)) {
                    count = count + 1;
                    startPositions += i + " ";
                }
            }
        }
        return count;
    }
    public String startingPositions(){
        return startPositions;
    }
}