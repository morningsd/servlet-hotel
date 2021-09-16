package edu.demian.wp.web.action;

import edu.demian.wp.web.action.get.*;
import edu.demian.wp.web.action.post.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Map<String, Action> actions = new HashMap<>();

    static {
        actions.put("GET/home", new GetHomeAction());
        actions.put("GET/login", new GetLoginAction());
        actions.put("POST/login", new PostLoginAction());
        actions.put("GET/register", new GetRegisterAction());
        actions.put("POST/register", new PostRegisterAction());
        actions.put("GET/logout", new GetLogoutAction());
        actions.put("GET/rooms", new GetRoomsAction());
        actions.put("GET/client/addApplication", new GetAddApplicationAction());
        actions.put("POST/client/addApplication", new PostAddApplicationAction());
        actions.put("GET/manager/addRoom", new GetAddRoomAction());
        actions.put("POST/manager/addRoom", new PostAddRoomAction());
        actions.put("GET/manager/editRoom", new GetEditRoomAction());
        actions.put("POST/manager/editRoom", new PostEditRoomAction());
        actions.put("GET/manager/deleteRoom", new GetDeleteRoomAction());
        actions.put("GET/client/cabinet", new GetCabinetAction());
        actions.put("POST/client/bookRoom", new PostBookRoomAction());
        actions.put("POST/client/payBill", new PostPayBillAction());
        actions.put("GET/manager/viewApplications", new GetViewApplicationsAction());
        actions.put("POST/manager/applicationOffer", new PostApplicationOfferAction());
        actions.put("POST/client/cancelApplication", new PostCancelApplicationAction());
    }

    public static Action getAction(HttpServletRequest request) {
        return actions.get(request.getMethod() + request.getPathInfo());
    }
}

