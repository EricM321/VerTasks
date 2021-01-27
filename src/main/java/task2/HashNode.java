package task2;

public class HashNode {

    Object key;
    Object value;

    HashNode next;

    public HashNode(Object key, Object value){
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj instanceof HashNode) {
            HashNode entry = (HashNode) obj;

            return key.equals(entry.key) &&
                    value.equals(entry.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash = 17 * hash + ((key == null) ? 0 : key.hashCode());
        hash = 17 * hash + ((value == null) ? 0 : value.hashCode());
        return hash;
    }
}
