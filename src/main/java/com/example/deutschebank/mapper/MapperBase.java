package com.example.deutschebank.mapper;

import com.example.deutschebank.mapper.contract.IMapperBase;

import java.util.LinkedList;
import java.util.List;

public abstract class MapperBase<TSource, TDestination> implements
        IMapperBase<TSource, TDestination> {
    public abstract TDestination map(TSource source);
    public List<TDestination> map(List<TSource> source) {
        List<TDestination> destinationList = new LinkedList<>();
        for(TSource obj : source) {
            destinationList.add(map(obj));
        }
        return destinationList;
    }
}