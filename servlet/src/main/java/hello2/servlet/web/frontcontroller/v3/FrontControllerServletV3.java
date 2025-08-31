package hello2.servlet.web.frontcontroller.v3;

import hello2.servlet.web.frontcontroller.ModelView;
import hello2.servlet.web.frontcontroller.MyView;
import hello2.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello2.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello2.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 1. 요청 파라미터 정보를 Map으로 변환
        Map<String, String> paramMap = createParamMap(request);

        // 2. 컨트롤러 호출 및 ModelView 객체 반환
        ModelView mv = controller.process(paramMap);

        // 3. 논리 뷰 이름을 물리 뷰 경로로 변환 (viewResolver)
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        // 4. 뷰 렌더링 (모델 데이터를 request attribute로 전달)
        // ※ 참고: 실제 동작을 위해서는 MyView의 render 메소드가 모델을 request attribute로 옮기는 로직이 필요합니다.
        view.render(mv.getModel(), request, response);
    }

    /**
     * request 객체의 모든 파라미터 정보를 읽어서 Map<String, String> 형태로 반환하는 메소드
     */
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    /**
     * 논리적인 뷰 이름을 받아서 실제 물리적인 뷰 경로를 완성하여 MyView 객체를 반환하는 메소드
     */
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}