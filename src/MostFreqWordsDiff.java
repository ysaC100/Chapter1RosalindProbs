import java.util.*;

public class MostFreqWordsDiff {
    private String text;
    private String pattern;
    private int lengthGene;
    private int missMatchNum;
    private StringBuilder newStr;
    private ArrayList<String> oneOffs;
    private Set<String> kmerTest = new HashSet<>();

    public MostFreqWordsDiff(String gene, int length, int diff) {
        text = gene;
        pattern = "";
        lengthGene = length;
        missMatchNum = diff;
        newStr = new StringBuilder();
        oneOffs = new ArrayList<>();
    }
    public MostFreqWordsDiff() {
        text = "";
        pattern = "";
        lengthGene = 0;
        missMatchNum = 0;
        newStr = new StringBuilder();
        oneOffs = new ArrayList<>();
    }

    public ArrayList<String> getOneOffs(String kmer) {
        String letter = "";
        String segment = kmer;
        String endKmer = "";
        oneOffs = new ArrayList<>();
        int countG = 0;
        int countC = 0;
        int countT = 0;
        int countA = 0;
        for (int i = 0; i < kmer.length(); i++) {
            for(int j = 0; j < 4; j++){ //AACAA
                letter = segment.substring(i, i + 1);
                if (!letter.equals("T") && countT == 0) {
                    countT++;
                    if(i == (segment.length())- 1){
                        endKmer = segment.substring(0, i) + 'T';
                    }
                    else if(i == 0) {
                        endKmer = 'T' + segment.substring(i + 1);
                    }
                    else if(i - 1 == 0){//if( i > 0 && i < kmer.length() - 1){
                            endKmer = segment.substring(0, 1) + 'T' + segment.substring(i + 1);
                    }
                    else{
                        endKmer = segment.substring(0, i) + 'T' + segment.substring(i + 1); // i-1, i
                    }
                }
                else if (!letter.equals("G") && countG == 0) {
                    countG++;
                    if(i == segment.length() - 1){
                        endKmer = segment.substring(0, i) + 'G';
                    }
                    else if(i == 0) {
                        endKmer = 'G' + segment.substring(i + 1);
                    }
                    else if(i - 1 == 0){//if( i > 0 && i < kmer.length() - 1){
                        endKmer = segment.substring(0, 1) + 'G' + segment.substring(i + 1);
                    }
                    else {
                        endKmer = segment.substring(0, i) + 'G' + segment.substring(i + 1);
                    }
                }
                else if (!letter.equals("C") &&  countC == 0) {
                    countC++;
                    if(i == segment.length() - 1){
                        endKmer = segment.substring(0, i) + 'C';
                    }
                    else if(i == 0) {
                        endKmer = 'C' + segment.substring(i + 1);
                    }
                    else if(i - 1 == 0){//if( i > 0 && i < kmer.length() - 1){
                        endKmer = segment.substring(0, 1) + 'C' + segment.substring(i + 1);
                    }
                    else {
                        endKmer = segment.substring(0, i) + 'C' + segment.substring(i + 1);
                    }
                }
                else if (!letter.equals("A") && countA == 0) {
                    countA++;
                    if(i == segment.length() - 1){
                        endKmer = segment.substring(0, i) + 'A';
                    }
                    else if(i == 0) {
                        endKmer = 'A' + segment.substring(i + 1);
                    }
                    else if(i - 1 == 0){//if( i > 0 && i < kmer.length() - 1){
                        endKmer = segment.substring(0, 1) + 'A' + segment.substring(i + 1);
                    }
                    else {
                        endKmer = segment.substring(0, i) + 'A' + segment.substring(i + 1);
                    }
                }
                if (!oneOffs.contains(endKmer)) {
                    oneOffs.add(endKmer);
                }
            }
            countG = 0;
            countC = 0;
            countT = 0;
            countA = 0;
        }
        return oneOffs;
    }

    public Set<String> getDOffs(String kmer, int currentMismatch, int d) {
        Set<String> resultSet = new HashSet<>();
        if (currentMismatch <= d) {
            for(int i = 0; i < getOneOffs(kmer).size(); i++) {
                String kmers = getOneOffs(kmer).get(i);
                //resultSet.add(oneOff);
                //if(!resultSet.contains(kmers)) {
                    resultSet.add(kmers);
                //}
                Set<String> tempSet = getDOffs(kmers, currentMismatch + 1, d);
                for(String str: tempSet) {
                        resultSet.add(str);
                }
            }
        }
        return resultSet;
    }
    //getDOfffs("AA", 1, 2) AA, AC, AG, AT, CA, CC, CG, CT, GA, GC, GG, GT, TA, TC, TG, TT
    //AA, TT, GG, CC, AC, CG, AG, TA, GT, TC, CT, AT, TG, GA, GC, CA
    public String mainMethod() {
        // get inputs
        Map<String, Integer> resultMap = new HashMap<>();
        Set<String> kmerSet = new HashSet<>();
        String kmer = "";
        StringBuilder keyCheck = new StringBuilder();
        ReverseComplement r1 = new ReverseComplement();
        for (int i = 0; i < text.length(); i++) { // each kmer in text AATAATACAAAGCAACACAAATCAAGACAAAACGAAACAAACCAAAAAAAAACCAAAGAAAACACAACAG
            if(i + lengthGene < text.length()) {
                kmer = text.substring(i, i + lengthGene);
                kmerSet = getDOffs(kmer, 1, missMatchNum); // get kmers up to "d" mismatches away
                kmerSet.add(kmer);
                //keyCheck.append("Kmer: " + kmer + " :");
                String kmers = "";
                for(String str: kmerSet){
                    kmers += str;
                }
                for (String strKmer: kmerSet) {//each k-mer in kmerSet for(String kmerFromSet: kmerSet){
                        String revComp = r1.ReverseComplement(strKmer);
                        if (resultMap.containsKey(strKmer)) { //k - mer in resultMap
                            resultMap.replace(strKmer, resultMap.get(strKmer), resultMap.get(strKmer) + 1);//value for the kmer//add 1 to this k - mer count in resultMap
                        }
                        else {
                            resultMap.put(strKmer, 1);//add k -mer to resultMap with value of 1
                        }
                        if(resultMap.containsKey(revComp)) {
                            resultMap.replace(revComp, resultMap.get(revComp), resultMap.get(revComp) + 1);//add 1 for this revComp(k - mer) in resultMap
                        }
			            else{
                            resultMap.put(revComp, 1);//add new revComp(k - mer) to resultMap with value of 1
                        }
                    }
            }
        }
        //output most frequent k -mers in resultMap
        String values = resultMap.values().toString();
        values = values.substring(1, values.length() - 1);
        String[] vals = values.split(", ");
        int count = 0;
        for(int num = 0; num < vals.length; num++) {
            if (count < Integer.parseInt(vals[num])) {
                count = Integer.parseInt(vals[num]);
            }
        }
        String keys = "";
        for(String k: resultMap.keySet()){
            keys += k;
        }
        for(int i = 0; i < keys.length(); i++) {
            if (i + lengthGene < keys.length()) {
                String key = keys.substring(i , i + lengthGene);
                if(resultMap.get(key) != null && !keyCheck.toString().contains(key)) {
                    int val = resultMap.get(key);
                    if (val >= count) {
                        keyCheck.append(key);
                        keyCheck.append(" ");
                    }
                }
            }
        }
        return keyCheck.toString();
    }
}