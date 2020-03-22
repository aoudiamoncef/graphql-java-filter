package com.lahzouz.graphqlfilter;

import com.lahzouz.graphqlfilter.util.QueryFile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;


public class CommonFilterTest extends AbstractFilterTest {

    @Test
    public void testSingleAnd() {
        List<Map<String, ?>> nodes = queryNodesAsList(new QueryFile("common", "and", "single"));
        Assertions.assertThat(nodes.size()).isEqualTo(1);
        Assertions.assertThat(nodes.get(0).get("uuid")).isEqualTo("1f9c42ed-506d-481d-b31e-1a9466e31a81");
    }

    @Test
    public void testEmptyAnd() {
        List<Map<String, ?>> nodes = queryNodesAsList(new QueryFile("common", "and", "empty"));
        Assertions.assertThat(nodes.size()).isEqualTo(testData.size());
    }

    @Test
    public void testBogusAnd() {
        List<Map<String, ?>> nodes = queryNodesAsList(new QueryFile("common", "and", "bogus"));
        Assertions.assertThat(nodes.size()).isEqualTo(0);
    }

    @Test
    public void testSimpleAnd() {
        List<Map<String, ?>> nodes = queryNodesAsList(new QueryFile("common", "and", "simple"));
        Assertions.assertThat(nodes.size()).isEqualTo(2);
        nodes.stream()
                .map(node -> ((String) node.get("name")).startsWith("Tree"))
                .forEach(aBoolean -> Assertions.assertThat(aBoolean).isTrue());
    }

    @Test
    public void testSingleOr() {
        List<Map<String, ?>> nodes = queryNodesAsList(new QueryFile("common", "or", "single"));
        Assertions.assertThat(nodes.size()).isEqualTo(1);
        Assertions.assertThat(nodes.get(0).get("uuid")).isEqualTo("1f9c42ed-506d-481d-b31e-1a9466e31a81");
    }

    @Test
    public void testEmptyOr() {
        List<Map<String, ?>> nodes = queryNodesAsList(new QueryFile("common", "or", "empty"));
        Assertions.assertThat(nodes.size()).isEqualTo(testData.size());
    }

    @Test
    public void testMultipleOr() {
        List<Map<String, ?>> nodes = queryNodesAsList(new QueryFile("common", "or", "multiple"));
        Assertions.assertThat(nodes.size()).isEqualTo(1);
    }

    @Test
    public void testSimpleOr() {
        List<Map<String, ?>> nodes = queryNodesAsList(new QueryFile("common", "or", "simple"));
        Assertions.assertThat(nodes.size()).isEqualTo(2);
    }

    @Test
    public void testIsNull() {
        List<Map<String, ?>> nodes = queryNodesAsList(new QueryFile("common", "isNull"));
        Assertions.assertThat(nodes.size()).isEqualTo(1);
    }
}
