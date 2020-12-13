package Tests;


public class Tests {

    public boolean empty() {
        AVLTreeTest avlTree = new AVLTreeTest();
        if (!avlTree.empty()) {
            return false;
        }
        avlTree.insert(1, "hello");
        return (!avlTree.empty());
    }

    public static boolean search() {
        AVLTreeTest avlTree = new AVLTreeTest();
        if (avlTree.search(1) != null) {
            return false;
        }
        avlTree.insert(1, "hello");
        if (avlTree.search(1).equals("hello")) {
            return true;
        }
        return false;
    }

    public static boolean insert_and_size() {
        AVLTreeTest avlTree = new AVLTreeTest();
        for (int i = 0; i < 1000; i++) {
            avlTree.insert(i, "num" + i);
        }
        return (avlTree.size() == 1000);
    }

    public boolean delete() {
        AVLTreeTest avlTree = new AVLTreeTest();
        if (avlTree.delete(1) != -1) {
            return false;
        }
        for (int i = 0; i < 100; i++) {
            avlTree.insert(i, "num" + i);
        }
        return true;
    }

    public boolean min() {
        AVLTreeTest avlTree = new AVLTreeTest();
        if (avlTree.min() != null) {
            return false;
        }
        for (int i = 0; i < 100; i++) {
            avlTree.insert(i, "num" + i);
        }
        return (avlTree.min().equals("num0"));
    }

    public boolean max() {
        AVLTreeTest avlTree = new AVLTreeTest();
        if (avlTree.max() != null) {
            return false;
        }
        for (int i = 0; i < 100; i++) {
            avlTree.insert(i, "num" + i);
        }
        return (avlTree.max().equals("num99"));
    }

    public boolean min_equals_max() {
        AVLTreeTest avlTree = new AVLTreeTest();
        avlTree.insert(1, "1");
        return (avlTree.min().equals(avlTree.max()));
    }

    public boolean keysToArray() {
        AVLTreeTest avlTree = new AVLTreeTest();
        String infoarray[];
        int[] keysarray;
        for (int i = 0; i < 100; i++) {
            avlTree.insert(i, "num" + i);
        }
        keysarray = avlTree.keysToArray();
        infoarray = avlTree.infoToArray();
        for (int i = 0; i < 100; i++) {
            if (!(infoarray[i].equals("num" + i) && keysarray[i] == i)) {
                return false;
            }
        }
        return true;

    }

    public static boolean size() {
        AVLTreeTest avlTree = new AVLTreeTest();
        for (int i = 0; i < 100; i++) {
            avlTree.insert(i, "num" + i);
        }
        for (int i = 0; i < 50; i++) {
            avlTree.delete(i);
        }
        for (int i = 0; i < 25; i++) {
            avlTree.insert(i, "num" + i);
        }
        return (avlTree.size() == 75);
    }

    public boolean split() {
        AVLTreeTest avlTree = new AVLTreeTest();
        for (int i = 0; i < 1000; i++) {
            avlTree.insert(i, "num" + i);
        }
        avlTree.split(786);
        return true;
    }

    public boolean select() {
        AVLTreeTest avlTree = new AVLTreeTest();
        for (int i = 0; i < 1000; i++) {
			avlTree.insert(i, "num" + i);
        }
        return (avlTree.search(500).equals("num" + 500));
    }

    public boolean avlNodeFuncsImplemented() {
        AVLTreeTest avlTree = new AVLTreeTest();
        avlTree.insert(1, "1");
        ActualAVLTree.IAVLNode avlNode = (ActualAVLTree.IAVLNode) avlTree.getRoot();
        return true;
    }

    public static boolean checkBalanceOfTree(AVLTreeTest.IAVLNode current) {
        boolean balancedRight = true, balancedLeft = true;
        int leftHeight = 0, rightHeight = 0;
        if (current.getRight() != null) {
            balancedRight = checkBalanceOfTree(current.getRight());
            rightHeight = getDepth(current.getRight());
        }
        if (current.getLeft() != null) {
            balancedLeft = checkBalanceOfTree(current.getLeft());
            leftHeight = getDepth(current.getLeft());
        }

        return balancedLeft && balancedRight && Math.abs(leftHeight - rightHeight) < 2;
    }

    public static int getDepth(AVLTreeTest.IAVLNode n) {
        int leftHeight = 0, rightHeight = 0;

        if (n.getRight() != null)
            rightHeight = getDepth(n.getRight());
        if (n.getLeft() != null)
            leftHeight = getDepth(n.getLeft());

        return Math.max(rightHeight, leftHeight) + 1;
    }

    public static boolean checkOrderingOfTree(AVLTreeTest.IAVLNode current) {
        if (current.getLeft().isRealNode()) {
            if (current.getLeft().getKey() > current.getKey())
                return false;
            else
                return checkOrderingOfTree(current.getLeft());
        } else if (current.getRight().isRealNode()) {
            if (current.getRight().getKey() < current.getKey())
                return false;
            else
                return checkOrderingOfTree(current.getRight());
        } else if (!current.getLeft().isRealNode() && !current.getRight().isRealNode())
            return true;

        return true;
    }

    public static boolean testRemove() {
        AVLTreeTest tree = new AVLTreeTest();
        if (!tree.empty()) {
        	System.out.println("error1");
            return false;
        }
        int[] values = new int[]{16, 24, 36, 19, 44, 28, 61, 74, 83, 64, 52, 65, 86, 93, 88};
        for (int val : values) {
            tree.insert(val, "" + val);
        }
        if (!tree.min().equals("16")) {
        	System.out.println("error1");
            return false;
        }
        if (!tree.max().equals("93")) {
        	System.out.println("error2");
            return false;
        }
        if (!checkBalanceOfTree(tree.getRoot())) {
        	System.out.println("error3");
            return false;
        }
        if (!checkOrderingOfTree(tree.getRoot())) {
        	System.out.println("error4");
            return false;
        }
        tree.delete(88);
        if (!checkBalanceOfTree(tree.getRoot())) {
        	System.out.println("error5");
            return false;
        }
        if (!checkOrderingOfTree(tree.getRoot())) {
        	System.out.println("error6");
            return false;
        }
        if (tree.search(88) != null) {
        	System.out.println("error7");
            return false;
        }

        tree.delete(19);
        if (!checkBalanceOfTree(tree.getRoot())) {
        	System.out.println("error8");
            return false;
        }
        if (!checkOrderingOfTree(tree.getRoot())) {
        	System.out.println("error9");
            return false;
        }
        if (tree.search(19) != null) {
        	System.out.println("error10");
            return false;
        }

        tree.delete(16);
        if (!checkBalanceOfTree(tree.getRoot())) {
        	System.out.println("error11");
            return false;
        }
        if (!checkOrderingOfTree(tree.getRoot())) {
        	System.out.println("error12");
            return false;
        }
        if (tree.search(16) != null) {
        	System.out.println("error13");
            return false;
        }

        tree.delete(28);
        if (!checkBalanceOfTree(tree.getRoot())) {
        	System.out.println("error14");
            return false;
        }
        if (!checkOrderingOfTree(tree.getRoot())) {
        	System.out.println("error15");
            return false;
        }
        if (tree.search(16) != null) {
        	System.out.println("error16");
            return false;
        }
        tree.delete(24);
        if (!checkBalanceOfTree(tree.getRoot())) {
        	System.out.println("error17");
            return false;
        }
        if (!checkOrderingOfTree(tree.getRoot())) {
        	System.out.println("error18");
            return false;
        }
        if (tree.search(24) != null) {
        	System.out.println("error19");
            return false;
        }

        tree.delete(36);
        if (!checkBalanceOfTree(tree.getRoot())) {
        	System.out.println("error20");
            return false;
        }
        if (!checkOrderingOfTree(tree.getRoot())) {
        	System.out.println("error21");
            return false;
        }
        if (tree.search(36) != null) {
        	System.out.println("error22");
            return false;
        }

        tree.delete(52);
        if (!checkBalanceOfTree(tree.getRoot())) {
        	System.out.println("error23");
            return false;
        }
        if (!checkOrderingOfTree(tree.getRoot())) {
        	System.out.println("error24");
            return false;
        }
        if (tree.search(52) != null) {
        	System.out.println("error25");
            return false;
        }

        tree.delete(93);
        if (!checkBalanceOfTree(tree.getRoot())) {
        	System.out.println("error26");

            return false;
        }
        if (!checkOrderingOfTree(tree.getRoot())) {
        	System.out.println("error27");
            return false;
        }
        if (tree.search(93) != null) {
        	System.out.println("error28");
            return false;
        }

        tree.delete(86);
        if (!checkBalanceOfTree(tree.getRoot())) {
        	System.out.println("error29");
            return false;
        }
        if (!checkOrderingOfTree(tree.getRoot())) {
        	System.out.println("error30");
            return false;
        }
        if (tree.search(86) != null) {
        	System.out.println("error31");
            return false;
        }

        tree.delete(83);
        if (!checkBalanceOfTree(tree.getRoot())) {
        	System.out.println("error32");
            return false;
        }
        if (!checkOrderingOfTree(tree.getRoot())) {
        	System.out.println("error32");
            return false;
        }
        if (tree.search(83) != null) {
        	System.out.println("error33");
            return false;
        }
        return true;
    }

}
