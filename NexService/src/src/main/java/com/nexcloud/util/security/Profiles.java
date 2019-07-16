/*
* Copyright 2019 NexCloud Co.,Ltd.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.nexcloud.util.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class Profiles {
	public static final String DEVELOPMENT = "dev";
	public static final String PRODUCTION = "prod";
}

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@ComponentScan(basePackages = {"com.nexcloud.util.security.*"})
@Profile(Profiles.PRODUCTION)
class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    AuthProvider authProvider;
    
    @Autowired
    AuthFailureHandler authFailureHandler;
 
    @Autowired
    AuthSuccessHandler authSuccessHandler;
 
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 허용되어야 할 경로들
        web.ignoring().antMatchers("/resources/**", 
                                   "/dist/**", 
                                   "/weather", 
                                   "/user/password/find",
                                   "/user/email",
                                   "/user/send/temppw",
                                   "/findpw", 
                                   "/user/findpw",
                                   "/user/cert/check",
                                   "/join", 
                                   "/getLanguage/**",
                                   "/getMessage"); // #3
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
 
        // 로그인 설정
        http.authorizeRequests()
            // ROLE_USER, ROLE_ADMIN으로 권한 분리 유알엘 정의
            .antMatchers("/user/join", "/user/login", "/error**").permitAll()
            .antMatchers("/**").access("ROLE_USER")
            .antMatchers("/**").access("ROLE_ADMIN")
            .antMatchers("/admin/**").access("ROLE_ADMIN")
            .antMatchers("/**").authenticated()
        .and()
            // 로그인 페이지 및 성공 url, handler 그리고 로그인 시 사용되는 id, password 파라미터 정의
            .formLogin()
            .loginPage("/user/login")
            .defaultSuccessUrl("/", true)
            .failureUrl("/")
            .failureHandler(authFailureHandler)
            .successHandler(authSuccessHandler)
            .usernameParameter("id")
            .passwordParameter("password")
        .and()    
            // 로그아웃 관련 설정
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
        .and()
            // csrf 사용유무 설정
            // csrf 설정을 사용하면 모든 request에 csrf 값을 함께 전달해야한다.
            //.csrf().and()
            // 로그인 프로세스가 진행될 provider
            .authenticationProvider(authProvider)
            .csrf().disable();
    }
}

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@ComponentScan(basePackages = {"com.nexcloud.util.security.*"})
@Profile(Profiles.DEVELOPMENT)
class DevSpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
    }
}
