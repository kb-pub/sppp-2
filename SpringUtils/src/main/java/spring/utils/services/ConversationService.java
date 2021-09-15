package spring.utils.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationService {
    private final MessageService messageService;
    private final IOService io;

    public void doConversation() {
        io.println(messageService.localize("conversation.hello"));
        io.print(messageService.localize("conversation.enter-numbers"));
        int x = io.nextInt();
        int y = io.nextInt();
        io.println(messageService.localize("conversation.arithmetic", x, y, x + y));
    }
}
