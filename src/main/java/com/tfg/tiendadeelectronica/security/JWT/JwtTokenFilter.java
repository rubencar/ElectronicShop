package com.tfg.tiendadeelectronica.security.JWT;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tfg.tiendadeelectronica.service.impl.UserDetailsServiceImpl;

public class JwtTokenFilter extends OncePerRequestFilter{

	private static  final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(req);
            if(token !=null && jwtProvider.validateToken(token)){
                String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(nombreUsuario);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (Exception e){
            logger.error("fail en método doFilter " + e.getMessage());
        }
        filterChain.doFilter(req, res);
    }

    
    /**
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request){
    	/*El token tiene la forma de la cadena Bearer xxxxx.yyyyy.zzzz*/
        String authReq = request.getHeader("Authorization");
        if(authReq != null && authReq.startsWith("Bearer "))
        	//Se elimina el inicio “Bearer “
            return authReq.replace("Bearer ", "");
        return null;
    }
    
}
