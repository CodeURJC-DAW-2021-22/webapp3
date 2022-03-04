package webapp3.webapp3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp3.webapp3.model.Member;
import webapp3.webapp3.repository.MemberRepository;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class MemberService {

    @Autowired
    private MemberRepository repository;

    public Optional<Member> findById(long id){
        return repository.findById(id);
    }

    public boolean exist(long id){
        return repository.existsById(id);
    }

    public List<Member> findAll() {
        return repository.findAll();
    }

    public void save(Member member) {
        repository.save(member);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }


    // generate data for graphics
}
