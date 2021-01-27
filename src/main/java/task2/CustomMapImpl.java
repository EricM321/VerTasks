package task2;

import java.util.ArrayList;

public class CustomMapImpl implements CustomMap{

    private static final int MAX_BUCKETS = 100;
    private int numBuckets = 10;
    private HashNode[] buckets;
    private int size = 0;

    public CustomMapImpl(){
        buckets = new HashNode[numBuckets];
    }

    private int getIndex(Object key){
        return Math.abs(key.hashCode()) % numBuckets;
    }

    @Override
    public void put(Object key, Object value) {
        if ((1.0*size)/numBuckets >= 0.7 && numBuckets != MAX_BUCKETS){
            HashNode[] oldBuckets = buckets;

            numBuckets += 10;
            size = 0;
            buckets = new HashNode[numBuckets];

            for (HashNode old : oldBuckets){
                while (old != null){
                    put(old.key, old.value);
                    old = old.next;
                }
            }
        } else if(numBuckets == MAX_BUCKETS && size == MAX_BUCKETS){
            System.out.println("MAX STORAGE REACHED!");
            return;
        }

        HashNode newEntry = new HashNode(key, value);

        int bucketIndex = getIndex(key);

        HashNode currentNode = buckets[bucketIndex];

        if(currentNode == null){
            buckets[bucketIndex] = newEntry;
            size++;
        } else{
            while(currentNode.next != null){
                if(currentNode.key.equals(key)){
                    currentNode.value = value;
                    return;
                }
                currentNode = currentNode.next;
            }
            if(currentNode.key.equals(key)){
                currentNode.value = value;
            } else {
                currentNode.next = newEntry;
                size++;
            }
        }
    }

    @Override
    public Object get(Object key) {
        HashNode currentNode = buckets[getIndex(key)];

        while(currentNode != null){
            if(currentNode.key.equals(key)){
                return currentNode.value;
            }

            currentNode = currentNode.next;
        }
        return null;
    }

    @Override
    public Object remove(Object key) {
        int bucketIndex = getIndex(key);
        HashNode currentNode = buckets[bucketIndex];
        HashNode previousNode = null;

        while (currentNode != null){
            if(currentNode.key.equals(key)){
                break;
            }

            previousNode = currentNode;
            currentNode = currentNode.next;
        }

        if(currentNode == null){
            return null;
        }

        size--;

        if(previousNode != null){
            previousNode.next = currentNode.next;
        } else {
            buckets[bucketIndex] = currentNode.next;
        }

        return currentNode.value;
    }

    @Override
    public int size() {
        return size;
    }
}
