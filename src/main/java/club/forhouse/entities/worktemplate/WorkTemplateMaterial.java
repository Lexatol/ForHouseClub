package club.forhouse.entities.worktemplate;

import club.forhouse.entities.material.Material;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "works_templates_materials")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class WorkTemplateMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_id")
    @NonNull
    private Long rowId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operation_id")
    @ToString.Exclude
    @NonNull
    private WorkTemplateOperation operationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id")
    @NonNull
    @ToString.Exclude
    private Material materialId;

    private int quantity = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkTemplateMaterial material = (WorkTemplateMaterial) o;

        return rowId.equals(material.rowId);
    }

    @Override
    public int hashCode() {
        return rowId.hashCode();
    }
}
