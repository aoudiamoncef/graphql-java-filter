# GraphQL Filter

[![CircleCI](https://circleci.com/gh/aoudiamoncef/graphql-java-filter.svg?style=svg)](https://circleci.com/gh/aoudiamoncef/graphql-java-filter)
[![Download](https://api.bintray.com/packages/sparow199/maven/graphql-java-filter/images/download.svg) ](https://bintray.com/sparow199/maven/graphql-java-filter/_latestVersion)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/2f8ef13df5414d73a36bbbdcf7ffe312)](https://www.codacy.com/manual/Sparow199/graphql-java-filter?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=aoudiamoncef/graphql-java-filter&amp;utm_campaign=Badge_Grade)
[![Known Vulnerabilities](https://snyk.io/test/github/aoudiamoncef/graphql-java-filter/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/aoudiamoncef/graphql-java-filter?targetFile=pom.xml)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Faoudiamoncef%2Fgraphql-java-filter.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Faoudiamoncef%2Fgraphql-java-filter?ref=badge_shield)
[![License](https://img.shields.io/github/license/aoudiamoncef/graphql-java-filter?color=yellow)](https://opensource.org/licenses/Apache-2.0)

Enhance your GraphQL API with filters.

Use this library to let the user filter through the results of a GraphQL query.

### Getting Started

1. Add Graphql Java filter to your project's dependencies:

```xml
   <dependency>
       <groupId>com.github.aoudiamoncef</groupId>
       <artifactId>graphql-java-filter</artifactId>
       <version>1.2.1-SNAPSHOT</version>
   </dependency>
```

## Live Demo
[Here is an implementation of this library.](https://demo.getmesh.io/api/v1/demo/graphql/browser)

Try this query:

```graphql
{
  nodes(filter: {
    schema: { is: vehicle },
    fields: {
      vehicle: {
        price: {
          lt: 2000000
        }
      }
    }
  }) {
    elements {
      fields {
        ... on vehicle {
          name
          price
        }
      }
    }
  }
}
```

Play around with the autocompletion to see whats possible.

## Usage
Create your filter by implementing the `Filter` interface or by extending one of the predefined abstract filters. It is best to create your filter by composing the small predefined filters.

See [AbstractFilterTest.java](src/test/java/com/gentics/graphqlfilter/AbstractFilterTest.java) to see an example of how to integrate this library in your environment.

## Overview

### Interfaces
* `Filter` The main Filter interface. Every filter implements this.
* `FilterField` can be used to create filters that can easily be nested in other filters.
* `StartFilter` is used to easily create the argument object for `graphql-java`

### Helper filters
* `MainFilter` A filter that does not filter directly and instead contains a collection of other filters.
* `StartMainFilter` Same as above but additionally implements StartFilter.
* `MappedFilter` Used to map a type into another type using another Filter
* `CommonFilters` Provides common filters which can be used for all types. This includes these logical operations of filters: `and`, `or` and `not`.

### Predefined filters
The following filters can be used to filter primitive types: `BooleanFilter`, `DateFilter`, `NumberFilter`, `StringFilter`.

## Examples
See [NodeFilter.java](src/test/java/com/gentics/graphqlfilter/filter/NodeFilter.java)  as an example filter implementation.