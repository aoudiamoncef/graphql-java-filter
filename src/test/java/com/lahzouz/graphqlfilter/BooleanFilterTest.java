package com.lahzouz.graphqlfilter;

import com.lahzouz.graphqlfilter.util.QueryFile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;


public class BooleanFilterTest extends AbstractFilterTest {
    @Test
    public void testBoolean() {
        List<Map<String, ?>> result = queryNodesAsList(new QueryFile("boolean", "published"));

        Assertions.assertThat(result.size()).isEqualTo(3);

        result.stream()
                .map(node -> (Boolean) node.get("published"))
                .forEach(aBoolean -> Assertions.assertThat(aBoolean).isTrue());
    }
}
