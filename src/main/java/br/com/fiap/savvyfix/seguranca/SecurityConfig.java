package br.com.fiap.savvyfix.seguranca;

import jakarta.servlet.http.HttpServlet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChai(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/produtos/deletar_produto/", "produtos/edita_produto/", "produtos/adiciona_produto")
                .hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
        )
                .formLogin((form) -> form.loginPage("/clientes/login_cliente").defaultSuccessUrl("/produtos").permitAll())
                .logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/logout?logout=true").permitAll())
                .exceptionHandling((exception) ->
                        exception.accessDeniedHandler(((request, response, accessDeniedException) -> {response.sendRedirect("/acesso_negado");}))
                        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
