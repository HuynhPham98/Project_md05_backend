package com.ra.module5_project.security.jwt;

import com.ra.module5_project.model.entity.User;
import com.ra.module5_project.security.principle.UserDetailService;
import com.ra.module5_project.service.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JWTAuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private final JWTProvider jwtProvider;
    @Autowired
    private  final  UserDetailService  userDetailService;
    @Autowired
    private final UserService userService;
    public JWTAuthTokenFilter(JWTProvider jwtProvider, UserDetailService userDetailService, UserService userService) {
        this.jwtProvider = jwtProvider;
        this.userDetailService = userDetailService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);
       try {
           if(token != null && jwtProvider.validateToken(token)){
               String username = jwtProvider.getUsernameFromToken(token);
               UserDetails userDetails = userDetailService.loadUserByUsername(username);
               if(userDetails != null){
                   User user = userService.getUserByUsername(username);
                   if(user != null && user.isStatusLogin() && !user.is_deleted() && user.isStatus()){
                       UsernamePasswordAuthenticationToken authenticationToken
                               = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                       authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                       SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                   }else {
                       response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"user not login !");
                       return;
                   }
               }
           }
       }catch (Exception ex){
           logger.error(ex.getMessage());
       }
       filterChain.doFilter(request,response);
    }

    public String getTokenFromRequest(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")){
            return header.substring(7);
        }else {
            return null ;
        }
    }

}
