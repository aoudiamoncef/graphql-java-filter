package com.lahzouz.graphqlfilter;

import com.lahzouz.graphqlfilter.util.QueryFile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;


public class NumberFilterTest extends AbstractFilterTest {

    @Test
    public void testEquals() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("number", "equals"));

        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0).get("name")).isEqualTo("images");
    }

    @Test
    public void testOneOf() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("number", "oneOf"));

        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result.get(0).get("name")).isEqualTo("images");
        Assertions.assertThat(result.get(1).get("name")).isEqualTo("Tree: Pine");
    }

    @Test
    public void testGT() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("number", "gt"));

        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result.get(0).get("name")).isEqualTo("Tree: Pine");
        Assertions.assertThat(result.get(1).get("name")).isEqualTo("Tree: Oak");
    }

    @Test
    public void testGTE() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("number", "gte"));

        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result.get(0).get("name")).isEqualTo("Tree: Pine");
        Assertions.assertThat(result.get(1).get("name")).isEqualTo("Tree: Oak");
    }

    @Test
    public void testLT() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("number", "lt"));

        Assertions.assertThat(result.size()).isEqualTo(3);
        Assertions.assertThat(result.get(0).get("name")).isEqualTo("images");
        Assertions.assertThat(result.get(1).get("name")).isEqualTo("Tree: Pine");
        Assertions.assertThat(result.get(2).get("name")).isEqualTo("Fruit: Apple");
    }

    @Test
    public void testLTE() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("number", "lte"));

        Assertions.assertThat(result.size()).isEqualTo(4);
        Assertions.assertThat(result.get(0).get("name")).isEqualTo("images");
        Assertions.assertThat(result.get(1).get("name")).isEqualTo("Tree: Pine");
        Assertions.assertThat(result.get(2).get("name")).isEqualTo("Tree: Oak");
        Assertions.assertThat(result.get(3).get("name")).isEqualTo("Fruit: Apple");
    }

    @Test
    public void testDivisible() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("number", "divisible"));

        Assertions.assertThat(result.size()).isEqualTo(3);
        Assertions.assertThat(result.get(0).get("name")).isEqualTo("images");
        Assertions.assertThat(result.get(1).get("name")).isEqualTo("Tree: Pine");
        Assertions.assertThat(result.get(2).get("name")).isEqualTo("Fruit: Apple");
    }

    @Test
    public void testCloseTo() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("number", "closeTo"));

        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0).get("name")).isEqualTo("Tree: Oak");
    }
}
