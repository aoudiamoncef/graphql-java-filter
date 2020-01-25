package com.gentics.graphqlfilter;

import com.gentics.graphqlfilter.util.QueryFile;
import graphql.ExecutionResult;
import graphql.GraphQLError;
import graphql.validation.ValidationError;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;


public class NestedFilterTest extends AbstractFilterTest {

    @Test
    public void testEnum() {
        List<Map<String, ?>> nodes = queryNodesAsList(new QueryFile("nested", "enum"));
        Assertions.assertThat(nodes.size()).isEqualTo(4);
    }

    @Test
    public void testInvalidEnum() {
        ExecutionResult result = queryNodes(new QueryFile("nested", "invalidEnum"), false);
        Assertions.assertThat(result.getErrors().size()).isEqualTo(1);
        GraphQLError error = result.getErrors().get(0);
        ValidationError validationError = (ValidationError) error;
        Assertions.assertThat(validationError.getValidationErrorType().name()).isEqualTo("WrongType");
    }

    @Test
    public void testSchemaUuid() {
        List<Map<String, ?>> nodes = queryNodesAsList(new QueryFile("nested", "schemaUuid"));
        Assertions.assertThat(nodes.size()).isEqualTo(4);
    }
}
