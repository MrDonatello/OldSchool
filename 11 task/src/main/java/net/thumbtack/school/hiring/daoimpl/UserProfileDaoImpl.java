package net.thumbtack.school.hiring.daoimpl;

import net.thumbtack.school.hiring.dao.UserProfileDao;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.service.ServiceException;

public class UserProfileDaoImpl implements UserProfileDao {

    public String activateDeactivateProfile(String token, boolean active) throws ServiceException {
        return Server.getDataBase().activateDeactivateProfile(token, active);
    }

    public String getToken(String login, String password) throws ServiceException {

        return Server.getDataBase().getToken(login, password);
    }

    public String logInServer(String login, String password) throws ServiceException {

        return Server.getDataBase().logInServer(login, password);
    }

    public String logOut(String token) throws ServiceException {

        return Server.getDataBase().logOutServer(token);
    }

    public String leaveServer(String token) throws ServiceException {

        return Server.getDataBase().leaveServer(token);
    }
}
