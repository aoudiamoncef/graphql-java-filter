package com.gentics.graphqlfilter;

import com.gentics.graphqlfilter.util.QueryFile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;


public class StringFilterTest extends AbstractFilterTest {

    @Test
    public void testEquals() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("string", "equals"));

        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0).get("uuid")).isEqualTo("e018fa14-39ed-431c-b09d-b27097b48b85");
    }

    @Test
    public void testOneOf() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("string", "oneOf"));

        Assertions.assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void testRegex() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("string", "regex"));

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
