package com.board.security.Member;

import com.board.domain.member.entity.Member;
import com.board.domain.member.repository.MemberRepository;
import com.board.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findMemberByEmail(email).orElseThrow(MemberNotFoundException::new);

        return MemberDatails.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .authorities(member.getRoles().stream()
                        .map(auth -> new SimpleGrantedAuthority(auth.toString())).collect(Collectors.toList()))
                .build();
    }
}
