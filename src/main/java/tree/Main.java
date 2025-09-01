package main.java.tree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 7, 9, 8, 3, 2, 4};
        TreeNode treeNode = buildTree(arr);
        printTree(treeNode);
        printTree(flipTree(treeNode));
        printTree(flipTree(treeNode));
        List<Integer> resPre = preIterTree(treeNode);
        System.out.println(resPre);
        List<Integer> resSuf = sufIterTree(treeNode);
        System.out.println(resSuf);
        List<Integer> resMid = midIterTree(treeNode);
        System.out.println(resMid);
        List<List<Integer>> levelOrder = levelOrder(treeNode);
        System.out.println(levelOrder);
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> items = new ArrayList<>();
            int len = queue.size();
            while (len > 0) {
                TreeNode node = queue.poll();
                items.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                len--;
            }
            res.add(items);
        }
        return res;
    }

    public static List<Integer> sufIterTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        Collections.reverse(res);
        return res;
    }

    public static List<Integer> midIterTree(TreeNode root) {
        /*
        5, 7, 9, 8, 3, 2, 4
             5
          7    9
        8   3 2  4
        前序：5 7 8 3 9 2 4
        中序：8 7 3 5 2 9 4
        后序：8 3 7 2 4 9 5
        in : 5 7 8 null // 一路向左，不断的将左节点入栈，直到左节点为空
        out: 8 7 //出栈8，判断右节点为空，继续出栈7。
        in : 3 //判断7右节点不为空，3入栈。判断左节点为空。出栈3。
        out: 3 5 //3出栈，判断3右节点为空，继续出栈5。
        in : 9 2 //判断5右节点不为空，9入栈。9左节点不为空，2继续入栈。判断2左节点为空。
        out: 2 9 //2出栈，2右节点为空，9继续出栈。
        in : 4 //9右节点不为空，4入栈。判断4左节点为空。出栈
        out: 4 //4右边节点为空，出栈，栈为空，结束。
        sum out: 8 7 3 5 2 9 4
        总结思路：入栈往左迭代，出栈往右迭代。
         */
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }

    public static List<Integer> preIterTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        List<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return res;
    }

    /**
     * 反转二叉树
     */
    public static TreeNode flipTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        flipTree(root.left);
        flipTree(root.right);
        swap(root);
        return root;
    }

    public static void swap(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }


    // 将整数数组转换为二叉树
    public static TreeNode buildTree(int[] arr) {
        if (arr.length == 0) return null;

        // 使用队列辅助构建二叉树
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode current = queue.poll();

            // 处理左子节点
            if (arr[i] != Integer.MIN_VALUE) {
                current.left = new TreeNode(arr[i]);
                queue.add(current.left);
            }
            i++;

            // 处理右子节点
            if (arr[i] != Integer.MIN_VALUE) {
                current.right = new TreeNode(arr[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }

    // 打印二叉树（层次遍历）
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("空树");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        System.out.print("二叉树的层次遍历: ");
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val == Integer.MIN_VALUE) {
                System.out.print("null ");
            } else {
                System.out.print(node.val + " ");

                if (node.left != null) {
                    queue.add(node.left);
                } else if (node.right != null) { // 只有右子树时需要占位
                    queue.add(new TreeNode(Integer.MIN_VALUE));
                }

                if (node.right != null) {
                    queue.add(node.right);
                } else if (node.left != null) { // 只有左子树时需要占位
                    queue.add(new TreeNode(Integer.MIN_VALUE));
                }
            }
        }
        System.out.println();
    }
}
