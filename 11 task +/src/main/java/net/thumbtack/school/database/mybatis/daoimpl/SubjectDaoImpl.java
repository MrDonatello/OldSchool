package net.thumbtack.school.database.mybatis.daoimpl;

import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.mybatis.dao.SubjectDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SubjectDaoImpl extends DaoImplBase implements SubjectDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectDaoImpl.class);

    @Override
    public Subject insert(Subject subject) {
        LOGGER.debug("DAO insert Subject {}", subject);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSubjectMapper(sqlSession).insert(subject);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't insert Subject {}, {}", subject, e);
                sqlSession.rollback();
                throw e;
            }
            sqlSession.commit();
        }
        return subject;
    }

    @Override
    public Subject getById(int id) {
        LOGGER.debug("DAO get Subject by id {}", id);
        try (SqlSession sqlSession = getSession()) {
            try {
                return getSubjectMapper(sqlSession).getById(id);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't get Subject by id {}, {}", id, e);
                sqlSession.rollback();
                throw e;
            }
        }
    }

    @Override
    public List<Subject> getAll() {
        LOGGER.debug("DAO get All Subject");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getSubjectMapper(sqlSession).getAll();
            } catch (RuntimeException e) {
                LOGGER.debug("Can't get All Subject{},", e);
                sqlSession.rollback();
                throw e;
            }
        }
    }

    @Override
    public Subject update(Subject subject) {
        LOGGER.debug("DAO update Subject {}", subject);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSubjectMapper(sqlSession).update(subject);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't update Subject {}, {}", subject, e);
                sqlSession.rollback();
                throw e;
            }
            sqlSession.commit();
        }
        return subject;
    }

    @Override
    public void delete(Subject subject) {
        LOGGER.debug("DAO delete Subject {}", subject);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSubjectMapper(sqlSession).delete(subject);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't delete Subject {}, {}", subject, e);
                sqlSession.rollback();
                throw e;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Subject");
        try (SqlSession sqlSession = getSession()) {
            try {
                getSubjectMapper(sqlSession).deleteAll();
            } catch (RuntimeException e) {
                LOGGER.debug("Can't delete all Subject", e);
                sqlSession.rollback();
                throw e;
            }
            sqlSession.commit();
        }
    }
}
