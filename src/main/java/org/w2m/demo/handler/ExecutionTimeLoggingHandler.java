package org.w2m.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Configuration
@Slf4j
public class ExecutionTimeLoggingHandler {

    @Around("@annotation(org.w2m.demo.annotation.ExecutionTimeLog)")
    public void auditExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant start = Instant.now();

        joinPoint.proceed();

        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toSeconds();

        var methodName = getMethodName(joinPoint.getSignature());

        var message = String.format("Method %s cost %s seconds to execute", methodName, timeElapsed);
        log.info(message);
    }

    private String getMethodName(Signature signature) {
        var methodSignature = (MethodSignature) signature;
        var methodParameters = getMethodParameters(methodSignature);
        return getMethodDescription(methodSignature, methodParameters);
    }

    private String getMethodDescription(Signature signature, String methodParameters) {
        var methodNameBuffer = new StringBuffer();
        methodNameBuffer.append(signature.getDeclaringTypeName());
        methodNameBuffer.append("@");
        methodNameBuffer.append(signature.getName());
        methodNameBuffer.append(methodParameters);

        return methodNameBuffer.toString();
    }

    private String getMethodParameters(MethodSignature methodSignature) {
        var parameters = Arrays.stream(methodSignature.getParameterTypes())
                .map(Class::getName)
                .collect(Collectors.toList())
                .toString();
        return String.format("(%s)", parameters);
    }
}
