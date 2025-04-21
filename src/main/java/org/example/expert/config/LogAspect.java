package org.example.expert.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class LogAspect {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Around("@annotation(org.example.expert.annotation.AccessLog)")
    public Object accessLog(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();

        Object[] args = joinPoint.getArgs();
        String requestBodyJson = convertToJson(args);

        log.info("요청한 사용자의 ID: {} "+",API 요청 시각: {} " + ",API 요청 URL: {} "+ "\n,요청본문: {} ", args[0],LocalDateTime.now(),
                request.getRequestURL(), requestBodyJson);

        Object result = joinPoint.proceed();

        log.info("요청한 사용자의 ID: {} "+",응답본문: {} ",args[0], result);

        return result;
    }

    private String convertToJson(Object object) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        }catch (JsonProcessingException e) {
            return "변환에 실패하였습니다";
        }
    }
}
