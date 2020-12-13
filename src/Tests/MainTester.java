package Tests;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainTester {
	private static final double GOLDEN_RATION = (Math.sqrt(5)+1)/2;
	
	public static void main(String[] args) {
		if (!empty()) System.out.println("Error in empty"); //Checks empty trees works fine
		else System.out.println("empty is fine");
		if (!Tests.search()) System.out.println("Error in search"); //Checks basic search
		else System.out.println("search is fine");
		//if (!insertDelete_and_size()) System.out.println("Error in insertDelete_and_size"); //Checks basic size
		//else System.out.println("insert delete size is fine");
		if (!delete()) System.out.println("Error in delete"); //Checks basic delete
		else System.out.println("delete is fine");
		if (!min()) System.out.println("Error in min"); //Checks basic min
		else System.out.println("min is fine");
		if (!max()) System.out.println("Error in max"); //Checks basic max
		else System.out.println("max is fine");
		if (!min_equals_max()) System.out.println("Error in min_equals_max"); //Checks for one node if min equals max
		else System.out.println("min equals max is fine");
		if (!keysToArray()) System.out.println("Error in keysToArray"); //Checks keysToArray
		else System.out.println("keys to array is fine");
		if (!split()) System.out.println("Error in split"); //Checks basic split
		else System.out.println("split is fine");
		if (!split2()) System.out.println("Error in split2"); //Checks basic split
		else System.out.println("split2 is fine");
		if (!join()) System.out.println("Error in join"); //Checks basic join
		else System.out.println("join is fine");
		if (!select()) System.out.println("Error in select"); //Checks basic search
		else System.out.println("select is fine");
		if (!avlNodeFuncsImplemented()) System.out.println("Error in avlNodeFuncsImplemented"); //??
		if (!Tests.testRemove()) System.out.println("Error in testRemove"); //Checks correctness of tree
		else System.out.println("remove is fine");
		
		System.out.println("Yay! We made it!");
	}
	
	
	/**
	 * The methods we use in the tests
	 * 
	 */
	public static void insertN(AVLTreeTest t, int n, int start) {
		for (int i = start; i <n+start ; i++) {
			t.insert(i,"i"+i);
		}
	}
	
	public static void deleteN(AVLTreeTest t, int n, int start) {
		for (int i = start; i <n+start ; i++) {
			t.delete(i);
		}
	}
	
	public static int insertShuffle(AVLTreeTest t, int n) {
        int count = 0;
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i <n ; i++) {
            l.add(i);
        }
        Collections.shuffle(l);
       for (Integer element: l) {
           count+=t.insert(element,"i"+element.toString());
       }
       return count;
    }
	
	public static int insertDeleteShuffle(AVLTreeTest t, int n) {
        int count = insertShuffle(t,n);
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i <n ; i++) {
            l.add(i);
        }
        Collections.shuffle(l);
        for (Integer element: l) {
            count+=t.delete(element);
        }
        return count;
    }
	
	public static boolean empty() {
        AVLTreeTest avlTree = new AVLTreeTest();
        if (!avlTree.empty()) {
            return false;
        }
        avlTree.insert(1, "hello");
        return (!avlTree.empty());
    }
	

	public static boolean insertDelete_and_size() {
        AVLTreeTest avlTree = new AVLTreeTest();
        insertShuffle(avlTree,1000);
        insertDeleteShuffle(avlTree,500);
        
        if (avlTree.size() != 500) {
        	return false;
        }
        
        AVLTreeTest t = new AVLTreeTest();
        for (int i = 0; i < 100; i++) {
            t.insert(i, "num" + i);
        }
        for (int i = 0; i < 50; i++) {
            t.delete(i);
        }
        for (int i = 0; i < 25; i++) {
            t.insert(i, "num" + i);
        }
        return (t.size() == 75);

    }
	
	public static boolean delete() {
        AVLTreeTest avlTree = new AVLTreeTest();
        if (avlTree.delete(1) != -1) {
            return false;
        }
        insertN(avlTree, 5,0);
        deleteN(avlTree,6,0);
        if (avlTree.size()!= 0) {
            return false;
        }
        if (!avlTree.empty()) {
            return false;
        }
        if (avlTree.min() != null) {
            return false;
        }
        if (avlTree.max() != null) {
            return false;
        }
        return true;
    }
	
	public static boolean min() {
        printableTree avlTree = new printableTree();
        if (avlTree.min() != null) {
            return false;
        }
        insertN(avlTree,100,0);
        deleteN(avlTree,50,0);
        deleteN(avlTree,10,90);
        if (!avlTree.min().equals("i50")) {
        	System.out.println("i50");
            return false;
        }
       // if (!avlTree.max().equals("i89")) {
        //	System.out.println("i89");
        	//System.out.println("got " + avlTree.max());
            //return false;
        //}
        if (avlTree.size() != 40) {
        	System.out.println("size");
        	System.out.println("got " + avlTree.size());
            return false;
        }

        return true;
    }
	
	public static boolean max() {
        AVLTreeTest avlTree = new AVLTreeTest();
        if (avlTree.max() != null) {
            return false;
        }
        insertN(avlTree, 1000,0);
        return (avlTree.max().equals("i999"));
    }
	
	public static boolean min_equals_max() {
        AVLTreeTest avlTree = new AVLTreeTest();
        avlTree.insert(1, "1");
        return (avlTree.min().equals(avlTree.max()));
    }
	
	public static boolean keysToArray() {
        AVLTreeTest avlTree = new AVLTreeTest();
        String infoarray[];
        int[] keysarray;
        insertShuffle(avlTree,1000);
        
        keysarray = avlTree.keysToArray();
        infoarray = avlTree.infoToArray();
        
        for (int i = 0; i < 100; i++) {
            if (!(infoarray[i].equals("i" + i) && keysarray[i] == i)) {
                return false;
            }
        }
        return true;
    }
	
	public static boolean split() {
        AVLTreeTest avlTree = new AVLTreeTest();
        for (int i = 0; i < 12; i++) {
            avlTree.insert(i, "num" + i);
        }
        AVLTreeTest[] tt = avlTree.split(6);
        
        if ((tt[0].size()!=6)||(tt[1].size()!=5)) {
        	System.out.println("error 1");
        	System.out.println("got left " + tt[0].size());
        	System.out.println("got right " + tt[1].size());
        	return false;
        }
        if ((tt[0].max()=="num"+5)||(tt[1].min()=="num"+7)){
        	System.out.println("error 2");
        	return false;
        }
        if ((!InsertAVLTest.isLeagalGoldenRatio(tt[0]))||(!InsertAVLTest.isLeagalGoldenRatio(tt[1]))) {
        	System.out.println("error 3");
        	return false;
        }
       // if ((!InsertAVLTest.isLeagalAVL((AVLTreeTest.AVLNode)tt[0].getRoot()))||(!InsertAVLTest.isLeagalAVL((AVLTreeTest.AVLNode)tt[1].getRoot()))) {
        //	return false;
        //}
        
        AVLTreeTest t1 = new AVLTreeTest();
        insertN(t1, 2, 0);
        AVLTreeTest[] tt1 = t1.split(1);
        if ((tt1[0].size()!=1)||(!tt1[1].empty())) {
        	return false;
        }
        
        return true;
    }
	
	
	public static boolean split2() {
        AVLTreeTest avlTree = new AVLTreeTest();
        for (int i = 0; i < 1000; i++) {
            avlTree.insert(i, "num" + i);
        }
        AVLTreeTest[] tt = avlTree.split(786);
        
        if ((tt[0].size()!=786)||(tt[1].size()!=213)) {
        	return false;
        }
        if ((tt[0].max()=="num"+785)||(tt[1].min()=="num"+787)){
        	return false;
        }
        if ((!InsertAVLTest.isLeagalGoldenRatio(tt[0]))||(!InsertAVLTest.isLeagalGoldenRatio(tt[1]))) {
        	return false;
        }
        //if ((!InsertAVLTest.isLeagalAVL((AVLTreeTest.AVLNode)tt[0].getRoot()))||(!InsertAVLTest.isLeagalAVL((AVLTreeTest.AVLNode)tt[1].getRoot()))) {
        	//return false;
        //}
        
        AVLTreeTest t1 = new AVLTreeTest();
        insertN(t1, 2, 0);
        AVLTreeTest[] tt1 = t1.split(1);
        if ((tt1[0].size()!=1)||(!tt1[1].empty())) {
        	return false;
        }
        
        return true;
    }
	
	public static boolean join() {
		AVLTreeTest x = new AVLTreeTest();
        AVLTreeTest t1 = new AVLTreeTest();
        AVLTreeTest t2 = new AVLTreeTest();
        
        insertN(t1, 100, 0);
        deleteN(t1, 10, 10);
        insertN(t2, 100, 101);
        x.insert(100, "i"+100);
        t1.join(x.getRoot(), t2);
        
        if (t1.size()!=191) {
        	System.out.print("error 1 join, ");
        	System.out.println("got " + t1.size());
        	return false;
        }
        if (!Tests.checkBalanceOfTree(t1.getRoot())) {
        	System.out.println("error 2 join");
        	return false;
        }
        if (!Tests.checkOrderingOfTree(t1.getRoot())) {
        	System.out.println("error 3 join");
            return false;
        }
        if (!InsertAVLTest.isLeagalGoldenRatio(t1)) {
        	System.out.println("error 4 join");
            return false;
        }
        if (!InsertAVLTest.isLeagalAVL((AVLTreeTest.AVLNode)t1.getRoot())) {
        	System.out.println("error 5 join");
            return false;
        }
        
        t1 = new AVLTreeTest();
        t2 = new AVLTreeTest();
        t1.join(x.getRoot(),t2);
        if ((t1.getRoot().getKey()!=x.getRoot().getKey())||(t1.size()!=1)) {
        	return false;
        }
        
        t1.insert(0, "i0");
        t1.join(x.getRoot(),t2);
        if ((t1.getRoot().getKey()!=x.getRoot().getKey())||(t1.size()!=2)) {
        	return false;
        }
        
        t1 = new AVLTreeTest();
        t2.insert(200, "i200");
        t1.join(x.getRoot(),t2);
        if (t1.size()!=2) {
        	return false;
        }
        
        return true;
    }
	
	public static boolean select() {
        AVLTreeTest avlTree = new AVLTreeTest();
        for (int i = 0; i < 1000; i++) {
			avlTree.insert(i, "num" + i);
        }
        return (avlTree.search(500).equals("num" + 500));
    }
	
	public static boolean avlNodeFuncsImplemented() {
        AVLTreeTest avlTree = new AVLTreeTest();
        avlTree.insert(1, "1");
        ActualAVLTree.IAVLNode avlNode = (ActualAVLTree.IAVLNode) avlTree.getRoot();
        return true;
    }
	

}
