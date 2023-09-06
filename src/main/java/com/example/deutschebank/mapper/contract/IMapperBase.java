package com.example.deutschebank.mapper.contract;

import java.util.List;

/**
 * The IMapperBase interface defines a contract for mapping objects from a source type to a destination type.
 * Classes that implement this interface must provide methods to map a single source object to a destination object
 * and to map a list of source objects to a list of destination objects.
 *
 * @param <TSource>      The source type to be mapped from.
 * @param <TDestination> The destination type to be mapped to.
 */

public interface IMapperBase<TSource, TDestination> {
    /**
     * Maps a single source object to a destination object.
     *
     * @param source The source object "entity" to be mapped.
     * @return The mapped "DTO" destination object.
     */
    TDestination map(TSource source);
    /**
     * Maps a list of source objects to a list of destination objects.
     *
     * @param source The list of source objects to be mapped.
     * @return The list of mapped destination objects.
     */
    List<TDestination> map(List<TSource> source);
}
