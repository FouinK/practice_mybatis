package mybatis.mybatisPractice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mybatis.mybatisPractice.dto.RequestMemberDto;
import mybatis.mybatisPractice.dto.RequestUpdateMemberDto;
import mybatis.mybatisPractice.dto.ResponseMemberDto;
import mybatis.mybatisPractice.mapper.MemberMapper;
import mybatis.mybatisPractice.model.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberMapper memberMapper;

    @PostMapping("/member")
    @Transactional
    public ResponseEntity<?> createMemebr(@RequestBody RequestMemberDto requestMemberDto) {

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());

        memberMapper.saveMember(
                Member.builder()
                        .username(requestMemberDto.getUsername())
                        .nickname(requestMemberDto.getNickname())
                        .build());

        return ResponseEntity.created(selfLink).build();
    }

    @GetMapping("/members")
    public ResponseEntity<?> getMembers() {
        return ResponseEntity.ok(memberMapper.findAll().stream()
                .map(member ->
                        ResponseMemberDto.builder()
                                .memberId(member.getMemberId())
                                .username(member.getUsername())
                                .nickname(member.getNickname())
                                .build()
                )
                .collect(Collectors.toList()));
    }

    @PutMapping("/member/{memberId}")
    public ResponseEntity<?> updateMember(
            @PathVariable Long memberId,
            @RequestBody RequestUpdateMemberDto requestUpdateMemberDto) {

        Member member = memberMapper.findById(memberId);

        member.setUsername(requestUpdateMemberDto.getUsername());
        member.setNickname(requestUpdateMemberDto.getNickname());

        memberMapper.updateMember(member);

        return ResponseEntity.ok("");
    }

    @DeleteMapping("/member/{memberId}")
    public ResponseEntity<?> deleteMember(
            @PathVariable Long memberId) {
        memberMapper.deleteById(memberId);
        return ResponseEntity.ok("");
    }
}
