package br.com.fiap.savvyfix.seguranca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/clientes/cadastro_cliente", "/css/**", "/images/**").permitAll()
                .requestMatchers("/produtos/deletar_produto/", "produtos/edita_produto/", "produtos/adiciona_produto/")
                .hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
        )
                .formLogin((form) -> form.loginPage("/clientes/login_cliente").defaultSuccessUrl("/", true).permitAll())
                .logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/clientes/login_cliente?logout=true").permitAll())
                .exceptionHandling((exception) ->
                        exception.accessDeniedHandler(((request, response, accessDeniedException) -> {response.sendRedirect("/clientes/acesso_negado");}))
                        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
