public class HammingDistance {
    private String strandOne;
    private String strandTwo;
    private int hammingDist;

    public int HammingDistance(String firstStrand, String secondStrand){
        strandOne = firstStrand;
        strandTwo = secondStrand;
        hammingDist = 0;
        for(int i = 0; i < firstStrand.length(); i++){
            if(strandOne.charAt(i) != strandTwo.charAt(i)){
                hammingDist++;
            }
        }
        return hammingDist;
    }
}
