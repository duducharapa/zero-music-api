package com.charapadev.zeromusic.music;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "musics")
public class Music implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String coverUrl;

    @Column
    private String fileUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Music compared = (Music) o;
        return getId() != null && Objects.equals(getId(), compared.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
