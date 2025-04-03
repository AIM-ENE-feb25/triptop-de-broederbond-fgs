package nl.han.soex.prototype.identityprovider.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nl.han.soex.prototype.identityprovider.exception.JwtNotValidException;
import nl.han.soex.prototype.identityprovider.security.TokenProvider;
import nl.han.soex.prototype.identityprovider.domain.IdentityProviderFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    public AuthenticationFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        String type = getTypeFromRequest(request);

        if (StringUtils.hasText(token)) {
            boolean isValid = IdentityProviderFactory.getIdentityProvider(type).isValidToken(token);

            if (!isValid)
                throw new JwtNotValidException();

            //TODO: Nu voor prototype genoeg
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    null,
                    null,
                    null
            );

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }
    private String getTypeFromRequest(HttpServletRequest request) {
        String type = request.getHeader("X-Authorization");

        if (StringUtils.hasText(type)) {
            return type;
        }

        return null;
    }
}

