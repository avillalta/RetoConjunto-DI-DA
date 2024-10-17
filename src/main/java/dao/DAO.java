package dao;

import java.util.List;

public interface DAO<T>{
    public List<T> findAll();
    public T findById(Integer id);
    public boolean save(T t);
    public void update(T t);
    public boolean delete(T t);
    public void deleteById(Integer id);
}
