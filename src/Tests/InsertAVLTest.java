package Tests;

import AVL.AVLTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InsertAVLTest {

    private static final double GOLDEN_RATIO = (Math.sqrt(5)+1)/2;


    public static int insertShuffle(AVLTreeTest t, int n) {
        int count = 0;
        List<Integer> l = new ArrayList<>();
        for (int i = 1; i <=n ; i++) {
            l.add(i);
        }
        Collections.shuffle(l);
       for (Integer element: l) {
           count+=t.insert(element,element.toString());
       }
       return count;
    }

    public static int insertDeleteShuffle(AVLTreeTest t, int n) {
        int count = insertShuffle(t,n);
        List<Integer> l = new ArrayList<>();
        for (int i = 1; i <=n ; i++) {
            l.add(i);
        }
        Collections.shuffle(l);
        for (Integer element: l) {
            count+=t.delete(element);
        }
        return count;
    }

    public static boolean isLeagalGoldenRatio(AVLTreeTest t){
        int height = t.getRoot().getHeight();
        int size = t.size();
        double upperBoundForHeight = Math.log(size)/Math.log(GOLDEN_RATIO);
        if (height > upperBoundForHeight) {
            //System.out.println("something is not good with golden ratio");
            return false;
        }

        //System.out.println("the height is " + height);
        //System.out.println("upper bound for height is " + upperBoundForHeight);
        return true;
    }

    public static boolean isLeagalAVL(AVLTreeTest.AVLNode t) {
        if (!t.isRealNode()) {
            return true;
        }
        isLeagalAVL((AVLTreeTest.AVLNode) t.getLeft());
        if (!t.isLegalNode()) {
            //System.out.println(t.getKey());
            //System.out.println(t.getNodeType());
            return false;
        }
        isLeagalAVL((AVLTreeTest.AVLNode) t.getRight());
        return true;
    }
}
