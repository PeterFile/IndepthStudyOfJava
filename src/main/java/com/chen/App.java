package com.chen;

import java.util.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        boolean res = searchMatrix(matrix, 5);

        System.out.println(res);
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        boolean res = false;

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] > target || matrix[i][n - 1] < target)
                continue;
            res = binary(matrix[i], target);
        }

        return res;
    }

    static boolean binary(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
}

class LRUCache {
    private HashMap<Integer, Node> map;
    private DoubleList cache;
    private int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    private void makeRecently(int key) {
        Node x = map.get(key);
        cache.remove(x);
        cache.addLast(x);
    }


    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        makeRecently(key);
        return map.get(key).value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            cache.remove(map.get(key));
            Node x = new Node(key, value);
            map.put(key, x);
            return;
        }

        if (cap == cache.size()) {
            Node deleted = cache.removeFirst();
            map.remove(deleted.key);
        }

        Node x = new Node(key, value);
        map.put(key, x);
    }
}

class DoubleList {
    private Node head, tail;
    private int size;

    public DoubleList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void addLast(Node x) {
        x.prev = tail.prev;
        x.next = tail;
        tail.prev.next = x;
        tail.prev = x;
        size++;
    }

    public void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    public Node removeFirst() {
        if (head.next == tail) return null;
        Node first = head.next;
        remove(first);
        return first;
    }

    public int size() {
        return size;
    }
}

class Node {
    public int key, value;
    public Node next, prev;

    public Node(int k, int v) {
        this.key = k;
        this.value = v;
    }
}