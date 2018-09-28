package xyz.stackoverflow.blog.validator;

import xyz.stackoverflow.blog.pojo.vo.BaseInfoVO;

import java.util.HashMap;
import java.util.Map;

public class BaseInfoValidator extends AbstractBaseValidator<BaseInfoVO> {

    @Override
    public Map validate(BaseInfoVO baseInfoVO){
        Map map = new HashMap<String,String>();

        if(!validateEmail(baseInfoVO.getEmail())){
            map.put("email","邮箱格式错误");
        }else if(!validateNickName(baseInfoVO.getNickname())){
            map.put("nickname","昵称长度要大于等于0");
        }else if(!validateSignature(baseInfoVO.getSignature())){
            map.put("signature","个性签名长度要大于0");
        }
        return map;
    }

}
