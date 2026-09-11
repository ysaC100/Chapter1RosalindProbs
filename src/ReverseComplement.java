public class ReverseComplement {
    private String dNAPattern;
    private String reversePattern;
    private int length;
    public String ReverseComplement(String pattern){
        dNAPattern = pattern;
        length = dNAPattern.length();
        reversePattern = "";
        for(int i = length - 1; i >= 0; i--){
            if(dNAPattern.charAt(i) == 'A'){
                reversePattern += "T";
            }
            if(dNAPattern.charAt(i) == 'T'){
                reversePattern += "A";
            }
            if(dNAPattern.charAt(i) == 'G'){
                reversePattern += "C";
            }
            if(dNAPattern.charAt(i) == 'C'){
                reversePattern += "G";
            }
        }
        return reversePattern;
    }
}
