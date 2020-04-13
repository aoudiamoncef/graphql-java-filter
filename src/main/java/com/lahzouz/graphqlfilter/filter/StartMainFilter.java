package com.lahzouz.graphqlfilter.filter;

import java.util.Map;

/**
 * A start filter that is also a main filter.
 */
public abstract class StartMainFilter<T> extends MainFilter<T> implements StartFilter<T, Map<String, ?>> {
    public StartMainFilter(String name, String description) {
        super(name, description);
    }
}
