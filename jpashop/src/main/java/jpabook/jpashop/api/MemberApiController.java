package jpabook.jpashop.api;

import jakarta.validation.Valid;
import jpabook.jpashop.Dto.MemberRequestDto;
import jpabook.jpashop.Dto.MemberResponseDto;
import jpabook.jpashop.Entity.Member;
import jpabook.jpashop.Entity.ResultEntity;
import jpabook.jpashop.Enum.StatusEnum;
import jpabook.jpashop.Service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

//    @PostMapping("/api/v1/members")
//    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member)  {
//        Long id = memberService.join(member);
//        return new CreateMemberResponse(id);
//    }

    /**
     * Member 생성
    */
    @PostMapping("/api/v2/members")
    public ResultEntity<MemberResponseDto> saveMemberV2(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        MemberResponseDto memberResponseDto = new MemberResponseDto();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Member member = new Member();
        member.setName(memberRequestDto.getName());
        member.setAddress(memberRequestDto.getAddress());

        try {
            Long id = memberService.join(member);
            memberResponseDto.setId(id);
            memberResponseDto.setName(memberRequestDto.getName());
        } catch (Exception e) {
            return new ResultEntity<>(StatusEnum.BAD_REQUEST.getStatusCode(), StatusEnum.BAD_REQUEST.getCode());
        }

        return new ResultEntity<>(memberResponseDto);
    }

    /**
     * Member 전체 조회
     */
    @GetMapping("/api/members")
    public ResultEntity<List<MemberResponseDto>> MemberList() {
        List<MemberResponseDto> memberResponseDtos =  new ArrayList<>();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<Member> members = memberService.findMembers();

        try {
            if(!(members.size() == 0)) {
                for(int i = 0; i < members.size(); i++) {
                    MemberResponseDto memberResponseDto = new MemberResponseDto();
                    memberResponseDto.setId(members.get(i).getId());
                    memberResponseDto.setName(members.get(i).getName());
                    memberResponseDtos.add(i, memberResponseDto);
                }
            }
        } catch (Exception e) {
            return new ResultEntity<>(9999, "조회된 회원이 없습니다.");
        }

        return new ResultEntity<>(memberResponseDtos);
    }

    /**
     * Member 단일 조회
     */
    @GetMapping("/api/members/{id}")
    public ResultEntity<MemberResponseDto> MemberDetail (@PathVariable("id") Long id) {
        MemberResponseDto memberResponseDto =  new MemberResponseDto();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        try {
            Member member = memberService.findOne(id);

            memberResponseDto.setId(member.getId());
            memberResponseDto.setName(member.getName());
        } catch (Exception e) {
            return new ResultEntity<>(9999, "조회된 회원이 없습니다.");
        }

        return new ResultEntity<>(memberResponseDto);
    }


}
