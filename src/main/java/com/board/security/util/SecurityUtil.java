package com.board.security.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {

    public static String getLoginUsername() {
        UserDetails member = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return member.getUsername();
    }
}
