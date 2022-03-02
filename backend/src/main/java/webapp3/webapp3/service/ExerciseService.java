package webapp3.webapp3.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp3.webapp3.model.Exercise;
import webapp3.webapp3.repository.ExerciseRepository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository repository;

    public Optional<Exercise> findById(long id){
        return repository.findById(id);
    }

    public void pdfGenerator() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Hello World", font);

        document.add(chunk);
        document.close();
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
