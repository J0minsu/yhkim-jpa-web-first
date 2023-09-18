package jpa.web.part.first.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Item {
    private String author;
    private String isbn;

    protected Book(String name, int price, int stockQuantity, String author, String isbn) {
        super(name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }

    protected Book(Long id, String name, int price, int stockQuantity, String author, String isbn) {
        super(id, name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }

    public static Book of(String name, int price, int stockQuantity, String author, String isbn) {
        return new Book(name, price,stockQuantity,author,isbn);
    }

    public static Book of(Long id, String name, int price, int stockQuantity, String author, String isbn) {
        return new Book(id,name, price, stockQuantity, author, isbn);
    }

}
