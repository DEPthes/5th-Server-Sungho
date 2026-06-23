package com.panacea.board.controller;

import com.panacea.board.dto.PostRequestDto;
import com.panacea.board.dto.PostResponseDto;
import com.panacea.board.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    // ── Thymeleaf 화면 ──────────────────────────────────────────────

    /** 게시글 목록 */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "post/list";
    }

    /** 게시글 작성 폼 */
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("postRequestDto", new PostRequestDto());
        return "post/create";
    }

    /** 게시글 작성 처리 */
    @PostMapping
    public String create(@Valid @ModelAttribute PostRequestDto postRequestDto,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "post/create";
        }
        postService.createPost(postRequestDto);
        return "redirect:/posts";
    }

    /** 게시글 상세 */
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        return "post/detail";
    }

    /** 게시글 수정 폼 */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        PostResponseDto post = postService.getPost(id);
        PostRequestDto dto = new PostRequestDto();
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setAuthor(post.getAuthor());
        model.addAttribute("post", post);
        model.addAttribute("postRequestDto", dto);
        return "post/edit";
    }

    /** 게시글 수정 처리 */
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute PostRequestDto postRequestDto,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postService.getPost(id));
            return "post/edit";
        }
        postService.updatePost(id, postRequestDto);
        return "redirect:/posts/" + id;
    }

    /** 게시글 삭제 처리 */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }

    // ── REST API (Postman 테스트용) ──────────────────────────────────

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<PostResponseDto> apiCreate(@Valid @RequestBody PostRequestDto dto) {
        return ResponseEntity.ok(postService.createPost(dto));
    }

    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<PostResponseDto>> apiList() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<PostResponseDto> apiDetail(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<PostResponseDto> apiUpdate(@PathVariable Long id,
                                                     @Valid @RequestBody PostRequestDto dto) {
        return ResponseEntity.ok(postService.updatePost(id, dto));
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> apiDelete(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
