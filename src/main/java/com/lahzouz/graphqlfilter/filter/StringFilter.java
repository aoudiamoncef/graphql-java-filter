package com.lahzouz.graphqlfilter.filter;

import com.lahzouz.graphqlfilter.util.FilterUtil;
import graphql.schema.GraphQLList;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static graphql.Scalars.GraphQLString;

/**
 * Filters strings by various means
 */
public class StringFilter extends MainFilter<String> {

    private static StringFilter instance;

    private StringFilter() {
        super("StringFilter", "Filters Strings");
    }

    /**
     * Get the singleton string filter
     *
     * @return StringFilter.
     */
    public static synchronized StringFilter filter() {
        if (instance == null) {
            instance = new StringFilter();
        }
        return instance;
    }

    @Override
    protected List<FilterField<String, ?>> getFilters() {
        return Arrays.asList(
                FilterField.isNull(),
                FilterField.create("equals", "Compares two strings for equality", GraphQLString, query -> query::equals),
                FilterField.<String, List<String>>create("oneOf", "Checks if the string is equal to one of the given strings",
                        GraphQLList.list(GraphQLString), query -> query::contains),
                FilterField.<String, String>create("regex", "Checks if the string matches the given regular expression.", GraphQLString,
                        query -> FilterUtil.nullablePredicate(Pattern.compile(query).asPredicate())));
    }
}
