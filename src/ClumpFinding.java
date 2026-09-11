public class ClumpFinding {
    private String genome;
    private int lengthGenome;
    private int numTimes;
    private int lengthCode;
    private PatternCount patternCount;
    private StringBuilder mostClumps;
    private MostFreq mostFreq;

    public String ClumpFinding(String gene, int code, int length, int times) {
        genome = gene;
        lengthGenome = length;
        numTimes = times;
        lengthCode = code;
        patternCount = new PatternCount();
        mostClumps = new StringBuilder();
        mostFreq = new MostFreq();
        int count = 0;
            for (int i = 0; i < genome.length(); i++) {
                if(i + length < genome.length()) {
                    if (patternCount.PatternCount(genome.substring(i, i + length), genome.substring(i, i + lengthCode)) >= numTimes) {
                        if(!mostClumps.toString().contains(genome.substring(i, i + lengthCode))) {
                            mostClumps.append(genome.substring(i, i + lengthCode));
                        }
                    }
                }
            }
        return mostClumps.toString();
    }
}
