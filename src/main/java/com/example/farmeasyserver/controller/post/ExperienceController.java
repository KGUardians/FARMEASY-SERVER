package com.example.farmeasyserver.controller.post;

import com.example.farmeasyserver.dto.post.experience.ExperienceRequest;
import com.example.farmeasyserver.dto.response.Response;
import com.example.farmeasyserver.service.post.PostService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("experience")
@RequiredArgsConstructor
public class ExperienceController {
    private final PostService postService;

    @GetMapping
    public Response readExperiencePostList(Pageable pageable){
        return Response.success(postService.getExperiencePostList(pageable));
    }

    @PostMapping("/post")
    public Response create(@Valid @ModelAttribute ExperienceRequest req) throws ChangeSetPersister.NotFoundException {
        return Response.success(postService.createExperiencePost(req));
    }

    @ApiOperation(value = "농촌체험 해당 게시글 조회", notes = "게시글을 조회한다.")
    @GetMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public Response read(@ApiParam(value = "게시글 id", required = true) @PathVariable Long postId) throws ChangeSetPersister.NotFoundException {
        return Response.success(postService.readExperiencePost(postId));
    }
}
