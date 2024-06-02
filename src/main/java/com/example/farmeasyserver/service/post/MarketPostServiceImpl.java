package com.example.farmeasyserver.service.post;

import com.example.farmeasyserver.dto.post.CreatePostResponse;
import com.example.farmeasyserver.dto.post.market.CreateMktPostRequest;
import com.example.farmeasyserver.dto.post.market.MarketListDto;
import com.example.farmeasyserver.dto.post.market.MarketPostDto;
import com.example.farmeasyserver.dto.post.market.UpdateMktPostReq;
import com.example.farmeasyserver.entity.board.market.MarketPost;
import com.example.farmeasyserver.entity.user.User;
import com.example.farmeasyserver.repository.post.market.MarketFilter;
import com.example.farmeasyserver.repository.post.market.MarketJpaRepo;
import com.example.farmeasyserver.repository.post.market.MarketRepo;
import com.example.farmeasyserver.util.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketPostServiceImpl implements MarketPostService{

    private final MarketJpaRepo marketJpaRepo;
    private final MarketRepo marketRepo;
    private final PostServiceImpl postService;

    @Override
    public List<MarketListDto> getRecentMarketPostDtos() {
        List<MarketListDto> recentMarketPosts = marketRepo.findTop4OrderByIdDesc();
        postService.imageMapping(recentMarketPosts); return recentMarketPosts;
    }

    @Override
    @Transactional
    public CreatePostResponse createMarketPost(CreateMktPostRequest req, User author) {
        MarketPost marketPost = postService.createPost(CreateMktPostRequest.toEntity(req), req, author);
        marketJpaRepo.save(marketPost);
        return new CreatePostResponse(marketPost.getId(),marketPost.getPostType());
    }

    @Override
    public MarketPostDto readMarketPost(Long postId){
        return MarketPostDto.toDto(getMarketPost(postId));
    }


    @Override
    @Transactional
    public MarketPostDto updateMarketPost(Long postId, UpdateMktPostReq req, User author) {
        MarketPost post = getMarketPost(postId);
        postService.updatePost(author, post, req);
        post.setItem(UpdateMktPostReq.reqToItem(req));
        marketJpaRepo.save(post);
        return MarketPostDto.toDto(post);
    }

    @Override
    @Transactional
    public Long deleteMarketPost(Long postId, User author) {
        MarketPost post = getMarketPost(postId);
        postService.deletePost(post,author);
        return postId;
    }

    @Override
    public Slice<MarketListDto> getMarketPosts(MarketFilter filter, Pageable pageable) {
        Slice<MarketListDto> listResponse = marketRepo.findPostList(filter, pageable);
        postService.imageMapping(listResponse.stream().toList()); return listResponse;
    }

    private MarketPost getMarketPost(Long postId){
        return marketJpaRepo.findByIdWithUser(postId).orElseThrow(() -> new ResourceNotFoundException("MarketPost", "marketPost", null));
    }
}
