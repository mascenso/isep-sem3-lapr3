package app.domain.utils;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public final class RankableMap<K extends Comparable<K>, V extends Comparable<V>> extends TreeMap<K, V> {

    private static final class Pair<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Pair<K, V>> {
        private final K k;
        private final V v;
        private Pair(K k, V v) {
            this.k = k;
            this.v = v;
        }
        @Override
        public int compareTo(Pair<K, V> that) {
            int a = v.compareTo(that.v);
            return a != 0 ? a : k.compareTo(that.k);
        }
    }

    private final SortedSet<Pair<K, V>> set = new TreeSet<>();

    @Override
    public V put(K k, V v) {
        V v2 = super.put(k, v);
        if (v.equals(v2))
            return v2;
        if (v2 != null)
            set.remove(new Pair<>(k, v2));
        set.add(new Pair<>(k, v));
        return v2;
    }

    @Override
    public V remove(Object k) {
        V v = super.remove(k);
        if (v != null)
            set.remove(new Pair<>((K) k, v));
        return v;
    }

    @Override
    public void clear() {
        super.clear();
        set.clear();
    }

    public int rank(K k) {
        return 1 + set.headSet(new Pair<K, V>(k, get(k))).size();
    }

    public RankableMap<K, V> getTopN(int n) {
        RankableMap<K, V> topN = new RankableMap<>();
        int i = 0;
        for (Pair<K, V> pair : set) {
            if (i++ == n) break;
            topN.put(pair.k, pair.v);
        }
        return topN;
    }
}
