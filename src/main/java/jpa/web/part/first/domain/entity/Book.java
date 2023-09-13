package jpa.web.part.first.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * packageName    : jpa.web.part.first.domain.entity
 * fileName       : Book
 * author         : ms.jo
 * date           : 2023/09/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/09/13        ms.jo       최초 생성
 */
@Entity
@Getter
@Setter
public class Book extends Item {
    private String author;
    private String isbn;
}
