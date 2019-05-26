package com.example.cinema.bl.user;

import com.example.cinema.vo.ManagerForm;
import com.example.cinema.vo.ResponseVO;
/**
 * @author lyp
 * created on 2019.05.26
 */
public interface AccountOfManagerService {
    /**
     * 添加管理员
     * @param managerForm
     * @return
     */
    ResponseVO addManager(ManagerForm managerForm);

    /**
     * 删除管理员（根据用户名）
     * @param name 管理员的用户名
     * @return
     */
    ResponseVO deleteManager(String name);

    /**
     * 修改管理员的信息
     * @param managerForm
     * @return
     */
    ResponseVO updateManager(ManagerForm managerForm);

    /**
     * 查找管理员的信息（根据用户名）
     * @param name 管理员的用户名
     * @return
     */
    ResponseVO searchByName(String name);

}
