package org.sid.sec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		
		// les qcces for domain or navigator
		
		 response.addHeader("Access-Control-Allow-Origin", "*");
	        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,authorization");
	        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
	      
		
		
		// if requet has methode option nn fait pas search token	 
		if(request.getMethod().equals("OPTIONS"))
		{
			response.setStatus(HttpServletResponse.SC_OK);
		}
		// si request has login exit
		else if(request.getRequestURI().equals("/login"))
		{
			filterChain.doFilter(request, response);
			return ;
		}
		else
		{
		// get jwt token 
		String jwtToken = request.getHeader(SecurtyParams.HEADER_NAME);
		if(jwtToken==null || !jwtToken.startsWith(SecurtyParams.HEADER_PREFIX))
		{// if jwt null dofilter == Exit
			filterChain.doFilter(request, response);
			return;
		}
		// algorithme que utiliser pour verfier token 
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurtyParams.SECRETE)).build();
		// decoder jwk apres supprimer le bearer
		DecodedJWT decodedJWT = verifier.verify(jwtToken.substring(SecurtyParams.HEADER_PREFIX.length()));
		// get username
		String username = decodedJWT.getSubject();
		// get les roles and convert to list 
		List<String> roles  = decodedJWT.getClaims().get("roles").asList(String.class);
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		roles.forEach(rn -> {
			authorities.add(new SimpleGrantedAuthority(rn));
		});
		
		// declare user spring with username and pass null and autorty
		UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null , authorities) ;
		// authentifier this user with spring methode
		SecurityContextHolder.getContext().setAuthentication(user);
 		// pqsse to filter suivant ou exit
		filterChain.doFilter(request, response);

		}
		
	}

}
