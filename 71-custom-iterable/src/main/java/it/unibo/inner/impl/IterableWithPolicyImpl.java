package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final List<T> array;
    private Predicate<T> filter;

    public IterableWithPolicyImpl (T[] array, Predicate<T> filter) {
        this.array = new ArrayList<T>(List.of(array));
        this.filter = filter;
    }

    public IterableWithPolicyImpl (T[] array) {
        this(array, new Predicate<T>() {
            public boolean test(T elem) {
                return true;
            }
        });
    }

    public Iterator<T> iterator() {
        return new MyIterator();
    }

    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    public String toString() {
        String string = "[";
        for (var elem : this) {
            string = string + elem + ", ";
        }
        string= string + "]";
        return string.toString();
    }

    private class MyIterator implements Iterator<T> {

        private int counter = 0;

        public boolean hasNext() {
            while (counter<IterableWithPolicyImpl.this.array.size()) {
                if (IterableWithPolicyImpl.this.filter.test(IterableWithPolicyImpl.this.array.get(counter))) {
                    return true;
                }
                counter++;
            }
            return false;
        }

        public T next() {
            return IterableWithPolicyImpl.this.array.get(counter++);
        }
    }
    
}
