package webapp3.webapp3.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import webapp3.webapp3.model.Exercise;
import webapp3.webapp3.model.ExerciseTable;
import webapp3.webapp3.model.User;
import webapp3.webapp3.repository.ExerciseRepository;
import webapp3.webapp3.repository.ExerciseTableRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ExerciseTableService {

    @Autowired
    private UserService memberService;

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

    public ByteArrayOutputStream generatePDF(Long userId, Long tableEx) throws DocumentException, IOException, SQLException {

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Optional<User> possibleMember = memberService.findById(userId);

        if(possibleMember.isPresent()){
            PdfPTable tableHeader = new PdfPTable(2);
            PdfPTable tableDoc = new PdfPTable(4);
            PdfWriter.getInstance(document, baos);
            document.open();

            //access table with chekbox

            Optional<ExerciseTable> exTab = exerciseTabRep.findById(tableEx);

            if (exTab.isPresent()){

                ExerciseTable exTabGet = exTab.get();

                Stream.of(exTabGet.getName(), exTabGet.getDescription())
                        .forEach(columnTitle -> {
                            PdfPCell header = new PdfPCell();
                            header.setBackgroundColor(BaseColor.ORANGE);
                            header.setBorderWidth(1);
                            header.setPhrase(new Phrase(columnTitle));
                            tableHeader.addCell(header);
                        });
                document.add(tableHeader);

                List<Exercise> exList = exTabGet.getExercises();

                Stream.of("Nombre", "Descripción", "Material", "Imagen")
                        .forEach(columnTitle -> {
                            PdfPCell header = new PdfPCell();
                            header.setBackgroundColor(BaseColor.ORANGE);
                            header.setBorderWidth(1);
                            header.setPhrase(new Phrase(columnTitle));
                            tableDoc.addCell(header);
                        });

                for (Exercise ex: exList) {
                    tableDoc.addCell(ex.getName());
                    tableDoc.addCell(ex.getDescription());
                    tableDoc.addCell(ex.getMaterial());
                    tableDoc.addCell(Image.getInstance(ex.getImage().getBinaryStream().readAllBytes()));
                }

                document.add(tableDoc);
            }
            // Close streams
            document.close();
        }
        baos.close();
        return baos;
    }

    public Page<ExerciseTable> findPage(int page){
        return exerciseTabRep.findAll(PageRequest.of(page, 8));
    }
}
