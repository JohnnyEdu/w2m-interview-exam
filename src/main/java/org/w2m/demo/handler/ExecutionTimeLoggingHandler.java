package org.w2m.demo.handler;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.Instant;

@Configuration
@Slf4j
public class ExecutionTimeLoggingHandler {

    @Around("@annotation(ExecutionTimeLog)")
    public void auditExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant start = Instant.now();

        joinPoint.proceed();

        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();

        var methodName = joinPoint.getSignature().getName();
        var message = String.format("Method %s cost %s millis to execute", methodName, timeElapsed);
        log.info(message);
    }
}
