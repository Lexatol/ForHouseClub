package club.forhouse.entities.material;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Material material = (Material) o;

        return materialId.equals(material.materialId);
    }

    @Override
    public int hashCode() {
        return materialId.hashCode();
    }
}
