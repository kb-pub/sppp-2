package JDBC.and.Boot.ui;

import lombok.RequiredArgsConstructor;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomPromptProvider implements PromptProvider {
    private static final AttributedString
            UPDATING_BOOK_PROMPT = new AttributedString("processing-book> ",
                    AttributedStyle.BOLD.foreground(AttributedStyle.CYAN)),
            MAIN_MENU_PROMPT = new AttributedString("main-menu> ",
                    AttributedStyle.BOLD.foreground(AttributedStyle.MAGENTA));

    private final Commands commands;

    @Override
    public AttributedString getPrompt() {
        return switch (commands.getState()) {
            case PROCESSING_BOOK -> UPDATING_BOOK_PROMPT;
            case MAIN_MENU -> MAIN_MENU_PROMPT;
        };
    }
}
