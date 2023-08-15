package com.example.module6.service;
import com.example.module6.model.Message;

import java.util.List;

public interface IMessageService extends IGeneralService<Message,Long>{
    List<Message> findMessageByReceiverId(Long receiverId);
    Message saveMessage(Message message);
}
