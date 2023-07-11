package com.zap.lojazap.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zap.lojazap.domaindois.service.EnvioEmailService;
import com.zap.lojazap.infrastructure.repository.service.email.FakeEnvioEmailService;
import com.zap.lojazap.infrastructure.repository.service.email.SandboxEnvioEmailService;
import com.zap.lojazap.infrastructure.repository.service.email.SmtpEnvioEmailService;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
     EnvioEmailService envioEmailService() {
        // Acho melhor usar switch aqui do que if/else if
        switch (emailProperties.getImpl()) {
            case FAKE:
                return new FakeEnvioEmailService();
            case SMTP:
                return new SmtpEnvioEmailService();
            case SANDBOX:
                return new SandboxEnvioEmailService();
            default:
                return null;
        }
    }
}
