package model.services;

import exceptions.ServicesException;
import model.entities.Member;
import model.repositories.MemberRepository;
import model.services.dao.MemberDAO;

public class MemberServices implements MemberRepository {

    MemberDAO memberDao;

    public MemberServices(MemberDAO memberDao){
        this.memberDao = memberDao;
    }

    @Override
    public void registerNewMember(Member member) {
        if(member.getEmail().equals(memberDao.selectMemberByEmail(member.getEmail()))){
            throw new ServicesException("You does not create a member with this email, there is a member with this email");
        }
        memberDao.insertMember(member);
    }

    @Override
    public Member findMemberByEmail(String email) {
        if(email == null){
            throw new ServicesException("Write a valid email (Example@gmail.com)");
        }
        Member member = memberDao.selectMemberByEmail(email);

        return member;
    }
}
