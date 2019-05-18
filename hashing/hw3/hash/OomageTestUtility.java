package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] bucketArray = new int[M];
        int bucketNum = 0;
        for (int i = 0; i < oomages.size(); i++) {
            bucketNum = (oomages.get(i).hashCode() & 0x7FFFFFFF) % M;
            bucketArray[bucketNum] += 1;
        }
        for (int j = 0; j < bucketArray.length; j++) {
            if (bucketArray[j] > (oomages.size() / 2.5) || bucketArray[j] < (oomages.size() / 50)) {
                return false;
            }
        }
        return true;

    }
}
