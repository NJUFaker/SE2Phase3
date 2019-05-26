package com.example.cinema.controller.user;

import com.example.cinema.bl.user.AccountOfManagerService;
import com.example.cinema.vo.ManagerForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lyp
 * created on 2019.05.26
 */
@RestController()
@RequestMapping("/accountOfManager")
public class AccountOfManagerController {
    @Autowired
    AccountOfManagerService accountOfManagerService;

    @PostMapping("/add")
    public ResponseVO addManager(@RequestBody ManagerForm managerForm){
        return accountOfManagerService.addManager(managerForm);
    }

    @DeleteMapping("/delete")
    public ResponseVO deleteManager(@RequestParam String name){
        return accountOfManagerService.deleteManager(name);
    }

    @PostMapping("/update")
    public ResponseVO updateManager(@RequestBody ManagerForm managerForm){
        return  accountOfManagerService.updateManager(managerForm);
    }

    @GetMapping("/get")
    public ResponseVO searchByName(@RequestParam String name){
        return accountOfManagerService.searchByName(name);
    }
}
