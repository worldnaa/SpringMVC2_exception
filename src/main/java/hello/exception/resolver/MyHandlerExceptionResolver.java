package hello.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        log.info("call resolver", ex);

        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage()); //예외를 여기서 먹어서 400으로 보내기
                return new ModelAndView(); //새로운 ModelAndView 를 빈 값으로 넘김 (정상 흐름으로 다시 진행 됨)
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("resolver ex", e);
        }

        return null; //예외가 다시 터져서 계속 날라간다
    }
}
