package com.reactor.datamodel;
/**
 * This class checks the tree for any anomolies*/
public class TreeCheck {
    /**
            * This method checks for  any anomalies in the tree starting from the root node.
            *
            * @return true if an anomaly is detected, {@code false} otherwise.
     * This method is synchronized to prevent concurrent modifications from affecting
     * the tree check.
     */
    public static synchronized boolean anomaly() {
        System.out.println("abe to access the root node for anomaly check");
        return !evaluateTree(AnomalyCache.getRoot());
    }
    /**
     * This method recursively evaluates each node of the tree to check for ordering anomalies
     * between  nodes . There is an  anomaly  if a node's
     * key is not correctly ordered next to to the left or right node.
     * This method is synchronized to ensure that the tree structure is not modified
     * during the evaluation.
     */
    public static synchronized boolean evaluateTree(MyTreeMap<String, Integer>.Node node) {
        if (node == null) {
            System.out.println("node is null and returns false.");
            return false;

        }
        if ((node.left != null && node.key.compareTo(node.left.key) <= 0) ||
                (node.right != null && node.key.compareTo(node.right.key) >= 0)) {
            System.out.println("node inconsistent at " + node.key + "returns true");
            return true;
        }
        return evaluateTree(node.left) || evaluateTree(node.right);
    }
}
