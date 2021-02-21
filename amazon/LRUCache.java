package amazon;

/*
对应lc146

这个题解讲得很清楚了，代码参考下面评论里面Vermouth：
https://leetcode-cn.com/problems/lru-cache/solution/lru-ce-lue-xiang-jie-he-shi-xian-by-labuladong/
* */

import java.util.HashMap;

public class LRUCache {
    public class Node{
        int key;
        int value;
//        应该写在这个类里面，而不是doubleList
        Node next, pre;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    public class doubleList{
        Node head = new Node(0,0);
        Node tail = new Node(0,0);
        int size;
        public doubleList(){
            head.next = tail;
            tail.pre = head;
            size = 0;
        }

        public void addFirst(Node newHead){
            Node temp = head.next;
            head.next = newHead;
            newHead.pre = head;
            newHead.next = temp;
            temp.pre = newHead;
            size++;
        }

        public Node pollLast(){
            Node temp = tail.pre;
            remove(temp);
            return temp;
        }

        public void remove(Node removeNode){
            Node rmpre = removeNode.pre;
            Node rmnext = removeNode.next;
            rmpre.next = rmnext;
            rmnext.pre = rmpre;
            size--;
        }
        private int size(){
            return size;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> hashMap;
    private doubleList dbList;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        hashMap = new HashMap<>();
        dbList = new doubleList();
    }

    public int get(int key) {
        if(!hashMap.containsKey(key)) return -1;
        Node getNode = hashMap.get(key);
        put(key, getNode.value);
        return getNode.value;
    }

//    整个数据结构这个函数最容易错，排查了半小时
    public void put(int key, int value) {
        Node putNode = new Node(key, value);
        if(!hashMap.containsKey(key)){
            if(dbList.size >= capacity){
                Node lastNode = dbList.pollLast();
//                这句也容易错
                hashMap.remove(lastNode.key);
            }
            dbList.addFirst(putNode);
            hashMap.put(key, putNode);
        }else {
//            查了好久才找到
//            dbList.remove(putNode);
            dbList.remove(hashMap.get(key));
            dbList.addFirst(putNode);
//            题目里面put的作用第一句话就说明了下面的情况
            hashMap.put(key, putNode);
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
