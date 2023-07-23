package com.charapadev.zeromusic.author;

import com.charapadev.zeromusic.music.Music;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "author") @ToString.Exclude
    private Set<Music> musics;

    private void addMusic(Music music) {
        if (this.musics == null) this.musics = new HashSet<>();
        musics.add(music);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Author compared = (Author) o;
        return getId() != null && Objects.equals(getId(), compared.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
