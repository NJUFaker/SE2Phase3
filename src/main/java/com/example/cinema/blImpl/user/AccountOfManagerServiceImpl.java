package com.example.cinema.blImpl.user;

import com.example.cinema.bl.user.AccountOfManagerService;
import com.example.cinema.data.user.AccountOfManagerMapper;
import com.example.cinema.po.ManagerPO;
import com.example.cinema.vo.ManagerForm;
import com.example.cinema.vo.ManagerVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountOfManagerServiceImpl implements AccountOfManagerService {
    @Autowired
    private AccountOfManagerMapper accountOfManagerMapper;

    /**
     * 添加管理员(用户名不能重复）
     * @param managerForm
     * @return
     */
    @Override
    public ResponseVO addManager(ManagerForm managerForm){
        try {
            accountOfManagerMapper.addManager(managerForm);
        }
        catch (Exception e){
            return ResponseVO.buildFailure("添加失败，用户名已存在");
        }
        return ResponseVO.buildSuccess();
    }

    /**
     * 删除管理员（根据用户名）
     * @param name 管理员的用户名
     * @return
     */
    @Override
    public ResponseVO deleteManager(String name){
        try{
            ManagerPO managerPO=accountOfManagerMapper.searchByName(name);
            if (managerPO==null){
                return ResponseVO.buildFailure("该用户不存在");
            }
            accountOfManagerMapper.deleteManager(name);
            return ResponseVO.buildSuccess();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("删除失败");
        }
    }

    /**
     * 修改管理员的信息
     * @param
     * @return
     */
    public ResponseVO updateManager(ManagerVO managerVO){
        try {
            accountOfManagerMapper.updateManager(managerVO);
            return ResponseVO.buildSuccess();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("修改失败");
        }
    }

    /**
     * 查找管理员的信息（根据用户名）
     * @param name 管理员的用户名
     * @return
     */
    public ResponseVO searchByName(String name){
        try {
            ManagerPO managerPO=accountOfManagerMapper.searchByName(name);
            if (managerPO==null){
                return ResponseVO.buildFailure("管理员不存在");
            }
            else {
                return ResponseVO.buildSuccess(managerPO.getManagerForm());
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("查询失败");
        }
    }
}