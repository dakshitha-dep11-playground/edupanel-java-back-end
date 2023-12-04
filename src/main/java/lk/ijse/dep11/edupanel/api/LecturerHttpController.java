package lk.ijse.dep11.edupanel.api;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import lk.ijse.dep11.edupanel.to.request.LecturerRequestTO;
import lk.ijse.dep11.edupanel.to.response.LecturerResTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/lecturers")
public class LecturerHttpController {

    @Autowired
    private DataSource pool;

    @Autowired
    private Bucket bucket;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "multipart/form-data", produces = "application/json")
    public LecturerResTO createNewLecturer(@ModelAttribute @Valid LecturerRequestTO lecturer){
        try (Connection connection = pool.getConnection()){

            /* Transaction mode */
            connection.setAutoCommit(false);
            try {
                PreparedStatement stm = connection.prepareStatement("INSERT INTO lecturer ( name, designation, qualifications, linkedin) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

                stm.setString(1,lecturer.getName());
                stm.setString(2,lecturer.getDesignation());
                stm.setString(3,lecturer.getQualifications());
                stm.setString(4,lecturer.getLinkedIn());
                stm.executeUpdate();
                ResultSet generatedKeys = stm.getGeneratedKeys();
                generatedKeys.next();
                int lecturerID = generatedKeys.getInt(1);

                /*Picture name*/
                String picture = lecturerID + "-" +lecturer.getName();

                /* Save picture data in database */
                if(lecturer.getPicture() != null && !lecturer.getPicture().isEmpty()) {
                    System.out.println("Picture have");
                    PreparedStatement stmPicture = connection.prepareStatement("UPDATE lecturer SET picture=? WHERE id=?");
                    stmPicture.setString(1,picture);
                    stmPicture.setInt(2,lecturerID);
                    stmPicture.executeUpdate();
                }

                /* Insert data in to full-time table or part-time table*/
                final String table = lecturer.getType().equalsIgnoreCase("full-time")?"full_time_rank":"part_time_rank";

                    Statement stmGetRank = connection.createStatement();
                    ResultSet rst = stmGetRank.executeQuery("SELECT `rank` FROM "+table+" ORDER BY  `rank` DESC LIMIT 1");

                    int newRank ;


                    if(!rst.next()){
                        newRank =1;
                    }else {
                        newRank = rst.getInt(1)+1;
                    }
                System.out.println(newRank);
                    PreparedStatement stmFullOrPartTime = connection.prepareStatement("INSERT INTO "+table+" (lecture_id, `rank`) VALUES (?,?)");
                    stmFullOrPartTime.setInt(1,lecturerID);
                    stmFullOrPartTime.setInt(2,newRank);
                    stmFullOrPartTime.executeUpdate();

                /* Save Picture in firebase storage */
                String pictureURL = null;
                if(lecturer.getPicture() != null && !lecturer.getPicture().isEmpty()){
                    Blob blob = bucket.create(picture, lecturer.getPicture().getInputStream(), lecturer.getPicture().getContentType());
                    pictureURL = blob.signUrl(1, TimeUnit.DAYS, Storage.SignUrlOption.withV4Signature()).toString();

                }

                connection.commit();

                return new LecturerResTO(lecturerID,
                                lecturer.getName(),
                                lecturer.getDesignation(),
                                lecturer.getQualifications(),
                                lecturer.getType(),
                                pictureURL,
                                lecturer.getLinkedIn() );
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            } finally {
                connection.setAutoCommit(true);
            }



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        System.out.println(lecturer);
//        System.out.println("Save this lecturer");
    }

    @PatchMapping("/{lecturer-id}")
    public void updateLecturerDetails(){
        System.out.println("update lecturer");
    }

    @DeleteMapping("/{lecturer-id}")
    public void DeleteLecturer(){
        System.out.println("Delete lec");
    }

    @GetMapping
    public void getAllLecturers(){
        System.out.println("get all lec");
    }



}
