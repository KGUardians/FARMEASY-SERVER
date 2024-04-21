package com.example.farmeasyserver.controller.post;

import com.example.farmeasyserver.dto.post.ruralexp.RuralExpRequest;
import com.example.farmeasyserver.dto.response.Response;
import com.example.farmeasyserver.service.post.PostService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rural-exp")
@RequiredArgsConstructor
public class RuralExpController {
    private final PostService postService;
    @PostMapping("/posts")
    public Response create(@Valid @ModelAttribute RuralExpRequest req) throws ChangeSetPersister.NotFoundException {
        return Response.success(postService.createRuralExpPost(req));
    }

    @ApiOperation(value = "농촌체험 게시글 조회", notes = "게시글을 조회한다.")
    @GetMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public Response read(@ApiParam(value = "게시글 id", required = true) @PathVariable Long postId) throws ChangeSetPersister.NotFoundException {
        return Response.success(postService.readRuralExpPost(postId));
    }
}
