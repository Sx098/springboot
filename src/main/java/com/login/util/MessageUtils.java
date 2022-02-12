package com.login.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.entity.ResultMessage;

public class MessageUtils {
    public static String getMessage(boolean systemMsgFlag, String fromName, String code,Object message) {
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSystemMsgFlag(systemMsgFlag);
        resultMessage.setMessage(message);
        //如果不是系统消息，就传入fromName
        if (!systemMsgFlag) {
            resultMessage.setFromName(fromName);
            resultMessage.setCode(code);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String repStr = null;
        try {
            repStr = objectMapper.writeValueAsString(resultMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return repStr;
    }
}
