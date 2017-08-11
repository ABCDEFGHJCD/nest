package com.jovezhao.nest.ddd;

import com.jovezhao.nest.ddd.builder.ConstructLoader;
import com.jovezhao.nest.ddd.builder.RepositoryLoader;
import com.jovezhao.nest.ddd.exception.EmptyException;
import com.jovezhao.nest.ddd.identifier.IdGenerator;
import com.jovezhao.nest.ddd.repository.IRoleRepository;
import com.jovezhao.nest.ddd.repository.IUnitOfWork;
import com.jovezhao.nest.ddd.repository.RepositoryManager;
import com.jovezhao.nest.utils.SpringUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhaofujun on 2017/6/20.
 */
public abstract class BaseEntityObject<T extends Identifier> implements Serializable {
    private transient boolean isLoad;

    /**
     * 唯一ID
     */
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    protected boolean OnVerifiying() {
        return true;
    }

    /**
     * 指定一个角色编号来扮演一个角色
     *
     * @param clazz  要扮演的角色的类型
     * @param roleId 如果不为空将通过仓储加载一个数据对象
     * @param <T>
     * @return
     */
    public <T extends BaseRole> T act(Class<T> clazz, Identifier roleId, boolean createByNotfound) {

        if (roleId == null)
            throw new EmptyException("roleId为空，不能扮演角色");
        T t = new RepositoryLoader<>(clazz).create(roleId);

        if (t == null && createByNotfound) {
            //没有找到时创建一个角色
            t = new RepositoryLoader<>(clazz).create(roleId);
        }

        if (t != null)
            t.setActor(this);
        return t;

    }

    /**
     * 扮演一个角色，使用演员的编号
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public <T extends BaseRole> T act(Class<T> tClass, boolean createByNotfound) {
        return act(tClass, this.getId(), createByNotfound);
    }

    private void addToUnitOfWork() {
        IUnitOfWork unitOfWork = SpringUtils.getInstance(IUnitOfWork.class);
        if (unitOfWork != null)
            unitOfWork.addEntityObject(this);
    }

    public <T extends BaseRole> Set<T> findRoles(Class<T> clazz) {

        IRoleRepository<T> repository = (IRoleRepository<T>) RepositoryManager.getEntityRepository(clazz);
        Set<Identifier> ids = repository.getRoleIds(this.id);
        Set<T> tSet = new HashSet<>();
        for (Identifier id : ids) {
            T t = this.act(clazz, id, false);
            tSet.add(t);
        }
        return tSet;
    }


    public void delete() {
        IUnitOfWork unitOfWork = SpringUtils.getInstance(IUnitOfWork.class);
        if (unitOfWork != null)
            unitOfWork.removeEntityObject(this);
    }

    public void setInitValue(String fieldName, Object value) {

    }
}
