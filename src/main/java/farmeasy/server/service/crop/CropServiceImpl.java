package farmeasy.server.service.crop;

import farmeasy.server.dto.CropPestDto;
import farmeasy.server.repository.CropJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService {

    private final CropJpaRepo cropRepository;

    public List<CropPestDto> findPests(Long cropId,List<String> pestList){
        //리턴할 목록
        List<CropPestDto> selectedPests = new ArrayList<>();
        //해당 작물의 병충해 목록
        List<CropPestDto> cropPestList = cropRepository.findPestsByCrop(cropId);

        for(CropPestDto pest : cropPestList){
            if(pestList.contains(pest.getPestName())){
                selectedPests.add(pest);
            }
        }

        return selectedPests;
    }
}
