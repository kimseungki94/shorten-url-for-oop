package com.shortenURL;

import com.shortenURL.parse.Base62;
import com.shortenURL.parse.TextParse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public TextParse parseUtil() {
        return new Base62();
    }
}
