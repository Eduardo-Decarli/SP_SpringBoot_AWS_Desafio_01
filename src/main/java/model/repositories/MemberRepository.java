package model.repositories;

import model.entities.Member;

import java.util.List;

public interface MemberRepository {
    public void registerNewMember(Member member);
    public Member findMemberByEmail(String email);
    public List<Member> findAllMembers();
}
