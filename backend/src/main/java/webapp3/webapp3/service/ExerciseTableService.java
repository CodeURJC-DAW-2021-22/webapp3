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
import webapp3.webapp3.model.ExerciseTable;
import webapp3.webapp3.model.User;
import webapp3.webapp3.repository.ExerciseRepository;
import webapp3.webapp3.repository.ExerciseTableRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ExerciseTableService {

    @Autowired
    private UserService memberService;

    @Autowired
    private ExerciseRepository repository;

    @Autowired
    private ExerciseTableRepository exerciseTabRep;

    public Optional<ExerciseTable> findById(Long id){
        return exerciseTabRep.findById(id);
    }

    public List<ExerciseTable> findAll(){
        return exerciseTabRep.findAll();
    }

    public void delete(Long id){
        exerciseTabRep.deleteById(id);
    }

    public ExerciseTable save(ExerciseTable exTab){
        return exerciseTabRep.save(exTab);
    }

    public ByteArrayOutputStream generatePDF(Long exerciseId, Long userId) throws DocumentException, IOException {

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Optional<User> possibleMember = memberService.findById(userId);
        if(possibleMember.isPresent()){
            PdfWriter.getInstance(document, baos);
            document.open();

            User member = possibleMember.get();
            // Sumo 1 al contador

            Optional<Exercise> possibleExercise = repository.findById(exerciseId);
            if (possibleExercise.isPresent()){
                PdfPTable table = new PdfPTable(3);
                addTableHeader(table);

                table.addCell("row 1, col 1");
                table.addCell("row 1, col 2");
                table.addCell("row 1, col 3");

                document.add(table);

            } else {
                // ...
            }

            // Cierro streams
            document.close();
        } else {

        }

        baos.close();
        return baos;
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("column header 1", "column header 2", "column header 3")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.MAGENTA);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }
}
