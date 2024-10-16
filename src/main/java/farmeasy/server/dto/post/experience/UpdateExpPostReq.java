package farmeasy.server.dto.post.experience;

import farmeasy.server.dto.post.UpdatePostRequest;
import farmeasy.server.entity.board.exprience.Recruitment;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class UpdateExpPostReq extends UpdatePostRequest {
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Schema(description = "체험 시작 일자", example = "2024-05-24")
    private String startDate;
    @Schema(description = "체험 시작 시간")
    private String startTime;
    @Schema(description = "체험 모집 인원")
    private int recruitmentNum;
    @Lob
    @Schema(description = "체험 상세 내용")
    private String detailedRecruitmentCondition;

    public static Recruitment reqToRecruitment(UpdateExpPostReq req){
        return Recruitment.builder()
                .startDate(req.getStartDate())
                .startTime(req.getStartTime())
                .recruitmentNum(req.getRecruitmentNum())
                .detailedRecruitmentCondition(req.getDetailedRecruitmentCondition())
                .build();
    }
}
