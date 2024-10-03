package spring.utils.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ConversationServiceTest {

    ConversationService sut;

    MessageService messageService;

    IOService ioService;

    @BeforeEach
    void setUp() {
        messageService = mock(MessageService.class);
        ioService = mock(IOService.class);
        sut = new ConversationService(messageService, ioService);
    }

    @Test
    void doConversation() {

        // https://habr.com/ru/post/444982/
        // https://assertj.github.io/doc/

        int x = 489, y = 234, res = x + y;
        when(ioService.nextInt()).thenReturn(x, y).thenThrow(new RuntimeException());
        when(messageService.localize("conversation.arithmetic", x, y, res))
                .thenReturn("success");

        sut.doConversation();

        verify(ioService, times(1)).println("success");
    }
}