package com.github.siphon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemberList<T> implements Iterable<T> {
    List<T> list = new ArrayList<>();
    int size;

    public MemberList(int size){
        this.size = size;
    }

    public int getSize(){
        return size;
    }

    int getCurrentSize(){
        return list.size();
    }

    public T get(int index){
        return list.get(index);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
