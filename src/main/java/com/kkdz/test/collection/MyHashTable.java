package com.kkdz.test.collection;

import java.util.Map;

/**
 *
 * @author bbbbln
 * @param <K>
 * @param <V>
 */
public class MyHashTable<K, V> {

    /**
     * 默认容量，16
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    /**
     * 扩容因子，如果数组被使用了75%，那么会进行一次扩容。
     */
    static final float DEFAULT_LOAD_FACTOR = .75f;
    private Node<K, V>[] table;

    public MyHashTable() {
    }

    //改变table的大小
    private Node<K, V>[] resize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 构造一个内部静态类，用来保存HashMap中的每一个最小整体单位。<br>
     *
     * 而用静态内部类的原因是：这个内部类不用访问外部类的任何资源。<br>
     *
     * 顺便提一点：如果内部内不用引用外部类的资源，那么都可以定义为静态的。<br>
     *
     * @param <K>
     * @param <V>
     */
    static class Node<K, V> implements Map.Entry<K, V> {

        /**
         * 当时节点的hash值
         */
        final int hash;

        final K key;

        V value;

        /**
         * 指向链表的下一个节点的引用
         */
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }

    }

    /**
     * 散列算法。目的是尽量解决hash碰撞问题。 这个是1.8版本的实现。<br>
     * 这样实现的是一个挠动函数：参见<br>
     * JDK 源码中 HashMap 的 hash 方法原理是什么？ - 胖君的回答 - 知乎<br>
     * https://www.zhihu.com/question/20733617/answer/111577937<br>
     * 1.7版本的实现<br>
     * static int hash(int h) { <br>
     * h ^= (h >>> 20) ^ (h >>> 12);<br>
     * return h ^ (h >>> 7) ^ (h >>> 4);<br> }
     *
     *
     * @param key
     * @return
     */
    static final int hash(Object key) {
        //复习一遍： & | ^(异或) ~(非)
        // >> << 右移或左移，用符号填充。>>>高位用0填充 。无<<<运算符
        int h;
        int index = key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        return index;
    }

    public V put(K key, V value) {
        int hash = hash(key);
        Node<K, V>[] tab;
        Node<K, V> p;
        int i;
        //如果table为空，那么初始化
        if ((tab = table) == null || table.length == 0) {
            tab = resize();
        }
        //如果key在table数组中不存在，就添加。否则 。。。
        if ((p = tab[i = indexFor(hash)]) == null) {
            tab[i] = newNode(hash, key, value);
        } else {
            //在If文句中，已经为p和i赋值了。
            Node<K, V> e;
            K k;
            //查找出的对象p的hash等于新的hash，并且，key也相同，表明是数组i位置的链的第一个node，替换其value。否则。。。
            if (p.hash == hash && ((k = p.key) == key || (k != null && k.equals(key)))) {
                V oldValue = p.value;
                p.value = value;
                return oldValue;
            } else {
                //从链表的第一个往后遍历，找不到就添加到最后的next位置，找到的话，就把p的指针指向e
                while (true) {
                    e = p.next;
                    if (e == null) {
                        p.next = newNode(hash, key, value);
                        break;
                    }
                    if (e.hash == hash && (e.key == key || (key != null && key.equals(e.key)))) {
                        V oldValue = e.value;
                        e.value = value;
                        return oldValue;
                    }
                    p = e;
                }
            }
        }
        return null;
    }

    private int indexFor(int hash) {
        return table.length & hash;
    }

    private Node<K, V> newNode(int hash, K key, V value) {
        return new Node<>(hash, key, value, null);
    }
}
