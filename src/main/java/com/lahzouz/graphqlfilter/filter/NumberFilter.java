package com.lahzouz.graphqlfilter.filter;

import com.lahzouz.graphqlfilter.util.FilterUtil;
import graphql.schema.GraphQLInputObjectField;
import graphql.schema.GraphQLInputObjectType;
import graphql.schema.GraphQLInputType;
import graphql.schema.GraphQLList;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static graphql.Scalars.GraphQLBigDecimal;

/**
 * Filters strings by various means
 */
public class NumberFilter extends MainFilter<BigDecimal> {

    private static NumberFilter instance;
    private static GraphQLInputType closeToType = GraphQLInputObjectType.newInputObject()
            .name("CloseToFilter")
            .description("Tests if the number is close to the given number by a given error margin.")
            .field(GraphQLInputObjectField.newInputObjectField()
                    .name("value")
                    .description("The value to compare to")
                    .type(GraphQLBigDecimal)
                    .build())
            .field(GraphQLInputObjectField.newInputObjectField()
                    .name("maxDifference")
                    .description("The maximum allowed difference")
                    .type(GraphQLBigDecimal)
                    .build())
            .build();

    private NumberFilter() {
        super("NumberFilter", "Filters numbers");
    }

    /**
     * Get the singleton string filter
     *
     * @return NumberFilter.
     */
    public static synchronized NumberFilter filter() {
        if (instance == null) {
            instance = new NumberFilter();
        }
        return instance;
    }

    @Override
    protected List<FilterField<BigDecimal, ?>> getFilters() {
        return Arrays.asList(
                FilterField.isNull(),
                FilterField.<BigDecimal, BigDecimal>create("equals",
                        "Compares two numbers for equality. Be careful when comparing floating point numbers, they might be not exact. In that case, use closeTo instead.",
                        GraphQLBigDecimal, query -> FilterUtil.nullablePredicate(val -> val.compareTo(query) == 0)),
                FilterField.<BigDecimal, List<BigDecimal>>create("oneOf", "Tests if the number is equal to one of the given numbers",
                        GraphQLList.list(GraphQLBigDecimal), query -> FilterUtil.nullablePredicate(val -> query.stream().anyMatch(v -> v.compareTo(val) == 0))),
                FilterField.<BigDecimal, BigDecimal>create("gt", "Tests if the number is greater than the given number",
                        GraphQLBigDecimal, query -> FilterUtil.nullablePredicate(val -> val.compareTo(query) > 0)),
                FilterField.<BigDecimal, BigDecimal>create("gte", "Tests if the number is greater than or equal to the given number",
                        GraphQLBigDecimal, query -> FilterUtil.nullablePredicate(val -> val.compareTo(query) >= 0)),
                FilterField.<BigDecimal, BigDecimal>create("lt", "Tests if the number is less than the given number",
                        GraphQLBigDecimal, query -> FilterUtil.nullablePredicate(val -> val.compareTo(query) < 0)),
                FilterField.<BigDecimal, BigDecimal>create("lte", "Tests if the number is less than or equal to the given number",
                        GraphQLBigDecimal, query -> FilterUtil.nullablePredicate(val -> val.compareTo(query) <= 0)),
                FilterField.<BigDecimal, BigDecimal>create("divisibleBy", "Tests if the number is divisible by the given number",
                        GraphQLBigDecimal, query -> FilterUtil.nullablePredicate(val -> val.remainder(query).compareTo(BigDecimal.ZERO) == 0)),
                FilterField.create("closeTo", "Tests if the number is close to the given number by a given error margin.",
                        closeToType, NumberFilter::closeTo));
    }

    private static Predicate<BigDecimal> closeTo(Map<String, BigDecimal> query) {
        BigDecimal value = query.get("value");
        BigDecimal maxDifference = query.get("maxDifference");
        return FilterUtil.nullablePredicate(val -> val.subtract(value).abs().compareTo(maxDifference) <= 0);
    }
}
