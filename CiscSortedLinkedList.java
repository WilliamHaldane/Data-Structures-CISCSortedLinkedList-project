package edu.ust.cisc;

import java.util.Iterator;
import java.util.Objects;

public class CiscSortedLinkedList<E extends Comparable<E>> implements CiscList<E> {

    private Node<E> head;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if(indexOf(o) != -1){
            return true;
        }
        return false;
        //should look a  t data in node parameter, not the one following the node like
        //some of the other methods we'll do
    }
    @Override
    public Iterator<E> iterator() {
        return new CiscLinkedListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] sizeArray = new Object[size];
        if(head == null){
            return sizeArray;
        }
        int index = 0;
        sizeArray[index] = head.data;
        return toArray(sizeArray, index+1, head.next);
    }
    private Object[] toArray(Object[] arr, int index, Node<E> n){
        if(n == null){
            return arr;
        }
        arr[index] = n.data;
        return toArray(arr, index+1,n.next);
    }

    public String toString(){
        Node<E> n = head;
        String list = "[";
        if(n == null){
            return list.concat("]");
        }
        return(list.concat(toString(n)).concat("]"));
    }
    private String toString(Node<E> n){
        String stg = String.valueOf(n.data);
        if(n.next == null){
            return stg;
        }
        else{
            stg = stg.concat(", ");
            return stg.concat(toString(n.next));
        }
    }
    @Override
    public boolean add(E e) {
        Node<E> n = head;
        if(head.next == null){ // if the list is completely empty, start the list
            head = new Node<E> (e, null);
            size++;
            return true;
        }
        else { // if the list has elements, do this
            add(e, head);
            return true;
        }
    }
    private void add(E element, Node<E> n){
        if(n.next == null){
            //n.next = new Node<E> ((E)o, null);
        //}else if(n.next.data.compareTo((E)o) > 0){

        }
       // else if(n.next.data.compareTo((E)o) == 0){
           // n.next = n;
          //  size++;
       // }
        /*else if(n.next.data.compareTo((E)o) == 0){
            n.next = n.next.next;
            size--;
            return true;
            //linking around the node to the next one

         */
        else{
            //return add(o, n.next);
        }

    }
        @Override
        public boolean remove(Object o) {
        if(size == 0){
            return false;
        }else if(head.data.equals(o)){
            head = head.next;
            size--;
            return true;
        }else{
            return remove(o, head);
        }
    }
    private boolean remove(Object o, Node<E> n){
        if(n.next == null){
            return false;
        }else if(n.next.data.compareTo((E)o) > 0){
            return false;
        }else if(n.next.data.compareTo((E)o) == 0){
            n.next = n.next.next;
            size--;
            return true;
            //linking around the node to the next one
        }else{
            return remove(o, n.next);
        }
    }

        @Override
        public void clear () {
            head = null;
            size = 0;
        }

        @Override
        public E get (int index){
            if(index < 0 || index >= size){
                throw new IndexOutOfBoundsException();
            }
            Node<E> n = head;
            return get(index, 0, n);
        }
        private E get(int index, int currentIndex, Node<E> currentNode){
            if(index == currentIndex) {
                return currentNode.data;
            } else{
                currentIndex++;
                return get(index, currentIndex, currentNode.next);
            }
        }

        @Override
        public E set ( int index, E element){
            throw new UnsupportedOperationException();
        }

        @Override
        public void add ( int index, E element){
            throw new UnsupportedOperationException();
        }

        @Override
        public E remove (int index){
            int currentIndex = 0;
            Node<E> n = head;
            if(index < 0 || index >= size){
                throw new IndexOutOfBoundsException();
            }
            else if(currentIndex == index){
                E value = n.data;
                head = n.next;
                size--;
                return value;
            }
            else if(currentIndex + 1 == index && currentIndex + 1 == size - 1){
                E value = n.next.data;
                n.next = head;
                size--;
                return value;
            }
            else if(currentIndex + 1 == index){
                E value = n.next.data;
                n.next = n.next.next;
                size--;
                return value;
            }
            else{
                return remove(index, currentIndex+1, n.next);
            }
        }
        private E remove(int index, int currentIndex, Node<E> n){
            if(currentIndex+1 == index){
                E value = n.next.data;
                n.next = n.next.next;
                size--;
                return value;
            }
            return remove(index, currentIndex+1, n.next);
        }

        @Override
        public int indexOf (Object o){
            int currentIndex = 0;
            Node<E> n = head;
            if (n == null) {
                return -1;
            } else if (n.data.equals(o)) {
                return 0;
            } else {
                return indexOf(o, currentIndex + 1, n.next);
            }
        }
        private int indexOf (Object o,int currentIndex, Node<E > n){
            if (n.data.equals(o)) {
                return currentIndex;
            } else if (n.data.compareTo((E) o) > 0) {
                return -1;
            } else if (n.next == null) {
                return -1;
            } else {
                return indexOf(o, currentIndex + 1, n.next);
            }
        }

        private static class Node<E> {
            private E data;
            private Node<E> next;

            private Node(E data, Node<E> next) {
                this.data = data;
                this.next = next;
            }
        }
        private class CiscLinkedListIterator implements Iterator<E> {
            private Node<E> nextnode;
            private int nextNodeIndex;

            public CiscLinkedListIterator() {
                nextnode = head;
                nextNodeIndex = 0;
            }

            public boolean hasNext() {
                return nextNodeIndex < size;
            }

            public E next() {
                E value = nextnode.data;
                nextnode = nextnode.next;
                nextNodeIndex++;
                return value;
            }
    }
}

