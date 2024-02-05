package com.example.ProjectTwo.Specification;

import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;


public class SpecificationEntity<T> {

    public static Specification equalValue(SingularAttribute singularAttribute, Object object) {
        return (root, query, builder) -> {
            return builder.equal(root.get(singularAttribute), object);
        };
    }
    public static Specification isNotNull(SingularAttribute singularAttribute) {
        return (root, query, builder) -> {
            return builder.isNotNull(root.get(singularAttribute));
        };
    }

    public static Specification ContainsValue(SingularAttribute singularAttribute, String string) {
        return (root, query, builder) -> {
            return builder.like(root.get(singularAttribute),"%" + string + "%" );
        };
    }
    public static Specification isLessValue(SingularAttribute singularAttribute, Comparable comparable) {
        return (root, query, builder) -> {
            return builder.lessThan(root.get(singularAttribute), comparable);
        };
    }

}
