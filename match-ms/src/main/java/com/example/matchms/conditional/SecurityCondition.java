package com.example.matchms.conditional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class SecurityCondition implements Condition {

    @Value("${security.enabled}")
    public Boolean securityEnabled;

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return securityEnabled == null || securityEnabled;
    }
}
