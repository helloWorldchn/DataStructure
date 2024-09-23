package kmp;

// KMP算法
public class KnuthMorrisPratt {
	public int indexKMP(String target, String pattern) {
		if (pattern.length() == 0) {
			return 0;
		}
		int[] next = getNext(pattern);
        int i = 0;
        int j = 0;
        while(i < target.length() && j < pattern.length()){
            if (j == -1 || target.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }else {
                j = next[j];
            }
        }
 
        if (j >= pattern.length()){
            return i-j;
        }else {
            return -1;
        }

	}
	
	public int[] getNext(String pattern) {
		int[] next = new int[pattern.length()];
        next[0] = -1;
        next[1] = 0;
 
        int i = 2;
        int j = 0;
 
        while(i < pattern.length()){
            if (j == -1 || pattern.charAt(j) == pattern.charAt(i-1)){
                next[i] = j+1;
                i++;
                j++;
            }else {
                j = next[j];
            }
        }
        return next;
	}
	
	public static void main(String[] args) {
		String targetString = "ABCABCAE";  // 目标字符串targetString
		String patternString = "ABCAE";  // 模式字符串patternString
		KnuthMorrisPratt knuthMorrisPratt = new KnuthMorrisPratt();
		int index = knuthMorrisPratt.indexKMP(targetString, patternString);
		System.out.println(index);
	}
}
