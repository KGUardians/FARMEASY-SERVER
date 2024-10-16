package farmeasy.server.entity.pestdetection;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CropPest {
    @Id
    private Long id;
    private String pestName;
    @ManyToOne(fetch = FetchType.LAZY)
    private Crop crop;
    @Lob
    private String description;
    public void setCrop(Crop crop){
        this.crop = crop;
        crop.getPests().add(this);
    }
}
