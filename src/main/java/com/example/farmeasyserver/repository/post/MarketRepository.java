package com.example.farmeasyserver.repository.post;

import com.example.farmeasyserver.dto.ImageDto;
import com.example.farmeasyserver.entity.board.CropCategory;
import com.example.farmeasyserver.entity.board.market.MarketPost;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.web.PageableDefault;

import java.util.List;
import java.util.Optional;

public interface MarketRepository extends JpaRepository<MarketPost,Long> {
    @Query("SELECT mp FROM MarketPost mp join fetch mp.author ORDER BY mp.id DESC limit 4")
    List<MarketPost> findTop4OrderByIdDesc();

    @Query("select mp from MarketPost mp join fetch mp.author where mp.id = :id")
    Optional<MarketPost> findByIdWithUser(Long id);

    @Query("select mp from MarketPost mp " +
            "join fetch mp.author a " +
            "where mp.cropCategory = :crop ")
    Slice<MarketPost> findBySidoAndSigungu(CropCategory crop, Pageable pageable);
}
