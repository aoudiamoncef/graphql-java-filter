package com.lahzouz.graphqlfilter;

import com.lahzouz.graphqlfilter.util.QueryFile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;


public class DateFilterTest extends AbstractFilterTest {

    @Test
    public void testEquals() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("date", "equals"));

        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0).get("name")).isEqualTo("images");
    }

    @Test
    public void testBefore() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("date", "before"));

        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0).get("name")).isEqualTo("images");
    }

    @Test
    public void testAfter() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("date", "after"));

        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result.get(0).get("name")).isEqualTo("Tree: Oak");
        Assertions.assertThat(result.get(1).get("name")).isEqualTo("Fruit: Apple");
    }

    @Test
    public void testBetween() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("date", "between"));

        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0).get("name")).isEqualTo("Tree: Pine");
    }

    @Test
    public void testFuture() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("date", "future"));

        Assertions.assertThat(result.size()).isEqualTo(0);
    }

    @Test
    public void testPast() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("date", "past"));

        Assertions.assertThat(result.size()).isEqualTo(4);
    }

    @Test
    public void testFormat() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("date", "format"));

        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result.get(0).get("name")).isEqualTo("Tree: Oak");
        Assertions.assertThat(result.get(1).get("name")).isEqualTo("Fruit: Apple");
    }
}
