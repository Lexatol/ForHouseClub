package club.forhouse.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "materials")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    @NonNull
    private Long materialId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private MaterialCategory category;

    private String name;
    private String description;
}
