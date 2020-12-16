package Tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Tests.AVLTreeTest.AVLNode;


public class Measurments {

	public static long insertionSort(Integer[] arr) 
    { 
		long count = 0;
        int n = arr.length; 
        for (int i = 1; i < n; i++) { 
            int key = arr[i]; 
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j]; 
                count++;
                j = j - 1; 
            } 
            arr[j + 1] = key; 
        }
        return count;
    }
	
	public static Integer[] downArray(Integer n) {
		Integer[] res = new Integer[n];
		for (int i = 0 ; i<n; i++)
			res[i] = n-i-1;
		return res;
	}
	
	public static Integer[] shuffleArray(Integer n) {
		Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        List<Integer> intList = Arrays.asList(arr);
        Collections.shuffle(intList);
        intList.toArray(arr);
        
        return arr;
	}

	
	static void printArray(Integer[] arr)
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
	
	static Integer[] sortedArray(int n) {
		Integer[] res = new Integer[n];
		//Arrays.sort(arr);
		for (int i=0; i<n; i++) 
			res[i]=i;
		return res;
	}
	
	static void part1Array () {
		for (int i=1; i<11; i++) {
			Integer[] randArr = shuffleArray(10000*i);
			//long[] randArrSorted = Arrays.copyOf(randArr, 10000*i);
			System.out.println("Array rand " + 10000*i + " count: " + insertionSort(randArr));
			//Arrays.sort(randArrSorted);
			//System.out.println(Arrays.equals(randArrSorted, randArr));
			Integer[] downArr = downArray(10000*i);
			System.out.println("Array down " + 10000*i + " count: " + insertionSort(downArr));
			//System.out.println(Arrays.equals(downArr, sortedArray(10000*i)));
		}
	}
	
	
	static void part1AVL() {
		for (int i=1; i<11; i++) {
			long count1 = 0;
			Integer[] randArr = shuffleArray(10000*i);
			FSAVLTree t1 = new FSAVLTree();
			count1 = t1.insertToTree(randArr);
			//long[] randArrSorted = Arrays.copyOf(randArr, 10000*i);
			System.out.println("AVL rand " + 10000*i + " count: " + count1);
			//Arrays.sort(randArrSorted);
			//System.out.println(Arrays.equals(randArrSorted, randArr));
			
			long count2 = 0;
			Integer[] downArr = downArray(10000*i);
			FSAVLTree t2 = new FSAVLTree();
			count2 = t2.insertToTree(downArr);
			System.out.println("AVL down " + 10000*i + " count: " + count2);
			//System.out.println(Arrays.equals(downArr, sortedArray(10000*i)));
		}
	}
	
	
	static void part2RandomSplit() {
		Random rand = new Random();
		System.out.println("Random:");
		for (int i=1; i<11; i++) {
			Integer[] randArr = shuffleArray(10000*i);
			NoFSAVLTree t1 = new NoFSAVLTree();
			t1.insertToTreeNoFS(randArr);
			int randomKey = rand.nextInt(10000*i);
			t1.split(randomKey);
			
		}
	}
	
	static void part2LeftMaxSplit() {
		System.out.println("MaxLeft:");
		for (int i=1; i<11; i++) {
			Integer[] randArr = shuffleArray(10000*i);
			NoFSAVLTree t1 = new NoFSAVLTree();
			t1.insertToTreeNoFS(randArr);
			AVLNode n = t1.findLeftMaxNode();
			t1.split(n.getKey());
			
		}
	}
	
public static void main(String[] args)
{
	System.out.println("~~~ Part 1 ~~~");
	//part1Array();
	//part1AVL();
	System.out.println("~~~ Part 2 ~~~");
	part2RandomSplit();
	part2LeftMaxSplit();
}

public static class NoFSAVLTree extends AVLTreeTest {
	public NoFSAVLTree() {
		super();
	}
	
	private void insertToTreeNoFS(Integer[] arr) {    	
    	for (int num : arr) {
    		this.insertNoFS(num, ""+num);
    	}
    }
    
    public int insertNoFS(int k, String i) {
 	   AVLNode n = new AVLNode(k, i);
 	   if (this.getRoot() == null) { // if the tree is empty
 		   this.root = n;
 		   return 0;
 	   }
 	   int num = insertBST(n); //inserting n according to BST rules
 	   if (num == -1) // if the key already exists in tree
 		   return -1;
 	   else 
 		   num = rebalanceInsert((AVLNode)n.getParent()); // rebalancing the tree
 	   
 	   updateSize(n); // updating the size attribute of the relevant nodes
 	   return num; // return number of rebalancing operations
    }
    
    @Override
    protected AVLNode clone(AVLNode n) {
  	  AVLNode res = new AVLNode(n.getKey(), n.getValue());
  	  res.setHeight(n.getHeight());
  	  return res;
    }
    
    @Override
    public AVLTreeTest[] split(int x) {
    	long sumJoin = 0;
    	long sum = 0;
    	long count = 0;
    	long maxJoin = 0;
    	
 	   AVLTreeTest T1 = new AVLTreeTest(); // tree with smaller keys
 	   AVLTreeTest T2 = new AVLTreeTest(); // tree with bigger keys
 	   AVLTreeTest temp = new AVLTreeTest();
 	   
 	   AVLNode n = treePosition(x); // finding x's node
 	   if (n.getLeft().getKey() != -1) {
 		   T1.root = n.getLeft(); // Initialising the smaller tree
 		   T1.root.setParent(null);
 	   }
 	   if (n.getRight().getKey() != -1) {
 		   T2.root = n.getRight(); // Initialising the bigger tree
 		   T2.root.setParent(null);
 	   }
 	   
 	   AVLNode cur = n;
 	   while (cur.getParent() != null) {
 		   if (cur.getParent().getRight() == cur) {// if cur is a right child			   
 			   temp.root = cur.getParent().getLeft();
 			   temp.root.setParent(null);
 			   if (temp.root.getKey() == -1)
 				   temp.root = null;
 			   AVLNode y = clone((AVLNode)cur.getParent());
 			   sumJoin += T1.join(y, temp);
 			   count++;
 		   }
 		   else { // if cur is a left child
 			   temp.root = cur.getParent().getRight();
 			   temp.root.setParent(null);
 			   if (temp.root.getKey() == -1)
 				   temp.root = null;
 			   AVLNode y = clone((AVLNode)cur.getParent());
 			   sumJoin += T2.join(y, temp);
 			   count++;
 		   }
 		  if (sumJoin > maxJoin)
 	 		   maxJoin = sumJoin;
 	 	   sum += sumJoin;
 		   cur = (AVLNode)cur.getParent();
 	   }
 	   
 	   System.out.println("splitting over " + x + ", count = " + count + ", sum = " + sum + ", maxJoin = " + maxJoin + ", avg join cost = " + sum/count);
 	   T1.rebalanceInsert((AVLNode)n.getLeft().getParent());
 	   T2.rebalanceInsert((AVLNode)n.getRight().getParent());
 	   AVLTreeTest[] result = {T1,T2}; 
 	   return result;
    }
    
    public AVLNode findLeftMaxNode() {
    	AVLNode node = (AVLNode)this.getRoot().getLeft();
    	while (node.getRight().getKey() != -1)  // stops on the virtual leaf's parent
			   node = (AVLNode)node.getRight();
		   return node;
    }
    
    
}


public static class FSAVLTree extends AVLTreeTest {
	protected AVLNode maxNode;
	
	public FSAVLTree() {
		super();
		this.maxNode = null;
	}
	
	private long insertPosition(int key) {
    	long count = 0;
    	AVLNode maxNode = this.maxNode;

        while ((maxNode.getKey() > key) && (maxNode != this.root)) {
            count += 1;
            maxNode = (AVLNode) maxNode.getParent();
        }

        AVLNode x = maxNode;
        AVLNode y = x;
        while (x.isRealNode()) {
            count += 1;
            y = x;
            if (key == x.getKey()) {
                return count;
            } else {
                if (key < x.getKey()) {
                    x = (AVLNode) x.getLeft();
                } else {
                    x = (AVLNode) x.getRight();
                }
            }
        }
        return 0;
    }
    
    private long insertToTree(Integer[] arr) {
    	long count = 0;
    	FSAVLTree t = new FSAVLTree();    	
    	for (int num : arr) {
    		t.insert(num, ""+num);
    		count += t.insertPosition(num);
    	}
    	return count;
    }
    
    
    
    @Override
    public int insert(int k, String i) {
 	   AVLNode n = new AVLNode(k, i);
 	   if (this.getRoot() == null) { // if the tree is empty
 		   this.root = n;
 		   this.maxNode = n;
 		   return 0;
 	   }
 	   int num = insertBST(n); //inserting n according to BST rules
 	   if (num == -1) // if the key already exists in tree
 		   return -1;
 	   else 
 		   num = rebalanceInsert((AVLNode)n.getParent()); // rebalancing the tree
 	   
 	   updateSize(n); // updating the size attribute of the relevant nodes
 	  if (k > this.maxNode.getKey())
			this.maxNode = n;
 	   return num; // return number of rebalancing operations
    }
    
    
}
}
