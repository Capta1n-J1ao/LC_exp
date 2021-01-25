package amazon;

/*
对应lc146

这个题解讲得很清楚了，代码参考下面评论里面Vermouth：
https://leetcode-cn.com/problems/lru-cache/solution/lru-ce-lue-xiang-jie-he-shi-xian-by-labuladong/
* */

import java.util.HashMap;

public class LRUCache {
    public class Node{
        int key, val;
        Node pre, next;
        private Node(int k, int v){
            this.key = k;
            this.val = v;
        }
    }

    private class doubleList{
        Node head = new Node(0,0);
        Node tail = new Node(0,0);
        int size;
        private doubleList(){
            head.next = tail;
            tail.pre = head;
            size = 0;
        }

        private void remove(Node delNode){
            Node temp1 = delNode.pre;
            Node temp2 = delNode.next;
//            注意这里是否会有问题
            temp1.next = temp2;
            temp2.pre = temp1;
            size--;
        }

        private Node removeLast(){
            Node last = tail.pre;
            remove(last);
            return last;
        }

        private void addFirst(Node newHead){
            Node temp = head.next;
            newHead.pre = head;
            head.next = newHead;
            newHead.next = temp;
            temp.pre = newHead;
            size++;
        }

        private int size(){
            return size;
        }
    }


    private int capacity;
    private HashMap<Integer, Node> hashMap;
    private doubleList doubleLinkedList;
    public LRUCache(int capacity) {
        hashMap = new HashMap<>();
        doubleLinkedList = new doubleList();
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!hashMap.containsKey(key)) return -1;
        int val = hashMap.get(key).val;
        put(key, val);
        return val;
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        if(!hashMap.containsKey(key)){
            if(capacity <= doubleLinkedList.size()){
                Node temp = doubleLinkedList.removeLast();
//                下面这句一开始忘了，要记住是对应的关系
                hashMap.remove(temp.key);
            }
            hashMap.put(key, node);
            doubleLinkedList.addFirst(node);
        }else {
            doubleLinkedList.remove(hashMap.get(key));
            doubleLinkedList.addFirst(node);
            hashMap.put(key, node);
        }
    }

//    以下这个写法无效，主要原因还是没有搞清楚Node到底指代的是哪个
//    public int get(int key) {
//        if(!hashMap.containsKey(key)) return -1;
//        return hashMap.get(key).val;
//    }
//
//    public void put(int key, int value) {
//        Node node = new Node(key, value);
//        if(!hashMap.containsKey(key)){
//            if(capacity <= doubleLinkedList.size()){
//                doubleLinkedList.removeLast();
////                下面这句一开始忘了，要记住是对应的关系
//                hashMap.remove(key);
//            }
//            hashMap.put(key, node);
//            doubleLinkedList.addFirst(node);
//        }else {
//            doubleLinkedList.remove(node);
//            doubleLinkedList.addFirst(node);
//        }
//    }
}
