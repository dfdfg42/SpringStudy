package com.example.CSEC.site.repository;

import com.example.CSEC.site.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 여기에 추가적으로 커스텀 메소드를 정의할 수 있습니다.
}
