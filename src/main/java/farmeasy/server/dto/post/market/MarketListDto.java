package farmeasy.server.dto.post.market;

import farmeasy.server.dto.mainpage.PostListDto;
import farmeasy.server.entity.board.CropCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MarketListDto extends PostListDto {

    private String sigungu;
    private String farmName;
    private int price;
    private int gram;

    public MarketListDto(Long postId, String sigungu, String farmName, CropCategory cropCategory, int price, int gram, int postLike){
        super(postId,postLike,cropCategory);
        this.sigungu = sigungu;
        this.farmName = farmName;
        this.price = price;
        this.gram = gram;
    }

}
