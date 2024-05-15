package com.example.farmeasyserver.repository.post.market;

import com.example.farmeasyserver.dto.post.market.ListMarketDto;
import com.example.farmeasyserver.entity.board.CropCategory;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.farmeasyserver.entity.board.market.QMarketPost.*;

@Repository
public class MarketRepo {
    private final EntityManager em;
    private final JPAQueryFactory query;

    public MarketRepo(EntityManager em){
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public List<ListMarketDto> findTop4OrderByIdDesc(){
        return em.createQuery(
                "SELECT new com.example.farmeasyserver.dto.post.market.ListMarketDto(mp.id, a.address.sigungu, a.farm.farmName, mp.cropCategory,mp.item.price,mp.item.gram,mp.postLike) " +
                        "FROM MarketPost mp " +
                        "join mp.author a " +
                        "ORDER BY mp.id DESC limit 4", ListMarketDto.class)
                .getResultList();
    };

    public Slice<ListMarketDto> findPostList(MarketFilter filter, Pageable pageable){

        int pageSize = pageable.getPageSize();
        List<ListMarketDto> postList;
        postList = query
                .select(Projections.constructor(
                        ListMarketDto.class,
                        marketPost.id,
                        marketPost.author.address.sigungu,
                        marketPost.author.farm.farmName,
                        marketPost.cropCategory,
                        marketPost.item.price,
                        marketPost.item.gram,
                        marketPost.postLike
                ))
                .from(marketPost)
                .innerJoin(marketPost.author)
                .where(cropEq(filter.getCrop()))
                .orderBy(marketPost.postedTime.desc())
                .offset(pageable.getOffset())
                .limit(pageSize+1)
                .fetch();

        boolean hasNext = postList.size() > pageSize;
        if(hasNext){
            postList.remove(pageSize);
        }
        return new SliceImpl<>(postList,pageable,hasNext);
    }

    private BooleanExpression cropEq(CropCategory crop){
        return crop != null && !crop.toString().isEmpty() ? marketPost.cropCategory.eq(crop):null;
    }

}
