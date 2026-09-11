public class PatternDiffCount {
    private String pattern;
    private String genome;
    private int diff;
    private String startPositions;
    private PatternCount patternCount;
    private HammingDistance hammingDistance;

    public String PatternDiffCount(int num, String text, String newPattern){
        pattern = newPattern;
        genome = text;
        diff = num;
        startPositions = "";
        String genePortion = "";
        hammingDistance = new HammingDistance();
        patternCount = new PatternCount();
        String[] tempString = new String[80];
        for(int i = 0; i < genome.length(); i++) {
            if (i + pattern.length() < genome.length()) {
                genePortion = genome.substring(i, i + pattern.length());
                if (hammingDistance.HammingDistance(genePortion, pattern) <= diff) {
                    patternCount.PatternCount(genome, genePortion);
                    tempString = patternCount.startingPositions().split(" ");
                    String str = "";
                    for(int j = 0; j < tempString.length; j++) {
                        str = tempString[j];
                        if (!startPositions.contains(str)) {
                            startPositions += patternCount.startingPositions();
                        }
                    }
                }
            }
        }
        return startPositions;
    }
}
