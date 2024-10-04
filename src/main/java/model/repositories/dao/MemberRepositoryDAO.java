package model.repositories.dao;

import model.entities.Member;

import java.util.List;

public interface MemberRepositoryDAO {
    public void insertMember(Member member);
    public Member selectMemberByEmail(String email);
    public Member selectMemberById(int id);
    public List<Member> selectAllMembers();
}
