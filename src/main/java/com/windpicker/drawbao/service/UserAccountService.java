package com.windpicker.drawbao.service;

import com.windpicker.drawbao.mapper.UserAccountMapper;
import com.windpicker.drawbao.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserAccountService {

    @Autowired
    private WxService wxService;

    @Autowired
    private UserAccountMapper userAccountMapper;

    public UserAccount login(String code, String openidFromClient) throws Exception {
        String wxid = openidFromClient;

        // 如果没有提供openid，尝试通过code换openid
        if (wxid == null || wxid.isEmpty()) {
            wxid = wxService.getOpenIdFromCode(code);
        }

        UserAccount user = userAccountMapper.findByWxid(wxid);
        if (user == null) {
            user = new UserAccount();
            user.setWxid(wxid);
            user.setChances(0);
            user.setRewards(BigDecimal.ZERO);
            userAccountMapper.insert(user);
        }

        return user;
    }
}
