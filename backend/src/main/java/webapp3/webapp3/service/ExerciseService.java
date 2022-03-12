package webapp3.webapp3.service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp3.webapp3.model.Exercise;
import webapp3.webapp3.model.User;
import webapp3.webapp3.repository.ExerciseRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository repository;

    public Optional<Exercise> findById(long id){
        return repository.findById(id);
    }

    public boolean exist(long id){
        return repository.existsById(id);
    }

    public List<Exercise> findAll() {
        return repository.findAll();
    }

    public void save(Exercise activity) {
        repository.save(activity);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }


}
