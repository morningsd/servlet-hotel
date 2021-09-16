package edu.demian.wp.web.action.get;

import edu.demian.wp.model.entity.Type;
import edu.demian.wp.web.action.Action;
import edu.demian.wp.web.action.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class GetAddApplicationAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        List<Type> typeList = Arrays.asList(Type.values());

        request.setAttribute("typeList", typeList);

        return "/client/addApplication";
    }
}
