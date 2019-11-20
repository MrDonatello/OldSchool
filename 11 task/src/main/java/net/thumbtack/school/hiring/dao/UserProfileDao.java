package net.thumbtack.school.hiring.dao;

import net.thumbtack.school.hiring.service.ServiceException;

public interface UserProfileDao {

    String activateDeactivateProfile(String token,boolean active) throws ServiceException;

    String getToken(String login,String password) throws ServiceException;

    String logInServer(String login,String password) throws ServiceException;

    String logOut(String token) throws ServiceException;

    String leaveServer(String token) throws ServiceException;
}
